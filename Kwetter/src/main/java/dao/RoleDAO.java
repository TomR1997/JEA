/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
    
    public void save(Role role){
        em.persist(role);
    }
    
    public Role find (String name){
        return em.find(Role.class, name);
    }
}
