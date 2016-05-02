package com.issart.datasource.entity;

import org.apache.commons.lang3.builder.Builder;
import org.joda.time.DateTime;

public class RsUserBuilder implements Builder {
    private RsUser rsUser;

    private RsUserBuilder() {
        rsUser = new RsUser();
    }

    public static RsUserBuilder create() {
        return new RsUserBuilder();
    }

    public RsUserBuilder userId(int userId) {
        rsUser.id = userId;
        return this;
    }

    public RsUserBuilder userPasswordSalt(String userPasswordSalt) {
        rsUser.userPasswordSalt = userPasswordSalt;
        return this;
    }

    public RsUserBuilder userPasswordHash(String userPasswordHash) {
        rsUser.userPasswordHash = userPasswordHash;
        return this;
    }

    public RsUserBuilder userName(String userName) {
        rsUser.userName = userName;
        return this;
    }

    public RsUserBuilder userIsAdministrator(boolean userIsAdministrator) {
        rsUser.userIsAdministrator = userIsAdministrator;
        return this;
    }

    public RsUserBuilder userDismissDate(DateTime userDismissDate) {
        rsUser.userDismissDate = userDismissDate;
        return this;
    }

    public RsUserBuilder userHireDate(DateTime userHireDate) {
        rsUser.userHireDate = userHireDate;
        return this;
    }

    public RsUser build() {
        return rsUser;
    }
}
