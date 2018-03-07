/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAO;
import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import domain.Post;
import domain.Role;
import domain.User;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import service.exceptions.FollowingException;
import service.exceptions.InvalidIdException;
import service.exceptions.InvalidNameException;
import service.exceptions.NonExistingUserException;
import service.exceptions.UnfollowingException;

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
    private User user2;
    private User user3;
    private User user4;
    private List<User> users;

    @Before
    public void setUp() {
        userService = new UserService();
        userService.setUserDao(userDao);
        user = new User("programmeergod", "Veldhoven", "somebio", "Tom Roelofs", new Role(), "someweb", new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Post>());
        user2 = new User("godprogrammeer", "Hovenveld", "biosome", "Roelofs Tom", new Role(), "websome", new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Post>());
        user.setId(1L);
        users = new ArrayList<>();
        users.add(user);
        user3 = null;
        user4 = null;
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
    public void invalidIdFindUserTest() throws NonExistingUserException, InvalidIdException {
        userService.findUser(-1L);
    }

    @Test
    public void saveTest() throws NonExistingUserException, InvalidIdException, InvalidNameException {
        doNothing().when(userDao).save(user);
        userService.save(user);
    }

    @Test(expected = InvalidIdException.class)
    public void invalidIdSaveTest() throws NonExistingUserException, InvalidIdException, InvalidNameException {
        user.setId(-1L);
        userService.save(user);
    }

    @Test(expected = InvalidNameException.class)
    public void invalidNameSaveTest() throws NonExistingUserException, InvalidIdException, InvalidNameException{
        user.setName("");
        userService.save(user);
        user.setName("a");
        user.setUsername("");
        userService.save(user);
    }
    
    @Test(expected = NonExistingUserException.class)
    public void nullUserSaveTest() throws NonExistingUserException, InvalidIdException, InvalidNameException{
        userService.save(user3);
    }
    
    @Test
    public void getAllTest() throws EmptyListException{       
        when(userDao.getAll()).thenReturn(users);
        assertTrue(!userService.getAll().isEmpty());
    }
    
    @Test(expected = EmptyListException.class)
    public void emptyListGetAllTest() throws EmptyListException{
        users.removeAll(users);
        when(userDao.getAll()).thenThrow(new EmptyListException());
        userService.getAll();
    }
    
    @Test
    public void followUserTest() throws FollowingException, NonExistingUserException, NonExistingEntryException{
        doNothing().when(userDao).followUser(user, user2);
        userService.followUser(user, user2);
    }
    
    @Test(expected = NonExistingUserException.class)
    public void nullUserFollowUserTest() throws NonExistingUserException, FollowingException, NonExistingEntryException{
        userService.followUser(user3, user2);
        userService.followUser(user2, user3);
    }
    
    @Test(expected = FollowingException.class)
    public void alreadyFollowingFollowUserTest() throws FollowingException, NonExistingUserException, NonExistingEntryException{
        user.getFollowing().add(user2);
        userService.followUser(user, user2);
    }

    @Test
    public void unfollowUser() throws NonExistingEntryException, UnfollowingException, NonExistingUserException, FollowingException{
        user.getFollowing().add(user2);
        doNothing().when(userDao).unfollowUser(user, user2);
        userService.unfollowUser(user, user2);
    }
    
    @Test(expected = NonExistingUserException.class)
    public void nullUserUnfollowUserTest() throws NonExistingUserException, NonExistingEntryException, UnfollowingException {
        userService.unfollowUser(user3, user2);
        userService.unfollowUser(user2, user3);
    }
    
    @Test(expected = UnfollowingException.class)
    public void notFollowingUnfollowUserTest() throws UnfollowingException, NonExistingUserException, NonExistingEntryException{
        userService.unfollowUser(user, user2);
    }
    
}
