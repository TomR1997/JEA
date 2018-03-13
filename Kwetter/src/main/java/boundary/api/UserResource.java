/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api;

import boundary.api.dto.UserDTO;
import boundary.api.response.CreateResponse;
import boundary.api.response.GetMultipleResponse;
import boundary.api.response.GetSingleResponse;
import boundary.api.response.ResponseBase;
import boundary.api.response.UpdateResponse;
import com.google.gson.Gson;
import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import domain.User;
import java.util.ArrayList;
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
import service.exceptions.InvalidAmountException;
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
        GetSingleResponse<UserDTO> response = new GetSingleResponse<>(false);
        try {
            response.setRecord(new UserDTO(userService.findUser(id)));
            response.setSuccess(true);
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
        }

        return new Gson().toJson(response);
    }

    @GET
    @Path("getAllUsers")
    public String getAllUsers() {
        GetMultipleResponse<UserDTO> response = new GetMultipleResponse<>(false);
        List<UserDTO> users = new ArrayList<>();
        try {
            for(int i = 0; i < userService.getAll().size(); i++){
                users.add(new UserDTO(userService.getAll().get(i)));
            }
            response.setRecords(users);
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen gebruikers gevonden.");
        }

        return new Gson().toJson(response);
    }

    @POST
    @Path("saveUser")
    public String saveUser(User user) {
        CreateResponse<UserDTO> response = new CreateResponse<>(false);
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

    /*@PUT
    @Path("followUser/{user}/{follow}")
    public String followUser(@PathParam("user") User user, @PathParam("follow") User follow) {
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
    @Path("unfollowUser/{user}/{unfollow}")
    public String unfollowUser(@PathParam("user") User user, @PathParam("unfollow") User unfollow) {
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
    }*/

    @PUT
    @Path("changeUsername/{id}/{newName}")
    public String changeUsername(@PathParam("id") Long id, @PathParam("newName") String newName) {
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
    @Path("changeBio/{id}/{newBio}")
    public String changeBio(@PathParam("id") Long id, @PathParam("newBio") String newBio) {
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

    @GET
    @Path("getFollowers/{id}")
    public String getFollowers(@PathParam("id") Long id) {
        GetMultipleResponse<UserDTO> response = new GetMultipleResponse<>(false);
        List<UserDTO> followers = new ArrayList<>();
        try {
            for(int i = 0; i < userService.getFollowers(id).size(); i++){
                followers.add(new UserDTO(userService.getFollowers(id).get(i)));
            }
            response.setRecords(followers);
            response.setSuccess(true);
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
        } catch (EmptyListException ex) {
            response.addMessage("U heeft geen volgers.");
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
        }

        return new Gson().toJson(response);
    }

    @GET
    @Path("getFollowing/{id}")
    public String getFollowing(@PathParam("id") Long id) {
        GetMultipleResponse<UserDTO> response = new GetMultipleResponse<>(false);
        List<UserDTO> following = new ArrayList<>();
        try {
            for(int i = 0; i < userService.getFollowing(id).size(); i++){
                following.add(new UserDTO(userService.getFollowing(id).get(i)));
            }
            response.setRecords(following);
            response.setSuccess(true);
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
        } catch (EmptyListException ex) {
            response.addMessage("U heeft geen volgers.");
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
        }

        return new Gson().toJson(response);
    }

    @GET
    @Path("getFollowerAmount/{id}")
    public String getFollowerAmount(@PathParam("id") Long id) {
        GetSingleResponse<Integer> response = new GetSingleResponse<>(false);
        try {
            response.setRecord(userService.getFollowerAmount(id));
            response.setSuccess(true);
        } catch (InvalidAmountException ex) {
            response.addMessage("U heeft geen volgers.");
        }

        return new Gson().toJson(response);
    }

    @GET
    @Path("getFollowingAmount/{id}")
    public String getFollowingAmount(@PathParam("id") Long id) {
        GetSingleResponse<Integer> response = new GetSingleResponse<>(false);
        try {
            response.setRecord(userService.getFollowingAmount(id));
            response.setSuccess(true);
        } catch (InvalidAmountException ex) {
            response.addMessage("U volgt niemand.");
        }

        return new Gson().toJson(response);
    }
}
