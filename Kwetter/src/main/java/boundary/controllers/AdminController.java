/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.controllers;

import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import domain.User;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.PostService;
import service.UserService;
import service.exceptions.InvalidIdException;

/**
 *
 * @author Tomt
 */
@Named
@SessionScoped
public class AdminController implements Serializable{
    
    @Inject
    private UserService userService;
    @Inject
    private PostService postService;
    
    private List<User> users;
    
    @PostConstruct
    public void init(){
        try {
            users = userService.getAll();
        } catch (EmptyListException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
