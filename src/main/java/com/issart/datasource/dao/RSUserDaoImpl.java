package com.issart.datasource.dao;

import com.issart.datasource.entity.RsUser;
import com.issart.exception.DataSourceException;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import org.joda.time.DateTime;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RSUserDaoImpl extends BaseDaoImpl<RsUser, Integer> implements IRSUserDao {

    public RSUserDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, RsUser.class);
    }

    private QueryBuilder<RsUser, Integer> getQueryBuilder() {
        return queryBuilder().selectColumns("id", "userName", "userHireDate", "userDismissDate",
            "userPasswordHash", "userPasswordSalt", "userIsAdministrator");
    }

    @Override
    public Optional<RsUser> find(RsUser entry) throws DataSourceException {
        int id = entry.getId();
        if (id > 0) {
            try {
                return Optional.ofNullable(
                    getQueryBuilder().where().idEq(id).queryForFirst()
                );
            } catch (SQLException e) {
                throw new DataSourceException(e.getLocalizedMessage());
            }
        } else {
            try {
                return Optional.ofNullable(
                    getQueryBuilder().where()
                        .eq("userName", entry.getUserName())
                        .and().eq("userPasswordHash", entry.getUserPasswordHash()).queryForFirst()
                );
            } catch (SQLException e) {
                throw new DataSourceException(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public Optional<RsUser> getUserByUsername(String username) throws DataSourceException {
        try {
            return Optional.ofNullable(
                getQueryBuilder().where().eq("userName", username).queryForFirst()
            );
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        }
    }

    @Override
    public Optional<RsUser> createUser(RsUser user) throws DataSourceException {
        try {
            return Optional.ofNullable( createIfNotExists(user) );
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        }
    }

    @Override
    public void modify(RsUser rsUser) throws DataSourceException {
        try {
            UpdateBuilder<RsUser, Integer> updateBuilder = updateBuilder();
            updateBuilder.updateColumnValue("username", new SelectArg(SqlType.STRING, rsUser.getUserName()));
            updateBuilder.updateColumnValue("userDismissDate", rsUser.getUserDismissDate());
            updateBuilder.updateColumnValue("userHireDate", rsUser.getUserHireDate());
            updateBuilder.updateColumnValue("userPasswordHash", rsUser.getUserPasswordHash());
            updateBuilder.updateColumnValue("userPasswordSalt", rsUser.getUserPasswordSalt());
            updateBuilder.updateColumnValue("userIsAdministrator", rsUser.IsAdministrator());
            updateBuilder.where().idEq(rsUser.getId());
            updateBuilder.update();
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        }
    }

    @Override
    public Optional<List<RsUser>> getUnDismissedUsers() throws DataSourceException {
        try {
            return Optional.ofNullable(
                    getQueryBuilder().where().gt("userDismissDate", new DateTime(0)).query()
            );
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        }
    }
}
