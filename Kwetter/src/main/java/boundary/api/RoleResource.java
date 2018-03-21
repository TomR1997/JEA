/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api;

import boundary.api.dto.RoleDTO;
import boundary.api.response.CreateResponse;
import boundary.api.response.GetSingleResponse;
import com.google.gson.Gson;
import domain.Role;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import service.RoleService;
import service.exceptions.NonExistingRoleException;

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
    @Path("{name}")
    public String findRole(@PathParam("name") String name){
        GetSingleResponse<RoleDTO> response = new GetSingleResponse<>(false);
        try {
            response.setRecord(new RoleDTO(roleService.findRole(name)));
            response.setSuccess(true);
        } catch (NonExistingRoleException ex) {
            response.addMessage("De opgegeven rol bestaat niet.");
        }
        
        return new Gson().toJson(response);
    }
    
    @POST
    @Path("save")
    public String saveRole(Role role){
        CreateResponse<String> response = new CreateResponse<>(false);
        try {
            roleService.saveRole(role);
            response.setSuccess(true);
        } catch (NonExistingRoleException ex) {
            response.addMessage("Role bevat niet de juiste waardes.");
        }
        
        return new Gson().toJson(response);
    }
}
