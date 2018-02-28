/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Post;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomt
 */
@Stateless
public class PostDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void save(Post post){
        em.persist(post);
    }
    
    public Post find (Long id){
        return em.find(Post.class, id);
    }
    
    public void delete(Long id){
        Post post = find(id);
        if(post == null){
            //throw exception
        }
        
        em.remove(post);
    }
}
