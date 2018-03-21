/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Tomt
 */
@Entity
@Table(name = "KWETTER_GROUP")
public class Group implements Serializable {
    @Id
    private String groupName;
    
    @ManyToMany
    @JoinTable(name = "USER_GROUP",
    joinColumns = @JoinColumn(name = "groupName", referencedColumnName = "groupName"),
            inverseJoinColumns = @JoinColumn(name = "username", referencedColumnName = "username"))
    private List<User> users;
    
    public Group(){
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
