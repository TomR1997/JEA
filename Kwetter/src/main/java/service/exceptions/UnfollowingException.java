/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.exceptions;

/**
 *
 * @author Tomt
 */
public class UnfollowingException extends Exception {
    
    public UnfollowingException() {
    }

    public UnfollowingException(String message) {
        super(message);
    }
}
