/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.EmptyListException;
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

    public void update(Post post) throws NonExistingEntryException {
        if (post == null) {
            throw new NonExistingEntryException();
        }
        em.merge(post);
    }

    public List<Post> getAll() throws EmptyListException {
        List<Post> posts = em.createNamedQuery("Post.allPosts").getResultList();
        if (posts.isEmpty()) {
            throw new EmptyListException();
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

    public Post find(String tags) throws NonExistingEntryException {
        Post post = null;
        if (post == null) {
            throw new NonExistingEntryException();
        }
        throw new NotImplementedException();
        //return post;
    }

    public void delete(Long id) throws NonExistingEntryException {
        Post post = em.find(Post.class, id);
        if (post == null) {
            throw new NonExistingEntryException();
        }
        em.remove(post);
    }

    public List<Post> getLatestPosts(User user) throws NonExistingEntryException {
        List<Post> posts = em.createNamedQuery("Post.latestPosts").getResultList();
        throw new NotImplementedException();
        /*if(posts == null){
            throw new NonExistingEntryException();
        }
        return posts;*/
    }

    public List<Post> getTimeline(Long id) throws EmptyListException {
        List<Post> posts = em.createNamedQuery("Post.getTimeline").getResultList();
        throw new NotImplementedException();
        /*if(posts.isEmpty()){
            throw new EmptyListException();
        }
        return posts;*/
    }
}
