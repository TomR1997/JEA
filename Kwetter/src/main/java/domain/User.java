/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Tomt
 */

@Entity
@Table(name="KWETTER_USER")
@NamedQuery(name = "User.allUsers", query = "SELECT u FROM User u")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String location;
    private String bio;
    private String name;
    
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
    private String web;
    
    /*@ManyToMany(mappedBy = "follows")
    private List<User> follows;
    @ManyToMany(mappedBy = "followedBy")
    private List<User> followedBy;*/
    
    @OneToMany(mappedBy="owner")
    private List<Post> posts;
    
    //private String password;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    /*public List<User> getFollows() {
        return follows;
    }

    public void setFollows(List<User> follows) {
        this.follows = follows;
    }

    public List<User> getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(List<User> followedBy) {
        this.followedBy = followedBy;
    }*/
   
}
