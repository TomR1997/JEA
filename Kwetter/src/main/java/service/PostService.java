/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.PostDAO;
import dao.exceptions.NonExistingEntryException;
import domain.Post;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import service.exceptions.NonExistingPostException;

/**
 *
 * @author Tomt
 */
@Stateless
public class PostService {
    @Inject
    private PostDAO postDao;
    
    public Post findPost(Long id) throws NonExistingPostException{
        try {
            return postDao.find(id);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingPostException();
        }
    }   
}
