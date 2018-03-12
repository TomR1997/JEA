/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonExistingEntryException;
import domain.Role;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomt
 */
@Stateless
public class RoleDAO {

    @PersistenceContext
    private EntityManager em;

    public RoleDAO(){
    }
    
    public RoleDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Role role) throws NonExistingEntryException {
        em.persist(role);
    }

    public void update(Role role) throws NonExistingEntryException {
        if (role == null) {
            throw new NonExistingEntryException();
        }
        em.merge(role);
    }

    public Role find(String name) throws NonExistingEntryException {
        Role role = em.find(Role.class, name);
        if (role == null) {
            throw new NonExistingEntryException();
        }
        return role;
    }
}
