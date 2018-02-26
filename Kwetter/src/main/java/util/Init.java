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
        /*Role roleUser = new Role();
        roleUser.setName(RoleName.USER.toString());
        roleDao.save(roleUser);
        /*User user1 = new User("programmeergod", "Veldhoven", "some bio..", "Tom Roelofs", RoleName.USER, "some web...", );
        User user2 = new User("programmeergod", "Veldhoven", "some bio..", "Tom Roelofs", RoleName.USER, "some web...", );
        User user3 = new User("programmeergod", "Veldhoven", "some bio..", "Tom Roelofs", RoleName.USER, "some web...", );
        userDao.save(user);*/
        System.out.println("Init done.......................");
    }
}
