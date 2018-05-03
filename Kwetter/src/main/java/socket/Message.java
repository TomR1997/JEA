/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import boundary.api.dto.UserDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tomt
 */
public class Message implements Serializable {
    private Long id;
    private String message;
    private Date messageSent;
    private UserDTO owner;
    private List<UserDTO> likedBy;
    
    public Message(){}

    public Message(Long id, String message, Date messageSent, UserDTO owner, List<UserDTO> likedBy) {
        this.id = id;
        this.message = message;
        this.messageSent = messageSent;
        this.owner = owner;
        this.likedBy = likedBy;
    }

    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageSent() {
        return messageSent;
    }

    public void setMessageSent(Date messageSent) {
        this.messageSent = messageSent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public List<UserDTO> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<UserDTO> likedBy) {
        this.likedBy = likedBy;
    }

}
