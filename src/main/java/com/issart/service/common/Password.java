package com.issart.service.common;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * A Vertigo password consists of a Base64-encoded password hash and Base64 encoded password salt.
 * @author tbull
 *
 */
public class Password {
    String password;
    String passwordSalt;

    public Password() {
    
    }
    
    public Password(String password, String passwordSalt) {
        super();
        this.password = password;
        this.passwordSalt = passwordSalt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
          .add("password", password)
          .add("passwordSalt", passwordSalt)
          .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(password, passwordSalt);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Password) {
            Password that = (Password) object;
            return Objects.equal(this.password, that.password)
                && Objects.equal(this.passwordSalt, that.passwordSalt);
        }
        return false;
    }

}
