/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.PostDAO;
import dao.RoleDAO;
import dao.UserDAO;
import dao.exceptions.NonExistingEntryException;
import domain.Post;
import domain.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tomt
 */
public class DaoTest {

    private EntityManager em;
    private EntityManagerFactory emf;
    private UserDAO userDao;
    private PostDAO postDao;
    private RoleDAO roleDao;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("KwetterPU");
        em = emf.createEntityManager();
        userDao = new UserDAO(em);
        postDao = new PostDAO(em);
        roleDao = new RoleDAO(em);
    }

    @Test
    public void userTest() throws NonExistingEntryException {
        em.getTransaction().begin();
        User user = userDao.getAll().get(0);
        em.close();
        Assert.assertSame(user, userDao.getAll().get(0));
    }

    @Test(expected = NonExistingEntryException.class)
    public void nonExistingUserExceptionTest() throws NonExistingEntryException {
        em.getTransaction().begin();
        userDao.find(-1L);
    }

    @Test
    public void postTest() throws NonExistingEntryException {
        em.getTransaction().begin();
        Post post = postDao.find(1L);
        em.close();
        Assert.assertSame(post, postDao.find(1L));
    }

    @Test(expected = NonExistingEntryException.class)
    public void nonExistingPostExceptionTest() throws NonExistingEntryException {
        em.getTransaction().begin();
        postDao.find(-1L);
    }
}
