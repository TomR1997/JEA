package service;

import dao.PostDAO;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import service.exceptions.InvalidIdException;
import service.exceptions.NonExistingPostException;

/**
 *
 * @author Tomt
 */
@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    private PostDAO postDao;
    @InjectMocks
    private PostService postService;
    private Post post;
    private User user;
    private List<Post> posts;

    @Before
    public void setUp() {
        postService = new PostService();
        postService.setPostDao(postDao);
        user = new User("programmeergod", "Veldhoven", "somebio", "Tom Roelofs", new Role(), "someweb", new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Post>());
        post = new Post("First post PagChomp", new Date(), user);
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
    
    @Test(expected = NonExistingPostException.class)
    public void nullPostDeletePostTest() throws NonExistingEntryException, InvalidIdException{
        doThrow(new NonExistingEntryException()).when(postDao).delete(post.getId());
        postService.deletePost(post.getId());
    }
    
    //latestposts && timelinetests
}
