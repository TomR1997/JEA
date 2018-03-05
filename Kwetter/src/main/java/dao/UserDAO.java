/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import service.exceptions.FollowingException;
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

    public List<User> getAll() throws NonExistingEntryException {
        List<User> users = em.createNamedQuery("User.allUsers").getResultList();
        if (users.isEmpty()) {
            throw new NonExistingEntryException();
        }
        return users;
    }

    public void save(User user) throws NonExistingEntryException {
        if (user == null) {
            throw new NonExistingEntryException();
        }
        em.persist(user);
    }

    public User find(Long id) throws NonExistingEntryException {
        User user = em.find(User.class, id);
        if (user == null) {
            throw new NonExistingEntryException();
        }
        return user;
    }

    public void followUser(User user, User following) throws NonExistingEntryException {
        if (user == null || following == null) {
            throw new NonExistingEntryException();
        }
        em.persist(user);
    }

    public void unfollowUser(User user, User unfollowing) throws NonExistingEntryException {
        if (user == null || unfollowing == null) {
            throw new NonExistingEntryException();
        }
        em.persist(user);
    }

    public void changeUsername(Long id, String newName) throws NonExistingEntryException {
        User user = find(id);
        if (user == null) {
            throw new NonExistingEntryException();
        }
        user.setUsername(newName);
        em.persist(user);
    }

    public void changeBio(Long id, String newBio) throws NonExistingEntryException {
        User user = find(id);
        if (user == null) {
            throw new NonExistingEntryException();
        }
        user.setBio(newBio);
        em.persist(user);
    }

    public List<User> getFollowers(Long id) throws NonExistingEntryException {
        User user = find(id);
        if (user == null) {
            throw new NonExistingEntryException();
        }
        return user.getFollowedBy();
    }
}
