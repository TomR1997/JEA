/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAO;
import dao.exceptions.NonExistingEntryException;
import domain.User;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import service.exceptions.InvalidIdException;
import service.exceptions.NonExistingUserException;

/**
 *
 * @author Tomt
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDAO userDao;
    @InjectMocks
    private UserService userService;
    private User user;

    @Before
    public void setUp() {
        userService = new UserService();
        userService.setUserDao(userDao);
        user = new User("programmeergod", "Veldhoven", "somebio", "Tom Roelofs", "someweb");
        user.setId(1L);
    }

    @Test
    public void findUserTest() throws NonExistingEntryException, NonExistingUserException, InvalidIdException {
        when(userDao.find(user.getId())).thenReturn(user);
        User u = userService.findUser(user.getId());

        assertSame(user, u);
    }

    @Test(expected = NonExistingUserException.class)
    public void exceptionFindUserTest() throws NonExistingUserException, NonExistingEntryException, InvalidIdException {
        when(userDao.find(user.getId())).thenThrow(new NonExistingEntryException());
        userService.findUser(user.getId());
    }
    
    @Test(expected = InvalidIdException.class)
    public void invalidIdFindUserTest() throws NonExistingUserException, InvalidIdException{
        userService.findUser(-1L);
    }
    
    @Test
    public void saveTest() throws NonExistingEntryException, NonExistingUserException, InvalidIdException{
        doNothing().when(userDao).save(user);
        userService.save(user);
    }
    
    @Test(expected = InvalidIdException.class)
    public void invalidIdSaveTest() throws NonExistingUserException, InvalidIdException{
        user.setId(-1L);
        userService.save(user);
    }

}
