/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import domain.Post;
import domain.User;
import java.util.Date;
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
public class PostDAOTest {

    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction tx;
    private PostDAO postDao;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("kwettertestpu");
        em = emf.createEntityManager();
        tx = em.getTransaction();
        postDao = new PostDAO(em);
    }

    @Test
    public void findPostTest() throws NonExistingEntryException {
        tx.begin();
        Post p = new Post();
        p.setId(1L);
        postDao.save(p);
        tx.commit();
        Post post = postDao.find(1L);
        assertSame(post, postDao.find(1L));
    }

    @Test
    public void createPostTest() throws NonExistingEntryException, EmptyListException {
        tx.begin();
        int allPosts = postDao.getAll().size();
        Post post = new Post("Hey all!", new Date());
        postDao.save(post);
        tx.commit();
        assertEquals(allPosts + 1, postDao.getAll().size());
    }

    @Test(expected = NonExistingEntryException.class)
    public void nonExistingPostExceptionTest() throws NonExistingEntryException {
        tx.begin();
        postDao.find(-1L);
    }
    
    @Test
    public void getLatestPostsTest() throws EmptyListException, NonExistingEntryException{
        tx.begin();
        Post p = new Post("a", new Date(), new User());
        postDao.save(p);
        tx.commit();
        assertTrue(!postDao.getLatestPosts(1L).isEmpty());
    }
    

}
