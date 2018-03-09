/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import domain.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    private UserDAO userDao;
    private User user1;
    private User user2;

    public UserDAOTest() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("KwetterPU");
        em = emf.createEntityManager();
        userDao = new UserDAO(em);
        user1 = new User();
        user2 = new User();
    }

    @Test
    public void userGetAllTest() throws EmptyListException {
        em.getTransaction().begin();
        User user = userDao.getAll().get(0);
        assertSame(user, userDao.getAll().get(0));
        em.close();
    }

    @Test(expected = NonExistingEntryException.class)
    public void nonExistingUserExceptionTest() throws NonExistingEntryException {
        em.getTransaction().begin();
        userDao.find(-1L);
    }

    @Test
    public void createUserTest() throws EmptyListException {
        em.getTransaction().begin();
        int allUsers = userDao.getAll().size();
        User user = new User("programmeergod", "Veldhoven", "somebio", "Tom Roelofs", "someweb");
        userDao.save(user);
        assertEquals(allUsers + 1, userDao.getAll().size());
        em.close();
    }

    @Test
    public void findUserTest() throws NonExistingEntryException {
        em.getTransaction().begin();
        User user = userDao.find(1L);
        assertNotNull(user);
        em.close();
    }

    @Test
    public void followUserTest() throws NonExistingEntryException {
        User user1 = new User();
        User user2 = new User();
        em.getTransaction().begin();
        int following = userDao.getFollowingAmount(1000L);
        user2.setId(1000L);
        userDao.followUser(user1, user2);
        assertEquals(following + 1, userDao.getFollowingAmount(1000L));
        em.close();
    }

    @Test
    public void unfollowUserTest() throws NonExistingEntryException {
        em.getTransaction().begin();
        user2.setId(1000L);
        userDao.followUser(user1, user2);
        int following = userDao.getFollowingAmount(1000L);
        userDao.unfollowUser(user1, user2);
        assertEquals(following - 1, userDao.getFollowingAmount(1000L));
        em.close();
    }

    @Test
    public void changeUsernameTest() throws NonExistingEntryException {
        em.getTransaction().begin();
        userDao.changeUsername(1L, "newName");
        em.close();
        assertEquals("newName", userDao.find(1L).getUsername());
    }

    @Test
    public void changeBioTest() throws NonExistingEntryException {
        em.getTransaction().begin();
        userDao.changeBio(1L, "newBio");
        em.close();
        assertEquals("newBio", userDao.find(1L).getBio());
    }

    @Test
    public void getFollowersTest() throws NonExistingEntryException, EmptyListException {
        em.getTransaction().begin();
        assertTrue(!userDao.getFollowers(1L).isEmpty());
        em.close();
    }

    @Test
    public void getFollowingTest() throws NonExistingEntryException, EmptyListException {
        em.getTransaction().begin();
        assertTrue(!userDao.getFollowing(1L).isEmpty());
        em.close();
    }

}
