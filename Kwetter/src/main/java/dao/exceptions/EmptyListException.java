/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.exceptions;

/**
 *
 * @author Tomt
 */
public class EmptyListException extends Exception {

    public EmptyListException() {
    }

    public EmptyListException(String message) {
        super(message);
    }
}
