/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.PostDAO;
import dao.RoleDAO;
import dao.UserDAO;
import domain.Post;
import domain.Role;
import domain.RoleName;
import domain.User;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Tomt
 */

@Startup
@Singleton
public class Init {
    @Inject
    private UserDAO userDao;
    @Inject
    private RoleDAO roleDao;
    @Inject
    private PostDAO postDao;

    @PostConstruct
    public void init() {
        System.out.println("Init begin......................");
        Role roleUser = new Role();
        Role roleModerator = new Role();
        
        roleUser.setName(RoleName.MODERATOR.toString());
        roleModerator.setName(RoleName.USER.toString());
        
        roleDao.save(roleUser);
        roleDao.save(roleModerator);           
        
        User user1 = new User("programmeergod", "Veldhoven", "somebio", "Tom Roelofs", roleUser, "someweb");
        User user2 = new User("wiekentmiljonair", "Veldhoven", "somebio", "Rom Toelofs", roleUser, "someweb");
        User user3 = new User("OneManLeft", "Eindhoven", "somebio", "Oliver Queen", roleUser, "someweb");
        User user4 = new User("PickleRick", "Eindhoven", "somebio", "Rick Sanchez", roleUser, "someweb");
        User user5 = new User("Mortymer", "Eindhoven", "somebio", "Morty", roleUser, "someweb");
        User user6 = new User("Devil666", "Veldhoven", "somebio", "Teemo", roleUser, "someweb");
        User user7 = new User("Shovel", "Waalre", "somebio", "Robert", roleUser, "someweb");
        User user8 = new User("UseCaseDiagram", "Veldhoven", "somebio", "Sjaak Afhaak", roleUser, "someweb");
        User user9 = new User("KochFractaller", "Eindhoven", "somebio", "Tomek Koch", roleUser, "someweb");
        User user10 = new User("ThreadFred", "Veldhoven", "somebio", "Fred Vred", roleUser, "someweb");
        
        userDao.save(user1);
        userDao.save(user2);
        userDao.save(user3);
        userDao.save(user4);
        userDao.save(user5);
        userDao.save(user6);
        userDao.save(user7);
        userDao.save(user8);
        userDao.save(user9);
        userDao.save(user10);
        
        Post post = new Post("Hey all!", new Date(), user1);
        postDao.save(post);
        
        System.out.println("Init done.......................");
    }
}
