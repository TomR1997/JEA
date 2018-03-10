/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api;

import boundary.api.response.DeleteResponse;
import boundary.api.response.GetMultipleResponse;
import boundary.api.response.GetSingleResponse;
import com.google.gson.Gson;
import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import domain.Post;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import service.PostService;
import service.exceptions.InvalidIdException;
import service.exceptions.InvalidNameException;
import service.exceptions.NonExistingPostException;

/**
 *
 * @author Tomt
 */
@Path("posts")
@Stateless
public class PostResource {

    @Inject
    private PostService postService;

    @GET
    @Path("findPost/{id}")
    public String findPost(@PathParam("id") Long id) {
        GetSingleResponse<Post> response = new GetSingleResponse<>(false);
        try {
            response.setRecord(postService.findPost(id));
            response.setSuccess(true);
        } catch (NonExistingPostException ex) {
            response.addMessage("De post bestaat niet.");
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
        }

        return new Gson().toJson(response);
    }

    @GET
    @Path("getAllPosts")
    public String getAllPosts() {
        GetMultipleResponse<Post> response = new GetMultipleResponse<>(false);
        try {
            response.setRecords(postService.getAll());
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen posts gevonden.");
        }
        return new Gson().toJson(response);
    }

    @DELETE
    @Path("deletePost/{id}")
    public String deletePost(@PathParam("id") Long id) {
        DeleteResponse<Long> response = new DeleteResponse<>(false);
        try {
            postService.deletePost(id);
            response.setSuccess(true);
        } catch (NonExistingEntryException ex) {
            response.addMessage("Post bestaat niet.");
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
        }

        return new Gson().toJson(response);
    }

    @GET
    @Path("getLatestPosts/{userId}")
    public String getLatestPosts(@PathParam("userId") Long userId) {
        GetMultipleResponse<Post> response = new GetMultipleResponse<>(false);
        try {
            response.setRecords(postService.getLatestPosts(userId));
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen posts gevonden.");
        }
        return new Gson().toJson(response);
    }

    @GET
    @Path("getTimeline/{userId}")
    public String getTimeline(@PathParam("userId") Long userId) {
        GetMultipleResponse<Post> response = new GetMultipleResponse<>(false);
        try {
            response.setRecords(postService.getTimeline(userId));
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen posts gevonden.");
        }
        return new Gson().toJson(response);
    }

    @GET
    @Path("findPosts/{tags}")
    public String findPosts(@PathParam("tags") String tags) {
        GetMultipleResponse<Post> response = new GetMultipleResponse<>(false);
        try {
            response.setRecords(postService.findPost(tags));
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen posts gevonden.");
        } catch (InvalidNameException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
        }
        response.setSuccess(true);
        return new Gson().toJson(response);
    }
}
