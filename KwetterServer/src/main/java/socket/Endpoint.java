/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
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
public class Endpoint {
    
}
