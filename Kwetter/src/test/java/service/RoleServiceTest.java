package service;

import dao.RoleDAO;
import dao.exceptions.NonExistingEntryException;
import domain.Role;
import domain.RoleName;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import service.exceptions.NonExistingRoleException;

/**
 *
 * @author Tomt
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @Mock
    private RoleDAO roleDao;
    @InjectMocks
    private RoleService roleService;
    private Role role;

    @Before
    public void setUp() {
        roleService = new RoleService();
        roleService.setRoleDao(roleDao);
        role = new Role(RoleName.MODERATOR.toString());
    }

    @Test
    public void findRoleTest() throws NonExistingEntryException, NonExistingRoleException {
        when(roleDao.find(role.getName())).thenReturn(role);
        Role r = roleService.findRole(role.getName());
        assertSame(role, r);
    }

    @Test(expected = NonExistingRoleException.class)
    public void exceptionFindRoleTest() throws NonExistingEntryException, NonExistingRoleException {
        when(roleDao.find(role.getName())).thenThrow(new NonExistingEntryException());
        roleService.findRole(role.getName());
    }
    
    @Test
    public void saveRoleTest() throws NonExistingRoleException, NonExistingEntryException{
        doNothing().when(roleDao).save(role);
        roleService.saveRole(role);
    }
}
