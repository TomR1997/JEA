/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api;

import boundary.api.dto.UserDTO;
import boundary.api.response.GetMultipleResponse;
import boundary.api.response.GetSingleResponse;
import boundary.api.response.UpdateResponse;
import com.google.gson.GsonBuilder;
import dao.exceptions.EmptyListException;
import domain.User;
import filter.JWTTokenNeeded;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.UserService;
import service.exceptions.FollowingException;
import service.exceptions.InvalidAmountException;
import service.exceptions.InvalidIdException;
import service.exceptions.InvalidNameException;
import service.exceptions.LoginException;
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
    private final GsonBuilder gson = new GsonBuilder().serializeNulls();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response findUser(@PathParam("id") Long id) {
        GetSingleResponse<UserDTO> response = new GetSingleResponse<>();
        try {
            response.setRecord(new UserDTO(userService.findUser(id)));
            response.setSuccess(true);
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }

        return Response.ok(gson.create().toJson(response)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        GetMultipleResponse<UserDTO> response = new GetMultipleResponse<>();
        List<UserDTO> users = new ArrayList<>();
        try {
            for(int i = 0; i < userService.getAll().size(); i++){
                users.add(new UserDTO(userService.getAll().get(i)));
            }
            response.setRecords(users);
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen gebruikers gevonden.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }

        return Response.ok(gson.create().toJson(response)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("follow/{userId}/{followingId}")
    public Response followUser(@PathParam("userId") Long userId, @PathParam("followingId") Long followingId) {
        UpdateResponse<Long> response = new UpdateResponse<>();
        try {
            userService.followUser(userId, followingId);
            response.setSuccess(true);
        } catch (FollowingException ex) {
            response.addMessage("U volgt deze gebruiker al.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }

        return Response.ok(gson.create().toJson(response)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("unfollow/{userId}/{unfollowId}")
    public Response unfollowUser(@PathParam("userId") Long userId, @PathParam("unfollowId") Long unfollowId) {
        UpdateResponse<User> response = new UpdateResponse<>();
        try {
            userService.unfollowUser(userId, unfollowId);
            response.setSuccess(true);
        } catch (UnfollowingException ex) {
            response.addMessage("U volgt deze gebruiker niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }

        return Response.ok(gson.create().toJson(response)).build();
    }

    @PUT
    @JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    @Path("changeUsername/{id}/{newName}")
    public Response changeUsername(@PathParam("id") Long id, @PathParam("newName") String newName) {
        UpdateResponse<User> response = new UpdateResponse<>();
        try {
            userService.changeUsername(id, newName);
            response.setSuccess(true);
        } catch (InvalidIdException ex) {
            response.addMessage("Het opgegeven id is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (InvalidNameException ex) {
            response.addMessage("De opgegeven naam is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }

        return Response.ok(gson.create().toJson(response)).build();
    }

    @PUT
    @JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    @Path("changeBio/{id}/{newBio}")
    public Response changeBio(@PathParam("id") Long id, @PathParam("newBio") String newBio) {
        UpdateResponse<User> response = new UpdateResponse<>();
        try {
            userService.changeBio(id, newBio);
            response.setSuccess(true);
        } catch (InvalidIdException ex) {
            response.addMessage("Het opgegeven id is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (InvalidNameException ex) {
            response.addMessage("De opgegeven bio is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }

        return Response.ok(gson.create().toJson(response)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("followers/{id}")
    public Response getFollowers(@PathParam("id") Long id) {
        GetMultipleResponse<UserDTO> response = new GetMultipleResponse<>();
        List<UserDTO> followers = new ArrayList<>();
        try {
            for(int i = 0; i < userService.getFollowers(id).size(); i++){
                followers.add(new UserDTO(userService.getFollowers(id).get(i)));
            }
            response.setRecords(followers);
            response.setSuccess(true);
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (EmptyListException ex) {
            response.addMessage("U heeft geen volgers.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }

        return Response.ok(gson.create().toJson(response)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("following/{id}")
    public Response getFollowing(@PathParam("id") Long id) {
        GetMultipleResponse<UserDTO> response = new GetMultipleResponse<>();
        List<UserDTO> following = new ArrayList<>();
        try {
            for(int i = 0; i < userService.getFollowing(id).size(); i++){
                following.add(new UserDTO(userService.getFollowing(id).get(i)));
            }
            response.setRecords(following);
            response.setSuccess(true);
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (EmptyListException ex) {
            response.addMessage("U heeft geen volgers.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }

        return Response.ok(gson.create().toJson(response)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("followeramount/{id}")
    public Response getFollowerAmount(@PathParam("id") Long id) {
        GetSingleResponse<Integer> response = new GetSingleResponse<>();
        try {
            response.setRecord(userService.getFollowerAmount(id));
            response.setSuccess(true);
        } catch (InvalidAmountException ex) {
            response.addMessage("U heeft geen volgers.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }

        return Response.ok(gson.create().toJson(response)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("followingamount/{id}")
    public Response getFollowingAmount(@PathParam("id") Long id) {
        GetSingleResponse<Integer> response = new GetSingleResponse<>();
        try {
            response.setRecord(userService.getFollowingAmount(id));
            response.setSuccess(true);
        } catch (InvalidAmountException ex) {
            response.addMessage("U volgt niemand.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }

        return Response.ok(gson.create().toJson(response)).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("login/{username}/{password}")
    public Response login(@PathParam("username") String name, @PathParam("password") String password){
        GetSingleResponse<String> response = new GetSingleResponse<>();
        try{
            if (userService.login(name, password)){
                response.setRecord(userService.createToken(name));
            }
            response.setSuccess(true);
        } catch (LoginException ex){
            response.addMessage("Ongeldige login gegevens.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (InvalidNameException ex){
            response.addMessage("Gebruikersnaam is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (UnsupportedEncodingException | NonExistingUserException ex) {
            response.addMessage(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }
        
        return Response.ok(gson.create().toJson(response)).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("name/{username}")
    public Response find(@PathParam("username") String username) {
        GetSingleResponse<UserDTO> response = new GetSingleResponse<>();
        try {
            response.setRecord(new UserDTO(userService.find(username)));
            response.setSuccess(true);
        } catch (NonExistingUserException ex) {
            response.addMessage("De gebruiker bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }

        return Response.ok(gson.create().toJson(response)).build();
    }
}
