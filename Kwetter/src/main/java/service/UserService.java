/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAO;
import dao.exceptions.NonExistingEntryException;
import domain.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import service.exceptions.NonExistingUserException;

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

    public List<User> getAll() throws NonExistingUserException {
        try {
            return userDao.getAll();
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingUserException();
        }
    }
}
