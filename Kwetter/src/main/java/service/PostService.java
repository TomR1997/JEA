/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.PostDAO;
import domain.Post;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Tomt
 */
@Stateless
public class PostService {
    @Inject
    private PostDAO postDao;
    
    public Post findPost(Long id){
        return postDao.find(id);
    }
    
    
}
