package com.issart.service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Optional;
import com.issart.datasource.entity.RsUser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by vurazov on 29.04.2016.
 */
public class UserServiceTest {
    UserService userService;

    @Before
    public void setUp() {
       userService = new UserService(new SecurityService());
    }

    @After
    public void tearDown() throws Exception {
        userService.close();
        FileUtils.deleteDirectory(FileUtils.getFile(
            System.getProperty("user.home") + "/" + ".scores"
        ));
    }

    @Test
    public void testUserService() throws Exception {
        assertTrue(userService.createUser("fancyUserAdmin", "fancyPassword", true).isPresent());
        assertTrue(userService.createUser("fancyUser", "fancyPassword", false).isPresent());

        Optional<RsUser> userAdmin = userService.getUser("fancyUserAdmin", "fancyPassword");
        assertTrue(userAdmin.isPresent());
        assertTrue(userAdmin.get().IsAdministrator());
        Optional<RsUser> user = userService.getUser("fancyUser", "fancyPassword");
        assertTrue(user.isPresent());
        assertFalse(user.get().IsAdministrator());

        userAdmin = userService.getUser("fancyUserAdmin", "fancyPassword");
        assertTrue(userAdmin.isPresent());
        assertTrue(userAdmin.get().IsAdministrator());
        DateTime dissmiss = DateTime.now();
        userService.updateUser(userAdmin.get().getUserName(), "fancyPassword", dissmiss, false);
        userAdmin = userService.getUser(userAdmin.get().getUserName(), "fancyPassword");
        assertFalse(userAdmin.get().IsAdministrator());
        assertTrue(userAdmin.get().getUserDismissDate().equals(dissmiss));
    }
/*
    @Test
    public void testCreateUser() throws Exception {
        assertTrue(userService.createUser("fancyUserAdmin", "fancyPassword", true).isPresent());
        assertTrue(userService.createUser("fancyUser", "fancyPassword", false).isPresent());
    }

    @Test
    public void testGetUser() throws Exception {
        Optional<RsUser> userAdmin = userService.getUser("fancyUserAdmin", "fancyPassword");
        assertTrue(userAdmin.isPresent());
        assertTrue(userAdmin.get().IsAdministrator());
        Optional<RsUser> user = userService.getUser("fancyUser", "fancyPassword");
        assertTrue(user.isPresent());
        assertFalse(user.get().IsAdministrator());
    }

    @Test
    public void testGetUpdateUser() throws Exception {
        Optional<RsUser> userAdmin = userService.getUser("fancyUserAdmin", "fancyPassword");
        assertTrue(userAdmin.isPresent());
        assertTrue(userAdmin.get().IsAdministrator());
        userService.updateUser(userAdmin.get().getUserName(), "fancyPassword", false);
        userAdmin = userService.getUser(userAdmin.get().getUserName(), "fancyPassword");
        assertFalse(userAdmin.get().IsAdministrator());
    }*/
}