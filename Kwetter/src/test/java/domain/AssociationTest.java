package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Post;
import domain.Role;
import domain.RoleName;
import domain.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tomt
 */
public class AssociationTest {
    private List<User> users = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();
    
    @Before
    public void setUp() {      
        createDummyData();
    }

    @Test
    public void userPostsTest(){
        Assert.assertTrue(!users.get(0).getPosts().isEmpty());
    }
    
    @Test
    public void userFollowingTest(){
        Assert.assertTrue(!users.get(0).getFollowing().isEmpty());
    }
      
    public void createDummyData(){
        createRoles();
        createUsers();
        createPosts();
        followEachother();
    }
    
    public void followEachother(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                follow(users.get(i), users.get(j));
            }
        }
    }
    
    public void follow(User user, User following){
        user.getFollowing().add(following);
    }
    
    public void createPosts(){
        for (int i = 0; i < 10; i++) {
            Post post = new Post("Hey all", new Date(), users.get(i));
            posts.add(post);
            users.get(i).getPosts().add(post);
        }
    }
    
    public void createRoles(){
        Role roleUser = new Role();
        Role roleModerator = new Role();                
        roleUser.setName(RoleName.MODERATOR.toString());
        roleModerator.setName(RoleName.USER.toString());
        roles.add(roleUser);
        roles.add(roleModerator);
    }
    public void createUsers(){
        for (int i = 0; i < 10; i++) {
            users.add(new User("user"+i, "Veldhoven", "Some Bio", "name"+i, roles.get(0), "Some Web", new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Post>()));
        }   
    }
}
