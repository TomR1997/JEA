/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonExistingEntryException;
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

    public PostDAO(EntityManager em) {
        this.em = em;
    }
    
    public void save(Post post) throws NonExistingEntryException{
        if (post == null){
            throw new NonExistingEntryException();
        }
        em.persist(post);
    }
    
    public Post find(Long id) throws NonExistingEntryException{
        Post post = em.find(Post.class, id);
        if (post == null){
            throw new NonExistingEntryException();
        }
        return post;
    }
    
    public void delete(Long id) throws NonExistingEntryException{
        Post post = em.find(Post.class, id);
        if(post == null){
            throw new NonExistingEntryException();
        }      
        em.remove(post);
    }
}
