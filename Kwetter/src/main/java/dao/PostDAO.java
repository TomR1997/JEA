/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonExistingEntryException;
import domain.Post;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    public void save(Post post) throws NonExistingEntryException {
        if (post == null) {
            throw new NonExistingEntryException();
        }
        em.persist(post);
    }

    public List<Post> getAll() throws NonExistingEntryException {
        List<Post> posts = em.createNamedQuery("Post.allPosts").getResultList();
        if (posts.isEmpty()) {
            throw new NonExistingEntryException();
        }
        return posts;
    }

    public Post find(Long id) throws NonExistingEntryException {
        Post post = em.find(Post.class, id);
        if (post == null) {
            throw new NonExistingEntryException();
        }
        return post;
    }
    
    public Post find(String search){
        throw new NotImplementedException();
    }

    public void delete(Long id) throws NonExistingEntryException {
        Post post = em.find(Post.class, id);
        if (post == null) {
            throw new NonExistingEntryException();
        }
        em.remove(post);
    }
    
    public List<Post> getLatestPosts(User user) throws NonExistingEntryException{
        List<Post> posts = em.createNamedQuery("Post.latestPosts").getResultList();
        throw new NotImplementedException();
        /*if(posts == null){
            throw new NonExistingEntryException();
        }
        return posts;*/
    }
    
    public List<Post> getTrendingPosts(){
        List<Post> posts = em.createNamedQuery("Post.getTrendingPosts").getResultList();
        throw new NotImplementedException();
        /*if(posts == null){
            throw new NonExistingEntryException();
        }
        return posts;*/
    }
}
