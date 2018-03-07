/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.PostDAO;
import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import domain.Post;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import service.exceptions.InvalidIdException;
import service.exceptions.InvalidNameException;
import service.exceptions.NonExistingPostException;

/**
 *
 * @author Tomt
 */
@Stateless
public class PostService {

    @Inject
    private PostDAO postDao;

    public Post findPost(Long id) throws NonExistingPostException {
        try {
            return postDao.find(id);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingPostException();
        }
    }

    public List<Post> getAll() throws EmptyListException {
        try {
            return postDao.getAll();
        } catch (EmptyListException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new EmptyListException();
        }
    }

    public Post find(Long id) throws NonExistingEntryException, InvalidIdException {
        validId(id);
        try {
            return postDao.find(id);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingEntryException();
        }
    }

    public Post find(String tags) throws NonExistingEntryException, InvalidNameException {
        validName(tags);
        try {
            return postDao.find(tags);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingEntryException();
        }
    }

    public void delete(Long id) throws NonExistingEntryException, InvalidIdException {
        validId(id);
        try {
            postDao.delete(id);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingEntryException();
        }
    }

    private void validId(Long id) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException();
        }
    }

    private void validName(String name) throws InvalidNameException {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException();
        }
    }
}
