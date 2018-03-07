/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
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

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public List<User> getAll() throws EmptyListException {
        List<User> users = em.createNamedQuery("User.allUsers").getResultList();
        if (users.isEmpty()) {
            throw new EmptyListException();
        }
        return users;
    }

    public void save(User user) {
        em.persist(user);
    }

    public void update(User user) throws NonExistingEntryException {
        if (user == null) {
            throw new NonExistingEntryException();
        }
        em.merge(user);
    }

    public User find(Long id) throws NonExistingEntryException {
        User user = em.find(User.class, id);
        if (user == null) {
            throw new NonExistingEntryException();
        }
        return user;
    }

    public void followUser(User user, User following) throws NonExistingEntryException {
        user.getFollowing().add(following);
        update(user);
        save(user);
    }

    public void unfollowUser(User user, User unfollowing) throws NonExistingEntryException {
        user.getFollowing().remove(unfollowing);
        update(user);
        save(user);
    }

    public void changeUsername(Long id, String newName) throws NonExistingEntryException {
        User user = find(id);
        if (user == null) {
            throw new NonExistingEntryException();
        }
        user.setUsername(newName);
        update(user);
        save(user);
    }

    public void changeBio(Long id, String newBio) throws NonExistingEntryException {
        User user = find(id);
        if (user == null) {
            throw new NonExistingEntryException();
        }
        user.setBio(newBio);
        update(user);
        save(user);
    }

    public List<User> getFollowers(Long id) throws NonExistingEntryException, EmptyListException {
        User user = find(id);
        if (user == null) {
            throw new NonExistingEntryException();
        }
        if (user.getFollowers().isEmpty()) {
            throw new EmptyListException();
        }
        return user.getFollowers();
    }

    public List<User> getFollowing(Long id) throws NonExistingEntryException, EmptyListException {
        User user = find(id);
        if (user == null) {
            throw new NonExistingEntryException();
        }
        if (user.getFollowers().isEmpty()) {
            throw new EmptyListException();
        }
        return user.getFollowers();
    }
   
    public int getFollowerAmount(Long id) throws EmptyListException{
        return em.createNamedQuery("User.getFollowerAmount").executeUpdate();
    }
    
    public int getFollowingAmount(Long id) throws EmptyListException{
        return em.createNamedQuery("User.getFollowingAmount").executeUpdate();
    }
}
