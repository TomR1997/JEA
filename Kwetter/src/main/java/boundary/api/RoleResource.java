/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api;

import domain.Role;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import service.RoleService;

/**
 *
 * @author Tomt
 */
@Path("roles")
@Stateless
public class RoleResource {
    @Inject
    private RoleService roleService;
    
    @GET
    @Path("GetRole/{id}")
    public Role getRole(@PathParam("id") String name){
        return roleService.findRole(name);
    }
}
