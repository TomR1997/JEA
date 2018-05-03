/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Tomt
 */
@ServerEndpoint(
    value = "/kwetterendpoint/",
    encoders = {MessageEncoder.class},
    decoders = {MessageDecoder.class},
    configurator = Configurator.class
)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton
public class EndPoint {

    private static final Logger LOG = Logger.getLogger(EndPoint.class.getName());
    private final Map<Session, String> peers;

    static {
        LOG.setLevel(Level.ALL);
    }
    
    @EJB
    private EndPoint delegate;

    public EndPoint() {
        this.peers = new HashMap<>();
    }
    
    @OnOpen
    public void onOpen(Session session, EndpointConfig cfg){
        LOG.log(Level.FINE, "openend session by {0}", session.getId());
        //Get username
        this.peers.put(session, session.getId());
    }
    
    @OnClose
    public void onClose(Session session, EndpointConfig cfg){
        LOG.log(Level.FINE, "closed session {0}", session.getId());
        this.peers.remove(session);
    }
    
    @OnError
    public void onError(Throwable t, Session session) {
        LOG.log(Level.SEVERE, "an error occured in session " + session, t.getMessage());
    }
    
    //getAsyncRemote()?
    @OnMessage
    public void onMessage(final Session session, final Message message){
        final Runnable runnable = () -> {
            try{
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException t){
                LOG.log(Level.SEVERE, "error in asynchronious runnable execution", t);
            }
        };
    }
    
    //getAsyncRemote()?
    private void sendMessage(Session peer, Object message) {
        try {
            if (peer.isOpen()) {
                peer.getBasicRemote().sendObject(message);
            }
        } catch (IOException | EncodeException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
}
