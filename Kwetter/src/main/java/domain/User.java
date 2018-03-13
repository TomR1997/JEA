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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Tomt
 */

@Entity(name="KWETTER_USER")
@Table(name="KWETTER_USER")
@NamedQueries({
@NamedQuery(name = "User.allUsers", query = "SELECT u FROM KWETTER_USER u"),
@NamedQuery(name = "User.getFollowingCount", query = "SELECT COUNT(u.id) FROM KWETTER_USER u " + "LEFT JOIN u.following f "
        + "WHERE u.followers = :followers"),
@NamedQuery(name = "User.getFollowerCount", query = "SELECT COUNT(u.id) FROM KWETTER_USER u " + "LEFT JOIN u.followers f "
        + "WHERE u.following = :following")
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String location;
    private String bio;
    private String name;
    
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
    private String web;
    
    @ManyToMany
    @JoinTable(name ="follow")
    private List<User> following;
    
    @ManyToMany(mappedBy = "following")
    @JoinTable(name ="follow")
    private List<User> followers;

    @OneToMany(mappedBy="owner")
    private List<Post> posts;
    
    @ManyToMany(mappedBy = "likedBy")
    @JoinTable(name ="likedby")
    private List<Post> likedPosts;

    public User(String username, String location, String bio, String name, Role role, String web, List<User> following, List<User> followedBy, List<Post> posts) {
        this.username = username;
        this.location = location;
        this.bio = bio;
        this.name = name;
        this.role = role;
        this.web = web;
        this.following = following;
        this.followers = followedBy;
        this.posts = posts;
    }
    
        public User(String username, String location, String bio, String name, Role role, String web, List<User> following, List<User> followedBy, List<Post> posts, List<Post> likedPosts) {
        this.username = username;
        this.location = location;
        this.bio = bio;
        this.name = name;
        this.role = role;
        this.web = web;
        this.following = following;
        this.followers = followedBy;
        this.posts = posts;
        this.likedPosts = likedPosts;
    }
        
    public User(String username, String location, String bio, String name, Role role, String web) {
        this.username = username;
        this.location = location;
        this.bio = bio;
        this.name = name;
        this.role = role;
        this.web = web;
    }

    public User(String username, String location, String bio, String name, String web) {
        this.username = username;
        this.location = location;
        this.bio = bio;
        this.name = name;
        this.web = web;
    }

    public List<Post> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(List<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }
    
    
    
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

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followedBy) {
        this.followers = followedBy;
    }
   
}
