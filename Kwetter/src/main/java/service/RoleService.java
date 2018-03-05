/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.RoleDAO;
import domain.Role;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Tomt
 */
@Stateless
public class RoleService {
    @Inject
    private RoleDAO roleDao;
    
    /*public Role findRole(String name){
        return roleDao.find(name);
    }*/
}
