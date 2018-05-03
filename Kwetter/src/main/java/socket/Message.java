/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.Serializable;

/**
 *
 * @author Tomt
 */
public class Message implements Serializable {
    private String text;
    
    public Message(){}

    public Message(String text) {
        this.text=text;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
