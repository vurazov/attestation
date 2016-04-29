package com.issart.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;

import org.apache.commons.codec.binary.Base64;

import com.issart.datasource.entity.RsUser;
import com.issart.service.common.Password;

public class SecurityService {

    private static final String ALGORITHM = "SHA-1";

    private final SecureRandom random;

    public SecurityService() {
        random = new SecureRandom();
    }

    public boolean validateUserPassword(RsUser user, String password) {
        return validatePassword(new Password(user.getUserPasswordHash(), user.getUserPasswordSalt()), password);
    }

    @Deprecated
    public void setUserPassword(RsUser user, String password) {
        Password result = generatePassword(password);
        user.setUserPasswordSalt(result.getPasswordSalt());
        user.setUserPasswordHash(result.getPassword());
    }
    
    public Password generatePassword(String password) {
        Password pw = new Password();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        pw.setPasswordSalt(Base64.encodeBase64String(salt));
        pw.setPassword(Base64.encodeBase64String(getPasswordHash(password, salt)));
        return pw;
    }
    
    public boolean validatePassword(Password password, String passwordAttempt) {
        return Objects.deepEquals(
            Base64.decodeBase64(password.getPassword()),
            getPasswordHash(passwordAttempt, Base64.decodeBase64(password.getPasswordSalt()))
        );
    }

    private static byte[] getPasswordHash(String password, byte[] salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(salt);
            messageDigest.update(password.getBytes(StandardCharsets.UTF_16LE));
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // normally this exception is never thrown, because ALGORITHM is hardcoded
            throw new RuntimeException(e);
        }
    }
}
