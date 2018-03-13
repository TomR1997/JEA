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
import javax.persistence.Query;

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

    public PostDAO() {
    }

    public void save(Post post) throws NonExistingEntryException {
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

    public List<Post> findPosts(String tags) throws EmptyListException {
        Query query = em.createNamedQuery("Post.findPosts");
        List<Post> posts = query.setParameter("tags", "%" + tags + "%").setMaxResults(20).getResultList();
        if (posts.isEmpty()) {
            throw new EmptyListException();
        }
        return posts;
    }

    public void delete(Long id) throws NonExistingEntryException {
        Post post = em.find(Post.class, id);
        if (post == null) {
            throw new NonExistingEntryException();
        }
        em.remove(post);
    }

    public List<Post> getLatestPosts(Long userId) throws EmptyListException {
        Query query = em.createNamedQuery("Post.getLatestPosts");
        List<Post> posts = query.setParameter("owner_id", userId).setMaxResults(20).getResultList();
        if (posts.isEmpty()) {
            throw new EmptyListException();
        }
        return posts;
    }

    public List<Post> getTimeline(Long userId) throws EmptyListException {
        Query query = em.createNamedQuery("Post.getTimeline");
        List<Post> posts = query.setParameter("owner_id", userId).setMaxResults(20).getResultList();
        if (posts.isEmpty()) {
            throw new EmptyListException();
        }
        return posts;
    }

    public int getPostCount(Long userId) {
        Query query = em.createNamedQuery("Post.getPostCountByOwner");
        return query.setParameter("owner_id", userId).getFirstResult();
    }

    public void likePost(Post post, User user) throws NonExistingEntryException {
        post.getLikedBy().add(user);
        update(post);
        save(post);
    }

    public void unlikePost(Post post, User user) throws NonExistingEntryException {
        post.getLikedBy().remove(user);
        update(post);
        save(post);
    }
}
