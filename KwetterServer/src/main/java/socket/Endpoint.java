/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import socket.serialize.MessageDecoder;
import socket.serialize.MessageEncoder;

/**
 *
 * @author Tomt
 */
@ServerEndpoint(
        value = "/kwetterendpoint/",
        encoders = {MessageEncoder.class},
        decoders = {MessageDecoder.class}
)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton
public class EndPoint {

    private static final Logger LOG = Logger.getLogger(EndPoint.class.getName());
    private final Set<Session> peers;

    static {
        LOG.setLevel(Level.ALL);
    }
    
    @EJB
    private EndPoint delegate;

    public EndPoint() {
        this.peers = Collections.synchronizedSet(new HashSet<Session>());
    }
    
    @OnOpen
    public void onOpen(Session session, EndpointConfig cfg){
        LOG.log(Level.FINE, "openend session {0}");
        this.peers.add(session);
    }
    
    @OnClose
    public void onClose(Session session, EndpointConfig cfg){
        LOG.log(Level.FINE, "closed session {0}");
        this.peers.remove(session);
    }
    
    @OnError
    public void onError(Throwable t) {
        LOG.log(Level.INFO, t.getMessage());
    }
}
