package com.issart.datasource.dao;

import java.sql.SQLException;
import java.util.Optional;
import com.issart.datasource.entity.RSActivityType;
import com.issart.exception.DataSourceException;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Created by vurazov on 29.04.2016.
 */
public class RSActivityTypeDaoImpl extends BaseDaoImpl<RSActivityType, Integer> implements IRSActivityTypeDao {

    public RSActivityTypeDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, RSActivityType.class);
    }

    private QueryBuilder<RSActivityType, Integer> getQueryBuilder() {
        return queryBuilder().selectColumns("activityTypeId", "activityTypeName",
            "activityTypeDefaultLoyaltyScore", "activityTypeDefaultExpirienceScore",
            "activityTypeDefaultCommunicationScore");
    }

    @Override
    public Optional<RSActivityType> find(RSActivityType entry) throws DataSourceException {
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
                        .eq("activityTypeName", entry.getActivityTypeName())
                        .queryForFirst()
                );
            } catch (SQLException e) {
                throw new DataSourceException(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void modify(RSActivityType entry) throws DataSourceException {
        try {
            UpdateBuilder<RSActivityType, Integer> updateBuilder = updateBuilder();
            updateBuilder.updateColumnValue("activityTypeName", new SelectArg(SqlType.STRING, entry.getActivityTypeName()));
            updateBuilder.updateColumnValue("activityTypeDefaultLoyaltyScore", entry.getActivityTypeDefaultLoyaltyScore());
            updateBuilder.updateColumnValue("activityTypeDefaultExpirienceScore", entry.getActivityTypeDefaultExpirienceScore());
            updateBuilder.updateColumnValue("activityTypeDefaultCommunicationScore", entry.getActivityTypeDefaultCommunicationScore());
            updateBuilder.where().idEq(entry.getId());
            updateBuilder.update();
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        }
    }

    @Override
    public Optional<RSActivityType> createActivityType(RSActivityType entry) throws DataSourceException {
        try {
            return Optional.ofNullable( createIfNotExists(entry));
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        }
    }
}
