/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import domain.Post;
import java.util.Date;
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
public class PostDAOTest {

    /*private EntityManager em;
    private EntityManagerFactory emf;
    private PostDAO postDao;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("KwetterPU");
        em = emf.createEntityManager();
        postDao = new PostDAO(em);
    }

    @Test
    public void findPostTest() throws NonExistingEntryException {
        em.getTransaction().begin();
        Post post = postDao.find(1L);
        assertSame(post, postDao.find(1L));
        em.close();
    }

    @Test
    public void createPostTest() throws NonExistingEntryException, EmptyListException {
        em.getTransaction().begin();
        int allPosts = postDao.getAll().size();
        Post post = new Post("Hey all!", new Date());
        postDao.save(post);
        assertEquals(allPosts + 1, postDao.getAll().size());
        em.close();
    }

    @Test(expected = NonExistingEntryException.class)
    public void nonExistingPostExceptionTest() throws NonExistingEntryException {
        em.getTransaction().begin();
        postDao.find(-1L);
        em.close();
    }
    
    @Test
    public void getLatestPostsTest() throws EmptyListException{
        em.getTransaction().begin();
        assertTrue(!postDao.getLatestPosts(1L).isEmpty());
        em.close();
    }
    
    @Test
    public void getTimelineTest() throws EmptyListException{
        em.getTransaction().begin();
        assertTrue(!postDao.getTimeline(1L).isEmpty());
        em.close();
    }*/

}
