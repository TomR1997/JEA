/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api.dto;

import domain.Role;

/**
 *
 * @author Tomt
 */
public class RoleDTO {

    private String name;

    public RoleDTO(Role role) {
        this.name = role.getName();
    }
}
