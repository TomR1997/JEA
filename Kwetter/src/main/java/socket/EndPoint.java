/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import boundary.api.dto.PostDTO;
import domain.Post;
import domain.User;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.websocket.DecodeException;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import service.PostService;
import service.UserService;
import service.exceptions.InvalidIdException;
import service.exceptions.NonExistingPostException;
import service.exceptions.NonExistingUserException;

/**
 *
 * @author Tomt
 */
@ServerEndpoint(
        value = "/kwetterendpoint/{username}",
        encoders = {MessageEncoder.class},
        decoders = {MessageDecoder.class},
        configurator = Configurator.class
)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton
public class EndPoint {

    private static final Logger LOG = Logger.getLogger(EndPoint.class.getName());
    private final Map<Session, String> peers;

    @Inject
    private PostService postService;

    @Inject
    private UserService userService;

    private final MessageEncoder encoder;
    private final MessageDecoder decoder;

    static {
        LOG.setLevel(Level.ALL);
    }

    public EndPoint() {
        this.peers = Collections.synchronizedMap(new HashMap<Session, String>());
        this.encoder = new MessageEncoder();
        this.decoder = new MessageDecoder();
    }

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        LOG.log(Level.FINE, "openend session by {0}", username);
        this.peers.put(session, username);
    }

    @OnClose
    public void onClose(Session session, EndpointConfig cfg) {
        LOG.log(Level.FINE, "closed session {0}", session.getId());
        this.peers.remove(session);
    }

    @OnError
    public void onError(Throwable t, Session session) {
        LOG.log(Level.SEVERE, "an error occured in session " + session, t.getMessage());
    }

    @OnMessage
    public void onMessage(final Session session, final String message) {
        Message post = null;
        User user = null;
        Post latestPost = null;

        try {
            LOG.log(Level.INFO, "message: {0}", message);
            post = decoder.decode(message);
            LOG.log(Level.INFO, "post: {0}", post);
        } catch (DecodeException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }

        if (post != null) {
            try {
                user = userService.findUser(post.getId());
            } catch (NonExistingUserException | InvalidIdException ex) {
                LOG.log(Level.SEVERE, ex.getMessage());
            }
            if (user != null) {
                try {
                    latestPost = postService.createPostSocket(user.getId(), post.getMessage());
                } catch (NonExistingUserException | InvalidIdException | NonExistingPostException ex) {
                    LOG.log(Level.SEVERE, ex.getMessage());
                }
                
                if (latestPost != null) {
                    String socketPost = null;
                    try {
                        socketPost = encoder.encode(new PostDTO(latestPost));
                    } catch (EncodeException ex) {
                        Logger.getLogger(EndPoint.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    LOG.log(Level.INFO, "newpost: {0}", socketPost);
                    broadcast(socketPost, user);
                }

            }
        }
    }

    private void sendMessage(Session peer, Object message) {
        try {
            if (peer.isOpen()) {
                peer.getBasicRemote().sendObject(message);
            }
        } catch (IOException | EncodeException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    private void broadcast(Object message, User user) {
        for (User follower : user.getFollowing()) {
            if (peers.containsValue(follower.getUsername())) {
                for (Session peer : peers.keySet()) {
                    sendMessage(peer, message);
                }
            }
        }
    }

}
