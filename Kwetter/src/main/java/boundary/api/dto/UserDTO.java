/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api.dto;

import domain.User;

/**
 *
 * @author Tomt
 */
public class UserDTO {
    
    private Long id;
    private String username;
    private String location;
    private String bio;
    private String name;
    private String role;
    private String web;
    private String following;
    private String followers;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.location = user.getLocation();
        this.bio = user.getBio();
        this.name = user.getName();
        this.role = user.getRole().getName();
        this.web = user.getWeb();
        this.following = "users/following/" + user.getId();
        this.followers = "users/followers/" + user.getId();
    }
    
    
}
