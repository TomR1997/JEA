/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api;

import boundary.api.response.ResponseBase;
import domain.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import service.UserService;
import service.exceptions.InvalidIdException;
import service.exceptions.NonExistingUserException;

/**
 *
 * @author Tomt
 */
@Path("users")
@Stateless
public class UserResource {
    @Inject
    private UserService userService;
    
    /*@GET
    @Path("GetUser/{id}")
    public User getUser(@PathParam("id") Long id) throws InvalidIdException{
        try {
            return userService.findUser(id);
        } catch (NonExistingUserException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*@GET
    public List<User> getAllUsers(){
        try {
            return userService.getAll();
        } catch (NonExistingUserException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
