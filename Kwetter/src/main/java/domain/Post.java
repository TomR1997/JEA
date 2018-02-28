/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Tomt
 */
@Entity
@Table(name="KWETTER_POST")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
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
