/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.PostDAO;
import dao.UserDAO;
import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import domain.Post;
import domain.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import service.exceptions.InvalidIdException;
import service.exceptions.InvalidNameException;
import service.exceptions.LikePostException;
import service.exceptions.NonExistingPostException;
import service.exceptions.NonExistingUserException;

/**
 *
 * @author Tomt
 */
@Stateless
public class PostService {

    @Inject
    private PostDAO postDao;
    @Inject
    private UserService userService;

    public void setPostDao(PostDAO postDao) {
        this.postDao = postDao;
    }

    public Post findPost(Long id) throws NonExistingPostException, InvalidIdException {
        validId(id);
        try {
            return postDao.find(id);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingPostException("No post was found.");
        }
    }

    public List<Post> getAll() throws EmptyListException {
        try {
            return postDao.getAll();
        } catch (EmptyListException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new EmptyListException("No posts were found.");
        }
    }

    public List<Post> findPost(String tags) throws EmptyListException, InvalidNameException {
        validName(tags);
        try {
            return postDao.find(tags);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new EmptyListException("No posts were found.");
        }
    }

    public void deletePost(Long id) throws NonExistingEntryException, InvalidIdException {
        validId(id);
        try {
            postDao.delete(id);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingEntryException("No post was found.");
        }
    }

    public List<Post> getLatestPosts(Long userId) throws EmptyListException {
        try {
            return postDao.getLatestPosts(userId);
        } catch (EmptyListException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new EmptyListException("No posts were found.");
        }
    }

    public List<Post> getTimeline(Long userId) throws EmptyListException {
        try {
            return postDao.getTimeline(userId);
        } catch (EmptyListException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new EmptyListException("No posts were found.");
        }
    }
    
    public void likePost(Long postId, Long userId) throws InvalidIdException, NonExistingUserException, NonExistingPostException, LikePostException{
        User user = null;
        Post post = null;
        try{
            user = userService.findUser(userId);
        } catch(NonExistingUserException ex){
            throw new NonExistingUserException();
        } catch (InvalidIdException ex) {
            throw new InvalidIdException();
        }
        
        try{
            post = postDao.find(postId);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingPostException("Post does not exist.");
        }
        
        if (post.getLikedBy().contains(user)){
            throw new LikePostException("User already liked this post.");
        }
        
        try{
            postDao.likePost(post, user);
        } catch(NonExistingEntryException ex){
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingPostException("Post does not exist.");
        }
    }

    private void validId(Long id) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException();
        }
    }

    private void validName(String name) throws InvalidNameException {
        if (name == null || name.isEmpty() || name.length() >= 255) {
            throw new InvalidNameException();
        }
    }
}
