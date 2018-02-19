/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomt
 */
@Stateless
public class UserDAO {
    @PersistenceContext
    private EntityManager em;
    
    public void save(User user){
        em.persist(user);
    }
    
    public User find (Long id){
        return em.find(User.class, id);
    }
    
}
