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
public class InvalidIdException extends Exception {

    public InvalidIdException() {
    }

    public InvalidIdException(String message) {
        super(message);
    }

}
