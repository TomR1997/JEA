package service;

import dao.PostDAO;
import dao.UserDAO;
import dao.exceptions.EmptyListException;
import dao.exceptions.NonExistingEntryException;
import domain.Post;
import domain.Role;
import domain.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import service.exceptions.InvalidIdException;
import service.exceptions.InvalidNameException;
import service.exceptions.LikePostException;
import service.exceptions.NonExistingPostException;
import service.exceptions.NonExistingUserException;

/**
 *
 * @author Tomt
 */
@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    private PostDAO postDao;
    @Mock
    private UserDAO userDao;
    @InjectMocks
    private PostService postService;
    @InjectMocks
    private UserService userService;
    
    private Post post;
    private User user;
    private List<Post> posts;

    @Before
    public void setUp() {
        postService = new PostService();
        userService = new UserService();
        userService.setUserDao(userDao);
        postService.setPostDao(postDao);
        user = new User("programmeergod", "Veldhoven", "somebio", "Tom Roelofs", new Role(), "someweb", new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Post>(), new ArrayList<Post>());
        user.setId(1L);
        post = new Post("First post PagChomp", new Date(), user, new ArrayList<User>());
        post.setId(1L);
        posts = new ArrayList<>();
        posts.add(post);
    }

    @Test
    public void findPostTest() throws NonExistingEntryException, NonExistingPostException, InvalidIdException {
        when(postDao.find(post.getId())).thenReturn(post);
        Post p = postService.findPost(post.getId());
        assertSame(post, p);
    }

    @Test(expected = NonExistingPostException.class)
    public void exceptionFindPostTest() throws NonExistingPostException, NonExistingEntryException, InvalidIdException {
        when(postDao.find(post.getId())).thenThrow(new NonExistingEntryException());
        postService.findPost(post.getId());
    }

    @Test(expected = InvalidIdException.class)
    public void invalidIdFindPostTest() throws NonExistingEntryException, InvalidIdException, NonExistingPostException {
        postService.findPost(-1L);
    }

    @Test
    public void getAllTest() throws EmptyListException {
        when(postDao.getAll()).thenReturn(posts);
        assertTrue(!postService.getAll().isEmpty());
    }

    @Test(expected = EmptyListException.class)
    public void emptyListGetAllTest() throws EmptyListException {
        posts.removeAll(posts);
        when(postDao.getAll()).thenThrow(new EmptyListException());
        postService.getAll();
    }
    
    @Test
    public void deletePostTest() throws NonExistingEntryException, InvalidIdException{
        doNothing().when(postDao).delete(post.getId());
        postService.deletePost(post.getId());
    }
    
    @Test(expected = NonExistingEntryException.class)
    public void nullPostDeletePostTest() throws NonExistingEntryException, InvalidIdException{
        doThrow(new NonExistingEntryException()).when(postDao).delete(post.getId());
        postService.deletePost(post.getId());
    }
    
    @Test
    public void findPostsTest() throws EmptyListException, InvalidNameException{
        String tag = "sometag";
        when(postDao.findPosts(tag)).thenReturn(posts);
        assertTrue(!postService.findPosts(tag).isEmpty());
    }
    
    @Test(expected = EmptyListException.class)
    public void emptyListFindPostsTest() throws EmptyListException, InvalidNameException{
        when(postDao.findPosts("a")).thenThrow(new EmptyListException());
        postService.findPosts("a");
    }
    
    @Test(expected = EmptyListException.class)
    public void emptyListGetLatestPostsTest() throws EmptyListException{
        when(postDao.getLatestPosts(1L)).thenThrow(new EmptyListException());
        postService.getLatestPosts(1L);
    }
    
    @Test
    public void getLatestPostsTest() throws EmptyListException{
        when(postDao.getLatestPosts(1L)).thenReturn(posts);
        assertTrue(!postService.getLatestPosts(1L).isEmpty());
    }
    
    @Test
    public void getTimelineTest() throws EmptyListException{
        when(postDao.getTimeline(1L)).thenReturn(posts);
        assertTrue(!postService.getTimeline(1L).isEmpty());
    }
    
    @Test(expected = EmptyListException.class)
    public void emptyListGetTimelineTest() throws EmptyListException{
        when(postDao.getTimeline(1L)).thenThrow(new EmptyListException());
        postService.getTimeline(1L);
    }
    
    @Test(expected = InvalidIdException.class)
    public void invalidIdLikePostTest() throws NonExistingUserException, InvalidIdException, NonExistingPostException, LikePostException{
        when(userService.findUser(-1L)).thenThrow(new InvalidIdException());
        postService.likePost(post.getId(), user.getId());
    }
    
    @Test(expected = NonExistingUserException.class)
    public void nullUserLikePostTest() throws NonExistingUserException, InvalidIdException, NonExistingPostException, LikePostException, NonExistingEntryException{
        when(userDao.find(1L)).thenThrow(new NonExistingEntryException());
        when(userService.findUser(user.getId())).thenThrow(new NonExistingUserException());
        postService.likePost(post.getId(), user.getId());
    }

    @Test(expected = InvalidIdException.class)
    public void invalidIdUnlikePostTest() throws NonExistingUserException, InvalidIdException, NonExistingPostException, LikePostException{
        when(userService.findUser(-1L)).thenThrow(new InvalidIdException());
        postService.unlikePost(post.getId(), user.getId());
    }
    
    @Test(expected = NonExistingUserException.class)
    public void nullUserUnlikePostTest() throws NonExistingUserException, InvalidIdException, NonExistingPostException, LikePostException, NonExistingEntryException{
        when(userDao.find(1L)).thenThrow(new NonExistingEntryException());
        when(userService.findUser(user.getId())).thenThrow(new NonExistingUserException());
        postService.unlikePost(post.getId(), user.getId());
    }

    
}
