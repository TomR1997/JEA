/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.RoleDAO;
import dao.exceptions.NonExistingEntryException;
import domain.Role;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import service.exceptions.NonExistingRoleException;

/**
 *
 * @author Tomt
 */
@Stateless
public class RoleService {

    @Inject
    private RoleDAO roleDao;

    public void setRoleDao(RoleDAO roleDao) {
        this.roleDao = roleDao;
    }

    public Role findRole(String name) throws NonExistingRoleException {
        try {
            return roleDao.find(name);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingRoleException();
        }
    }

    public void saveRole(Role role) throws NonExistingRoleException {
        if (role == null) {
            throw new NonExistingRoleException();
        }
        try {
            roleDao.save(role);
        } catch (NonExistingEntryException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonExistingRoleException();
        }
    }
}
