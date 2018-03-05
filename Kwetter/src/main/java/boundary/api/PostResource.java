/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api;

import domain.Post;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import service.PostService;

/**
 *
 * @author Tomt
 */
@Path("posts")
@Stateless
public class PostResource {
    @Inject
    private PostService postService;
    
    /*@GET
    @Path("GetPost/{id}")
    public Post getPost(@PathParam("id") Long id){
        return postService.findPost(id);
    }*/
}
