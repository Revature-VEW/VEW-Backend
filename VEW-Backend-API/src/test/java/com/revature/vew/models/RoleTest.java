package com.revature.vew.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RoleTest {
    @Test
    public void testSetRoleId() {
        Role role = new Role();
        role.setRoleId(123);
        assertEquals(123, role.getRoleId());
    }

    @Test
    public void testSetRole() {
        Role role = new Role();
        role.setRole("Role");
        assertEquals("Role", role.getRole());
    }

    @Test
    public void testToString() {
        assertEquals("Role{roleId=0, role='null', creationDate=null, lastModifiedDate=null}", (new Role()).toString());
    }
}

