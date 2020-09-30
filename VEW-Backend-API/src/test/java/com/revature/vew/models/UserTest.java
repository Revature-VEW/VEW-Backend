package com.revature.vew.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void testSetUserId() {
        User user = new User();
        user.setUserId(123);
        assertEquals(123, user.getUserId());
    }

    @Test
    public void testSetEmail() {
        User user = new User();
        user.setEmail("Email");
        assertEquals("Email", user.getEmail());
    }

    @Test
    public void testSetPassword() {
        User user = new User();
        user.setPassword("iloveyou");
        assertEquals("iloveyou", user.getPassword());
    }

    @Test
    public void testSetFirstName() {
        User user = new User();
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());
    }

    @Test
    public void testSetLastName() {
        User user = new User();
        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void testSetRole() {
        User user = new User();
        user.setRole(new Role());
        assertEquals(
                "User{userId=0, username='null', password='null', firstName='null', lastName='null', role=Role{roleId=0,"
                        + " role='null', creationDate=null, lastModifiedDate=null}}",
                user.toString());
    }

    @Test
    public void testToString() {
        assertEquals("User{userId=0, username='null', password='null', firstName='null', lastName='null', role=null}",
                (new User()).toString());
    }
}

