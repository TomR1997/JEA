/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Tomt
 */
public class MessageEncoder implements Encoder.Text<Message> {

    private final Gson gson = new Gson();
    private static final Logger LOG = Logger.getLogger(MessageEncoder.class.getName());

    static {
        LOG.setLevel(Level.ALL);
    }

    @Override
    public String encode(Message m) throws EncodeException {
        try {
            return gson.toJson(m);
        } catch (Throwable t) {
            throw new EncodeException(m, "json encoding error", t);
        }
    }

    @Override
    public void init(EndpointConfig ec) {
        LOG.fine("created encoder");
    }

    @Override
    public void destroy() {
        LOG.fine("destroyed encoder");
    }

}
