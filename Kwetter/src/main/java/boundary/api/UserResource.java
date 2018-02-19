/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api;

import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import service.UserService;

/**
 *
 * @author Tomt
 */
@Path("users")
@Stateless
public class UserResource {
    @Inject
    private UserService userService;
    
    @GET
    @Path("GetUser/{id}")
    public User getUser(@PathParam("id") Long id){
        return userService.findUser(id);
    }
    
    @GET
    public List<User> getAllUsers(){
        return userService.getAll();
    }
}
