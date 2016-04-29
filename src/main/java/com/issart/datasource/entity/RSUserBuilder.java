package com.issart.datasource.entity;

import org.apache.commons.lang3.builder.Builder;
import org.joda.time.DateTime;

public class RSUserBuilder implements Builder {
    private RsUser rsUser;

    private RSUserBuilder() {
        rsUser = new RsUser();
    }

    public static RSUserBuilder create() {
        return new RSUserBuilder();
    }

    public RSUserBuilder userId(int userId) {
        rsUser.id = userId;
        return this;
    }

    public RSUserBuilder userPasswordSalt(String userPasswordSalt) {
        rsUser.userPasswordSalt = userPasswordSalt;
        return this;
    }

    public RSUserBuilder userPasswordHash(String userPasswordHash) {
        rsUser.userPasswordHash = userPasswordHash;
        return this;
    }

    public RSUserBuilder userName(String userName) {
        rsUser.userName = userName;
        return this;
    }

    public RSUserBuilder userIsAdministrator(boolean userIsAdministrator) {
        rsUser.userIsAdministrator = userIsAdministrator;
        return this;
    }

    public RSUserBuilder userDismissDate(DateTime userDismissDate) {
        rsUser.userDismissDate = userDismissDate;
        return this;
    }

    public RSUserBuilder userHireDate(DateTime userHireDate) {
        rsUser.userHireDate = userHireDate;
        return this;
    }

    public RsUser build() {
        return rsUser;
    }
}
