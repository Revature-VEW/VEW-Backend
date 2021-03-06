package com.revature.vew.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends Auditable<String> {
    @Id
    @Column(name = "role_id", nullable = false, unique = true)
    private  int roleId;
    @Column(nullable = false, unique = true)
    private String role;

    public Role() {
    }

    public Role(int roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                ", creationDate=" + creationDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
