/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api.dto;

import domain.Post;
import java.util.Date;
/**
 *
 * @author Tomt
 */
public class PostDTO {

    private Long id;
    private String message;
    private Date messageSent;
    private UserDTO owner;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.message = post.getMessage();
        this.messageSent = post.getMessageSent();
        this.owner = new UserDTO(post.getOwner());
    }
     
}
