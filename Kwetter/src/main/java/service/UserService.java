/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAO;
import service.exceptions.FollowingException;
import dao.exceptions.NonExistingEntryException;
import domain.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import service.exceptions.InvalidIdException;
import service.exceptions.InvalidNameException;
import service.exceptions.NonExistingUserException;
import service.exceptions.UnfollowingException;

/**
 *
 * @author Tomt
 */
@Stateless
public class UserService {

    @Inject
    private UserDAO userDao;

    public User findUser(Long id) throws NonExistingUserException {
        try {
            return userDao.find(id);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingUserException();
        }
    }

    public void save(User user) throws NonExistingUserException {
        try {
            userDao.save(user);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingUserException();
        }
    }

    public List<User> getAll() throws NonExistingUserException {
        try {
            return userDao.getAll();
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingUserException();
        }
    }

    public void followUser(User user, User following) throws FollowingException, NonExistingUserException {
        if (user == null || following == null) {
            throw new NonExistingUserException();
        }
        if (!user.getFollowing().contains(following)) {
            user.getFollowing().add(following);
            try {
                userDao.followUser(user, following);
            } catch (NonExistingEntryException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        throw new FollowingException();
    }

    public void unfollowUser(User user, User unfollowing) throws NonExistingEntryException, UnfollowingException {
        if (user == null || unfollowing == null) {
            throw new NonExistingEntryException();
        }
        if (user.getFollowing().contains(unfollowing)) {
            user.getFollowing().remove(unfollowing);
            try {
                userDao.unfollowUser(user, unfollowing);
            } catch (NonExistingEntryException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        throw new UnfollowingException();
    }

    public void changeUsername(Long id, String newName) throws InvalidIdException, InvalidNameException, NonExistingUserException {
        validId(id);
        validName(newName);
        try {
            userDao.changeUsername(id, newName);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingUserException();
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
