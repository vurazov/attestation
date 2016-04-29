package com.issart.datasource.dao;

import com.issart.datasource.entity.RsUser;
import com.issart.exception.DataSourceException;
import com.j256.ormlite.dao.Dao;
import java.util.Optional;


public interface IRSUserDao extends Dao<RsUser, Integer> {

    Optional<RsUser> find(RsUser entry) throws DataSourceException;

    Optional<RsUser> getUserByUsername(String username) throws DataSourceException;

    Optional<RsUser> createUser(RsUser user) throws DataSourceException;

    void modify(RsUser rsUser)throws DataSourceException;
}
