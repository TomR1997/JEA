/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAO;
import dao.exceptions.EmptyListException;
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

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }
    
    public User findUser(Long id) throws NonExistingUserException, InvalidIdException {
        validId(id);
        try {
            return userDao.find(id);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingUserException("User does not exist.");
        }
    }

    public void save(User user) throws NonExistingUserException, InvalidIdException, InvalidNameException {
        if (user == null){
            throw new NonExistingUserException("User does not exist.");
        }
        validUser(user);
        userDao.save(user);
    }

    public List<User> getAll() throws EmptyListException {
        try {
            return userDao.getAll();
        } catch (EmptyListException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new EmptyListException("There are no users in the database.");
        }
    }

    public void followUser(User user, User following) throws FollowingException, NonExistingUserException, NonExistingEntryException {
        if (user == null || following == null) {
            throw new NonExistingUserException("User does not exist.");
        }
        if (!user.getFollowing().contains(following)) {
            userDao.followUser(user, following);
        }
        else {
            throw new FollowingException("User is already following this user.");
        }
    }

    public void unfollowUser(User user, User unfollowing) throws UnfollowingException, NonExistingUserException, NonExistingEntryException {
        if (user == null || unfollowing == null) {
            throw new NonExistingUserException("User does not exist.");
        }
        if (user.getFollowing().contains(unfollowing)) {
            userDao.unfollowUser(user, unfollowing);
        }
        else {
            throw new UnfollowingException("User is not following this user.");
        }
    }

    public void changeUsername(Long id, String newName) throws InvalidIdException, InvalidNameException, NonExistingUserException {
        validId(id);
        validName(newName);
        try {
            userDao.changeUsername(id, newName);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingUserException("User does not exist.");
        }
    }

    public void changeBio(Long id, String newBio) throws InvalidIdException, InvalidNameException, NonExistingUserException {
        validId(id);
        validName(newBio);
        try {
            userDao.changeBio(id, newBio);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingUserException("User does not exist.");
        }
    }

    public List<User> getFollowers(Long id) throws NonExistingUserException, EmptyListException, InvalidIdException {
        validId(id);
        try {
            return userDao.getFollowers(id);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingUserException("User does not exist.");
        } catch (EmptyListException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new EmptyListException("User has no followers.");
        }
    }

    public List<User> getFollowing(Long id) throws NonExistingUserException, EmptyListException, InvalidIdException {
        validId(id);
        try {
            return userDao.getFollowing(id);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingUserException("User does not exist.");
        } catch (EmptyListException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new EmptyListException("User is not following anyone.");
        }
    }

    private void validId(Long id) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException("Invalid id.");
        }
    }

    private void validName(String name) throws InvalidNameException {
        if (name == null || name.isEmpty() || name.length() >= 255) {
            throw new InvalidNameException("Invalid name.");
        }
    }
    
    private void validUser(User user) throws InvalidNameException, InvalidIdException{
        validId(user.getId());
        validName(user.getUsername());
        validName(user.getName());
    }
}
