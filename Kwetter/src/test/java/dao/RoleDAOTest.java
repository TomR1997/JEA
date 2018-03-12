/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonExistingEntryException;
import domain.Role;
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
public class RoleDAOTest {

    /*private EntityManager em;
    private EntityManagerFactory emf;
    private RoleDAO roleDao;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("KwetterPU");
        em = emf.createEntityManager();
        roleDao = new RoleDAO(em);
    }

    @Test
    public void findRoleTest() throws NonExistingEntryException {
        em.getTransaction().begin();
        Role role = roleDao.find("Moderator");
        assertNotNull(role);
        em.close();
    }*/
}
