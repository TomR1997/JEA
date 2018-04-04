/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api;

import boundary.api.dto.RoleDTO;
import boundary.api.response.GetSingleResponse;
import com.google.gson.GsonBuilder;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    private final GsonBuilder gson = new GsonBuilder().serializeNulls();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{name}")
    public Response findRole(@PathParam("name") String name){
        GetSingleResponse<RoleDTO> response = new GetSingleResponse<>();
        try {
            response.setRecord(new RoleDTO(roleService.findRole(name)));
            response.setSuccess(true);
        } catch (NonExistingRoleException ex) {
            response.addMessage("De opgegeven rol bestaat niet.");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.create().toJson(response)).build();
        }
        
        return Response.ok(gson.create().toJson(response)).build();
    }
}
