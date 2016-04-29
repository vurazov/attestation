package com.issart.service;

/**
 * Created by vurazov on 29.04.2016.
 */
import org.junit.Assert;
import com.issart.service.common.Password;
import org.junit.Before;
import org.junit.Test;

public class SecurityServiceTest {
    SecurityService service;

    @Before
    public void setup() {
        service = new SecurityService();
    }

    @Test
    public void testValidPassword() {
        Password pass = service.generatePassword("fancyPassword");

        Assert.assertTrue(service.validatePassword(pass, "fancyPassword"));
    }

    @Test
    public void testInvalidPassword() {
        Password pass = service.generatePassword("fancyPassword");

        Assert.assertFalse(service.validatePassword(pass, "wrongPassword"));
    }

    @Test
    public void testInvalidValidator() {
        Assert.assertFalse(
            service.validatePassword(
                new Password("bogus", "password"),
                "fancyPassword"
            )
        );
    }
}

