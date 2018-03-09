/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api;

import boundary.api.response.CreateResponse;
import boundary.api.response.GetMultipleResponse;
import boundary.api.response.GetSingleResponse;
import boundary.api.response.ResponseBase;
import boundary.api.response.UpdateResponse;
import com.google.gson.Gson;
import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import domain.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import service.UserService;
import service.exceptions.FollowingException;
import service.exceptions.InvalidIdException;
import service.exceptions.InvalidNameException;
import service.exceptions.NonExistingUserException;
import service.exceptions.UnfollowingException;

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
    @Path("findUser/{id}")
    public String findUser(@PathParam("id") Long id) throws InvalidIdException {
        GetSingleResponse<User> response = new GetSingleResponse<>(false);
        try {
            response.Record = userService.findUser(id);
            response.setSuccess(true);
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
        }

        return new Gson().toJson(response);
    }

    @GET
    @Path("getAllUsers")
    public String getAllUsers() {
        GetMultipleResponse<User> response = new GetMultipleResponse<>(false);
        try {
            response.Records = userService.getAll();
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen gebruikers gevonden.");
        }

        return new Gson().toJson(response);
    }

    @POST
    @Path("saveUser")
    public String saveUser(User user) {
        CreateResponse<User> response = new CreateResponse<>(false);
        try {
            userService.save(user);
            response.setSuccess(true);
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker ontbreekt data.");
        } catch (InvalidIdException ex) {
            response.addMessage("De id is ongeldig.");
        } catch (InvalidNameException ex) {
            response.addMessage("De opgegeven naam is ongeldig.");
        }

        return new Gson().toJson(response);
    }

    @PUT
    @Path("followUser")
    public String followUser(User user, User follow) {
        UpdateResponse<User> response = new UpdateResponse<>(false);
        try {
            userService.followUser(user, follow);
            response.setSuccess(true);
        } catch (FollowingException ex) {
            response.addMessage("U volgt deze gebruiker al.");
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
        }

        return new Gson().toJson(response);
    }

    @PUT
    @Path("unfollowUser")
    public String unfollowUser(User user, User unfollow) {
        UpdateResponse<User> response = new UpdateResponse<>(false);
        try {
            userService.unfollowUser(user, unfollow);
            response.setSuccess(true);
        } catch (UnfollowingException ex) {
            response.addMessage("U volgt deze gebruiker niet.");
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
        }

        return new Gson().toJson(response);
    }

    @PUT
    @Path("changeUsername")
    public String changeUsername(Long id, String newName) {
        UpdateResponse<User> response = new UpdateResponse<>(false);
        try {
            userService.changeUsername(id, newName);
            response.setSuccess(true);
        } catch (InvalidIdException ex) {
            response.addMessage("Het opgegeven id is ongeldig.");
        } catch (InvalidNameException ex) {
            response.addMessage("De opgegeven naam is ongeldig.");
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
        }

        return new Gson().toJson(response);
    }

    @PUT
    @Path("changeBio")
    public String changeBio(Long id, String newBio) {
        UpdateResponse<User> response = new UpdateResponse<>(false);
        try {
            userService.changeBio(id, newBio);
            response.setSuccess(true);
        } catch (InvalidIdException ex) {
            response.addMessage("Het opgegeven id is ongeldig.");
        } catch (InvalidNameException ex) {
            response.addMessage("De opgegeven bio is ongeldig.");
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
        }

        return new Gson().toJson(response);
    }
}
