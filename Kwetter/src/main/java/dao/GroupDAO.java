/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonExistingEntryException;
import domain.Group;
import domain.Role;
import domain.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomt
 */
@Stateless
public class GroupDAO {

    @PersistenceContext
    private EntityManager em;

    public GroupDAO() {
    }

    public GroupDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Group group) {
        em.persist(group);
    }

    public void update(Group group) {
        em.merge(group);
    }
}
