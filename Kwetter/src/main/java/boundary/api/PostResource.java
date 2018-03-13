/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api;

import boundary.api.dto.PostDTO;
import boundary.api.response.CreateResponse;
import boundary.api.response.DeleteResponse;
import boundary.api.response.GetMultipleResponse;
import boundary.api.response.GetSingleResponse;
import com.google.gson.Gson;
import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import domain.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import service.PostService;
import service.exceptions.InvalidIdException;
import service.exceptions.InvalidNameException;
import service.exceptions.LikePostException;
import service.exceptions.NonExistingPostException;
import service.exceptions.NonExistingUserException;

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
        GetSingleResponse<PostDTO> response = new GetSingleResponse<>(false);
        try {
            response.setRecord(new PostDTO(postService.findPost(id)));
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
        GetMultipleResponse<PostDTO> response = new GetMultipleResponse<>(false);
        List<PostDTO> posts = new ArrayList<>();
        try {
            for(int i = 0; i < postService.getAll().size(); i++){
                posts.add(new PostDTO(postService.getAll().get(i)));
            }
            response.setRecords(posts);
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
        GetMultipleResponse<PostDTO> response = new GetMultipleResponse<>(false);
        List<PostDTO> latestPosts = new ArrayList<>();
        try {
            for(int i = 0; i < postService.getLatestPosts(userId).size(); i++){
                latestPosts.add(new PostDTO(postService.getLatestPosts(userId).get(i)));
            }
            response.setRecords(latestPosts);
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen posts gevonden.");
        }
        return new Gson().toJson(response);
    }

    @GET
    @Path("getTimeline/{userId}")
    public String getTimeline(@PathParam("userId") Long userId) {
        GetMultipleResponse<PostDTO> response = new GetMultipleResponse<>(false);
        List<PostDTO> posts = new ArrayList<>();
        try {
            for(int i = 0; i < postService.getTimeline(userId).size(); i++){
                posts.add(new PostDTO(postService.getTimeline(userId).get(i)));
            }
            response.setRecords(posts);
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen posts gevonden.");
        }
        return new Gson().toJson(response);
    }

    @GET
    @Path("findPosts/{tags}")
    public String findPosts(@PathParam("tags") String tags) {
        GetMultipleResponse<PostDTO> response = new GetMultipleResponse<>(false);
        List<PostDTO> posts = new ArrayList<>();
        try {
            for(int i = 0; i < postService.findPost(tags).size(); i++){
                posts.add(new PostDTO(postService.findPost(tags).get(i)));
            }
            response.setRecords(posts);
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen posts gevonden.");
        } catch (InvalidNameException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
        }
        
        return new Gson().toJson(response);
    }
    
    @PUT
    @Path("likePost/{postId}/{userId}")
    public String likePost(@PathParam("postId") Long postId, @PathParam("userId")Long userId){
        CreateResponse<Long> response = new CreateResponse<>(false);
        try {
            postService.likePost(postId, userId);
            response.setSuccess(true);
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
        } catch (NonExistingUserException ex) {
            response.addMessage("Opgegeven user bestaat niet.");
        } catch (NonExistingPostException ex) {
            response.addMessage("Opgegeven post bestaat niet.");
        } catch (LikePostException ex) {
            response.addMessage("U heeft de post al geliked.");
        }
        return new Gson().toJson(response);
    }
    
    @PUT
    @Path("unlikePost/{postId}/{userId}")
    public String unlikePost(@PathParam("postId") Long postId, @PathParam("userId")Long userId){
        CreateResponse<Long> response = new CreateResponse<>(false);
        try {
            postService.unlikePost(postId, userId);
            response.setSuccess(true);
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
        } catch (NonExistingUserException ex) {
            response.addMessage("Opgegeven user bestaat niet.");
        } catch (NonExistingPostException ex) {
            response.addMessage("Opgegeven post bestaat niet.");
        } catch (LikePostException ex) {
            response.addMessage("U heeft deze post nog niet geliked.");
        }
        return new Gson().toJson(response);
    } 
}
