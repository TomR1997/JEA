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
import com.google.gson.GsonBuilder;
import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import filter.JWTTokenNeeded;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    private final GsonBuilder gson = new GsonBuilder().serializeNulls();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response findPost(@PathParam("id") Long id) {
        GetSingleResponse<PostDTO> response = new GetSingleResponse<>();
        try {
            response.setRecord(new PostDTO(postService.findPost(id)));
            response.setSuccess(true);
        } catch (NonExistingPostException ex) {
            response.addMessage("De post bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }

        return Response.ok(gson.create().toJson(response)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPosts() {
        GetMultipleResponse<PostDTO> response = new GetMultipleResponse<>();
        List<PostDTO> posts = new ArrayList<>();
        try {
            for(int i = 0; i < postService.getAll().size(); i++){
                posts.add(new PostDTO(postService.getAll().get(i)));
            }
            response.setRecords(posts);
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen posts gevonden.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }
        return Response.ok(gson.create().toJson(response)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public Response deletePost(@PathParam("id") Long id) {
        DeleteResponse<Long> response = new DeleteResponse<>();
        try {
            postService.deletePost(id);
            response.setSuccess(true);
        } catch (NonExistingEntryException ex) {
            response.addMessage("Post bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }

        return Response.ok(gson.create().toJson(response)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("latest/{userId}")
    public Response getLatestPosts(@PathParam("userId") Long userId) {
        GetMultipleResponse<PostDTO> response = new GetMultipleResponse<>();
        List<PostDTO> latestPosts = new ArrayList<>();
        try {
            for(int i = 0; i < postService.getLatestPosts(userId).size(); i++){
                latestPosts.add(new PostDTO(postService.getLatestPosts(userId).get(i)));
            }
            response.setRecords(latestPosts);
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen posts gevonden.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }
        return Response.ok(gson.create().toJson(response)).build();
    }

    @GET
    @JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    @Path("timeline/{userId}")
    public Response getTimeline(@PathParam("userId") Long userId) {
        GetMultipleResponse<PostDTO> response = new GetMultipleResponse<>();
        List<PostDTO> posts = new ArrayList<>();
        try {
            for(int i = 0; i < postService.getTimeline(userId).size(); i++){
                posts.add(new PostDTO(postService.getTimeline(userId).get(i)));
            }
            response.setRecords(posts);
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen posts gevonden.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }
         return Response.ok(gson.create().toJson(response)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("find/{tags}")
    public Response findPosts(@PathParam("tags") String tags) {
        GetMultipleResponse<PostDTO> response = new GetMultipleResponse<>();
        List<PostDTO> posts = new ArrayList<>();
        try {
            for(int i = 0; i < postService.findPosts(tags).size(); i++){
                posts.add(new PostDTO(postService.findPosts(tags).get(i)));
            }
            response.setRecords(posts);
            response.setSuccess(true);
        } catch (EmptyListException ex) {
            response.addMessage("Er zijn geen posts gevonden.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (InvalidNameException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }
        
         return Response.ok(gson.create().toJson(response)).build();
    }
    
    @PUT
    @JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    @Path("like/{postId}/{userId}")
    public Response likePost(@PathParam("postId") Long postId, @PathParam("userId")Long userId){
        CreateResponse<Long> response = new CreateResponse<>();
        try {
            postService.likePost(postId, userId);
            response.setSuccess(true);
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (NonExistingUserException ex) {
            response.addMessage("Opgegeven user bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (NonExistingPostException ex) {
            response.addMessage("Opgegeven post bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (LikePostException ex) {
            response.addMessage("U heeft de post al geliked.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }
         return Response.ok(gson.create().toJson(response)).build();
    }
    
    @PUT
    @JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    @Path("unlike/{postId}/{userId}")
    public Response unlikePost(@PathParam("postId") Long postId, @PathParam("userId")Long userId){
        CreateResponse<Long> response = new CreateResponse<>();
        try {
            postService.unlikePost(postId, userId);
            response.setSuccess(true);
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (NonExistingUserException ex) {
            response.addMessage("Opgegeven user bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (NonExistingPostException ex) {
            response.addMessage("Opgegeven post bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (LikePostException ex) {
            response.addMessage("U heeft deze post nog niet geliked.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }
         return Response.ok(gson.create().toJson(response)).build();
    } 
    
    @POST
    //@JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    @Path("create/{userId}/{content}")
    public Response createPost(@PathParam("userId") Long userId, @PathParam("content")String content){
        CreateResponse<Long> response = new CreateResponse<>();
        try {
            postService.createPost(userId, content);
            response.setSuccess(true);
        } catch (InvalidIdException ex) {
            response.addMessage("Opgegeven id is ongeldig.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (NonExistingUserException ex) {
            response.addMessage("Opgegeven user bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        } catch (NonExistingPostException ex) {
            response.addMessage("Opgegeven post bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }
         return Response.ok(gson.create().toJson(response)).build();
    }
}
