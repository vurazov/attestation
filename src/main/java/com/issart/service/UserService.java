package com.issart.service;

import java.util.Optional;
import com.google.common.base.Strings;
import com.issart.datasource.entity.RSUserBuilder;
import com.issart.datasource.entity.RsUser;
import com.issart.exception.DataSourceException;
import com.issart.exception.DuplicateUserException;
import com.issart.exception.InvalidCredentialsException;
import com.issart.exception.InvalidUserException;
import com.issart.exception.ServiceException;
import com.issart.service.common.Password;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vurazov on 29.04.2016.
 */
public class UserService extends AbstractService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    SecurityService securityService;

    public UserService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public Optional<RsUser> createUser(
        String userName,
        String userPassword,
        boolean isAdministator
    ) throws ServiceException, DataSourceException {
        if (dataSource.getIRSUserDAO().getUserByUsername(userName).isPresent()) {
            throw new DuplicateUserException("Username already exist: " + userName);
        }
        Password password;
        if (StringUtils.isNotEmpty(userPassword)) {
            password = securityService.generatePassword(userPassword);
        } else {
            throw new InvalidCredentialsException("Username and/or password are incorrect.");
        }
        final Optional<RsUser> user = dataSource.getIRSUserDAO().createUser(RSUserBuilder.create()
            .userId(-1)
            .userName(userName)
            .userPasswordHash(password.getPassword())
            .userPasswordSalt(password.getPasswordSalt())
            .userHireDate(DateTime.now())
            .userDismissDate(new DateTime(0))
            .userIsAdministrator(isAdministator)
            .build());

        if (user.isPresent()) {
            return user;
        } else {
            throw new InvalidUserException("Username and/or password are incorrect.");
        }
    }
    public void updateUser(
        String userName, String userPassword,
        DateTime dismissDate, boolean isAdministator
    ) throws ServiceException, DataSourceException {

        if(Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(userPassword)){
            throw new InvalidCredentialsException("Username and/or password are incorrect.");
        }

        final Optional<RsUser> user = dataSource.getIRSUserDAO().getUserByUsername(userName);
        if (!user.isPresent() && securityService.validateUserPassword(user.get(), userPassword)) {
            throw new InvalidUserException("Username does not exists: " + userName);
        }
        if(dismissDate != null){
            user.get().setUserDismissDate(dismissDate);
        }
        user.get().setIsAdministrator(isAdministator);
        dataSource.getIRSUserDAO().modify(user.get());
    }

    public Optional<RsUser> getUser(String userName, String userPassword) throws ServiceException, DataSourceException {
        if (Strings.isNullOrEmpty(userName)) {
            throw new InvalidCredentialsException("Username or email is empty.");
        }

        if (Strings.isNullOrEmpty(userPassword)) {
            throw new InvalidCredentialsException("Password is empty.");
        }

        final Optional<RsUser> user = dataSource.getIRSUserDAO().getUserByUsername(userName);
        if(!securityService.validateUserPassword(user.get(), userPassword)){
            throw new InvalidCredentialsException("Username and/or password are incorrect.");
        }
        if (user.isPresent()) {
            return user;
        } else {
            throw new InvalidCredentialsException("Username and/or password are incorrect.");
        }
    }

}
