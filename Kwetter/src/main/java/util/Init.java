/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.RoleDAO;
import dao.UserDAO;
import domain.Role;
import domain.RoleName;
import domain.User;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Tomt
 */
@Startup
@Singleton
public class Init {
    @Inject
    private UserDAO userDao;
    @Inject
    private RoleDAO roleDao;

    @PostConstruct
    public void init() {
        System.out.println("Init begin......................");
        Role role = new Role();
        role.setName(RoleName.USER.toString());
        roleDao.save(role);
        /*User user = new User("programmeergod", "Veldhoven", "some bio..", "Tom Roelofs", RoleName.USER, "some web...", );*/
        System.out.println("Init done.......................");
    }
}
