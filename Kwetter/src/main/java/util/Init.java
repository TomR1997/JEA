/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.PostDAO;
import dao.RoleDAO;
import dao.UserDAO;
import dao.exceptions.NonExistingEntryException;
import domain.Post;
import domain.Role;
import domain.RoleName;
import domain.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

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
        User user1 = new User("programmeergod", "Veldhoven", "somebio", "Tom Roelofs", "someweb");
        userDao.save(user1);
        /*Role roleUser = new Role();
        Role roleModerator = new Role();

        roleUser.setName(RoleName.USER.toString());
        roleModerator.setName(RoleName.MODERATOR.toString());

        try {
            roleDao.save(roleUser);
            roleDao.save(roleModerator);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        User user1 = new User("programmeergod", "Veldhoven", "somebio", "Tom Roelofs", roleModerator, "someweb");
        User user2 = new User("wiekentmiljonair", "Veldhoven", "somebio", "Rom Toelofs", roleModerator, "someweb");
        User user3 = new User("OneManLeft", "Eindhoven", "somebio", "Oliver Queen", roleUser, "someweb");
        User user4 = new User("PickleRick", "Eindhoven", "somebio", "Rick Sanchez", roleUser, "someweb");
        User user5 = new User("Mortymer", "Eindhoven", "somebio", "Morty", roleUser, "someweb");
        User user6 = new User("Devil666", "Veldhoven", "somebio", "Teemo", roleUser, "someweb");
        User user7 = new User("Shovel", "Waalre", "somebio", "Robert", roleUser, "someweb");
        User user8 = new User("UseCaseDiagram", "Veldhoven", "somebio", "Sjaak Afhaak", roleUser, "someweb");
        User user9 = new User("KochFractaller", "Eindhoven", "somebio", "Tomek Koch", roleUser, "someweb");
        User user10 = new User("ThreadFred", "Veldhoven", "somebio", "Fred Vred", roleUser, "someweb");

        try {
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
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            userDao.followUser(user1, user2);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
        }


        Post post1 = new Post("First post PagChomp", new Date(), user1);
        Post post2 = new Post("Hey all!", new Date(), user2);
        Post post3 = new Post("Hey all!", new Date(), user3);
        Post post4 = new Post("Hey all!", new Date(), user4);
        Post post5 = new Post("Hey all!", new Date(), user5);
        Post post6 = new Post("Hey all!", new Date(), user6);
        Post post7 = new Post("Hey all!", new Date(), user7);
        Post post8 = new Post("Hey all!", new Date(), user8);
        Post post9 = new Post("Hey all!", new Date(), user9);
        Post post10 = new Post("Hey all!", new Date(), user10);
        try {
            postDao.save(post1);
            postDao.save(post2);
            postDao.save(post3);
            postDao.save(post4);
            postDao.save(post5);
            postDao.save(post6);
            postDao.save(post7);
            postDao.save(post8);
            postDao.save(post9);
            postDao.save(post10);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
