/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import domain.Post;
import domain.Role;
import domain.RoleName;
import domain.User;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomt
 */
public class UserDAOTest {

    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction tx;
    private UserDAO userDao;
    private User user1;
    private User user2;

    public UserDAOTest() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("kwettertestpu");
        em = emf.createEntityManager();
        tx = em.getTransaction();
        userDao = new UserDAO(em);
        user1 = new User();
        user2 = new User();
    }

    @Test
    public void userGetAllTest() throws EmptyListException {
        tx.begin();
        userDao.save(user1);
        tx.commit();
        assertTrue(!userDao.getAll().isEmpty());
    }

    @Test(expected = NonExistingEntryException.class)
    public void nonExistingUserExceptionTest() throws NonExistingEntryException {
        tx.begin();
        userDao.find(-1L);
    }

    @Test
    public void createUserTest() throws EmptyListException {
        tx.begin();
        userDao.save(user1);
        int allUsers = userDao.getAll().size();
        userDao.save(user2);
        tx.commit();
        assertEquals(allUsers + 1, userDao.getAll().size());
    }

    @Test
    public void findUserTest() throws NonExistingEntryException {
        tx.begin();
        User user = userDao.find(1L);
        assertNotNull(user);
    }

    @Test
    public void followUserTest() throws NonExistingEntryException {
        User user1 = new User();
        User user2 = new User();
        tx.begin();
        int following = userDao.getFollowingAmount(1000L);
        user2.setId(1000L);
        userDao.followUser(user1, user2);
        tx.commit();
        assertEquals(following + 1, userDao.getFollowingAmount(1000L));
    }

    @Test
    public void unfollowUserTest() throws NonExistingEntryException {
        tx.begin();
        user2.setId(1000L);
        userDao.followUser(user1, user2);
        int following = userDao.getFollowingAmount(5L);
        userDao.unfollowUser(user1, user2);
        tx.commit();
        assertEquals(following - 1, userDao.getFollowingAmount(5L));
    }

    @Test
    public void changeUsernameTest() throws NonExistingEntryException {
        tx.begin();
        userDao.changeUsername(1L, "newName");
        tx.commit();
        assertEquals("newName", userDao.find(1L).getUsername());
    }

    @Test
    public void changeBioTest() throws NonExistingEntryException {
        tx.begin();
        user1.setId(1L);
        user1.setBio("bio");
        userDao.save(user1);
        tx.commit();
        userDao.changeBio(1L, "newBio");
        tx.commit();
        assertEquals("newBio", userDao.find(1L).getBio());
    }

    @Test
    public void getFollowersTest() throws NonExistingEntryException, EmptyListException {
        tx.begin();
        userDao.save(user1);
        tx.commit();
        assertTrue(!userDao.getFollowers(1L).isEmpty());
    }

    @Test
    public void getFollowingTest() throws NonExistingEntryException, EmptyListException {
        tx.begin();
        user1.setFollowing(new ArrayList<User>());
        user1.getFollowing().add(user2);
        userDao.save(user1);
        tx.commit();
        assertTrue(!userDao.getFollowing(1L).isEmpty());
    }

}
