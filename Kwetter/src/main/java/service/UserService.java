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
import service.exceptions.InvalidAmountException;
import service.exceptions.InvalidIdException;
import service.exceptions.InvalidNameException;
import service.exceptions.LoginException;
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
        if (user == null) {
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

    public void followUser(Long userId, Long followingId) throws FollowingException, NonExistingUserException, InvalidIdException {
        validId(userId);
        validId(followingId);
        User user = null;
        User following = null;
        try {
            user = userDao.find(userId);
            following = userDao.find(followingId);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingUserException("User does not exist.");
        }

        if (!user.getFollowing().contains(following)) {
            try {
                userDao.followUser(user, following);
            } catch (NonExistingEntryException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NonExistingUserException("User does not exist.");
            }
        } else {
            throw new FollowingException("User is already following this user.");
        }
    }

    public void unfollowUser(Long userId, Long unfollowingId) throws UnfollowingException, NonExistingUserException, InvalidIdException {
        validId(userId);
        validId(unfollowingId);
        User user = null;
        User unfollowing = null;
        try {
            user = userDao.find(userId);
            unfollowing = userDao.find(unfollowingId);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingUserException("User does not exist.");
        }

        if (user.getFollowing().contains(unfollowing)) {
            try {
                userDao.unfollowUser(user, unfollowing);
            } catch (NonExistingEntryException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                throw new NonExistingUserException("User does not exist.");
            }
        } else {
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

    public int getFollowerAmount(Long id) throws InvalidAmountException {
        int followers = userDao.getFollowerAmount(id);
        validAmount(followers);
        return followers;
    }

    public int getFollowingAmount(Long id) throws InvalidAmountException {
        int following = userDao.getFollowingAmount(id);
        validAmount(following);
        return following;
    }

    private void validAmount(int amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Invalid amount.");
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

    private void validUser(User user) throws InvalidNameException, InvalidIdException {
        validId(user.getId());
        validName(user.getUsername());
        validName(user.getName());
    }
}
