/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.UserDAO;
import dao.exceptions.NonExistingEntryException;
import domain.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import service.exceptions.NonExistingUserException;

/**
 *
 * @author Tomt
 */
public class DaoTest {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    private UserDAO userDao;
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("KwetterPU");
        em = emf.createEntityManager();
        userDao = new UserDAO(em);
    }
    
    @Test
    public void userTest() throws NonExistingEntryException{
        em.getTransaction().begin();
        User user = userDao.getAll().get(0);
        em.close();
        
        Assert.assertEquals(user.getName(), userDao.getAll().get(0).getName());
    }
    
    @Test(expected = NonExistingUserException.class)
    public void nonExistingUserExceptionTest() throws NonExistingEntryException{
        em.getTransaction().begin();
        User user = userDao.find(-1L);
    }
}
