/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.User;
import java.util.List;
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

    public List<User> getAll() {
        return em.createNamedQuery("User.allUsers").getResultList();
    }

    public void save(User user) {
        em.persist(user);
    }

    public User find(Long id) {
        return em.find(User.class, id);
    }

    public void followUser(User user, User following) {
        if (!user.getFollowing().contains(following)) {
            user.getFollowing().add(following);
            em.persist(user);
        }
        //throw exception
    }

    public void unfollowUser(User user, User unfollowing) {
        if (user.getFollowing().contains(unfollowing)) {
            user.getFollowing().remove(unfollowing);
            em.persist(user);
        }
        //throw exception
    }

    public void changeUsername(Long id, String newName) {
        User user = find(id);
        if (user == null) {
            //throw exception
        }

        user.setUsername(newName);
        em.persist(user);
    }

    public void changeBio(Long id, String newBio) {
        User user = find(id);
        if (user == null) {
            //throw exception
        }

        user.setBio(newBio);
        em.persist(user);
    }
    
    public List<User> getFollowers(Long id){
        User user = find(id);
        if (user == null){
            //throw exception
        }
        
        return user.getFollowedBy();
    }
}
