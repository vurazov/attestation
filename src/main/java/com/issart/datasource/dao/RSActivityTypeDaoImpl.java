package com.issart.datasource.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import com.issart.datasource.entity.RsActivityType;
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
public class RSActivityTypeDaoImpl extends BaseDaoImpl<RsActivityType, Integer> implements IRSActivityTypeDao {

    public RSActivityTypeDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, RsActivityType.class);
    }

    private QueryBuilder<RsActivityType, Integer> getQueryBuilder() {
        return queryBuilder().selectColumns("activityTypeId", "activityTypeName",
            "activityTypeDefaultLoyaltyScore", "activityTypeDefaultExpirienceScore",
            "activityTypeDefaultCommunicationScore");
    }

    @Override
    public Optional<RsActivityType> find(RsActivityType entry) throws DataSourceException {
        try {
            return Optional.ofNullable(
                    getQueryBuilder().where().idEq(entry.getId()).queryForFirst()
            );
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        }
    }

    @Override
    public Optional<List<RsActivityType>> getListActivityType() throws DataSourceException {
        try {
            return Optional.ofNullable(getQueryBuilder().orderBy("activityTypeName", true).query());
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        }
    }

    @Override
    public void modify(RsActivityType entry) throws DataSourceException {
        try {
            UpdateBuilder<RsActivityType, Integer> updateBuilder = updateBuilder();
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
    public Optional<RsActivityType> createActivityType(RsActivityType entry) throws DataSourceException {
        try {
            return Optional.ofNullable( createIfNotExists(entry));
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        }
    }
}
