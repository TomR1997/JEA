/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api.dto;

import domain.Post;
import domain.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Tomt
 */
public class PostDTO {

    private Long id;
    private String message;
    private Date messageSent;
    private UserDTO owner;
    private List<UserDTO> likedBy;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.message = post.getMessage();
        this.messageSent = post.getMessageSent();
        this.owner = new UserDTO(post.getOwner());
        this.likedBy = parseUsers(post.getLikedBy());
    }
    
    private List<UserDTO> parseUsers(List<User> users){
        List<UserDTO> dtoUsers = new ArrayList<>();
        for(int i = 0; i < users.size(); i++){
            dtoUsers.add(new UserDTO(users.get(i)));
        }
        return dtoUsers;
    }
     
}
