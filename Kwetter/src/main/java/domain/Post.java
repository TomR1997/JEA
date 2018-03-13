/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Tomt
 */
@Entity(name = "KWETTER_POST")
@Table(name="KWETTER_POST")
@NamedQueries({
@NamedQuery(name = "Post.allPosts", query = "SELECT p FROM KWETTER_POST p"),
@NamedQuery(name = "Post.getPostCountByOwner", query = "SELECT COUNT(p.id) " + "FROM KWETTER_POST p " 
        + "WHERE p.owner = :owner_id " + "ORDER BY p.messageSent DESC"),
@NamedQuery(name = "Post.getLatestPosts", query = "SELECT p " + "FROM KWETTER_POST p " + "WHERE p.owner.id = :owner_id "
        + "ORDER BY p.messageSent DESC"),
@NamedQuery(name = "Post.getTimeline", query = "SELECT p FROM KWETTER_POST p, KWETTER_USER u "
        + "WHERE p.owner.id = :owner_id OR (p.owner = u.followers AND u.following = :owner_id) ORDER BY p.messageSent DESC"),
@NamedQuery(name = "Post.findPosts", query = "SELECT p FROM KWETTER_POST p "
        + "WHERE p.message LIKE :tags OR p.owner.username LIKE :tags ")
})
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
    
    @Temporal(TemporalType.DATE)
    private Date messageSent;
    
    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;
    
    public Post() {
    }

    public Post(String message, Date messageSent, User owner) {
        this.message = message;
        this.messageSent = messageSent;
        this.owner = owner;
    }

    public Post(String message, Date date) {
        this.message = message;
        this.messageSent = date;
    }
    
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
}
