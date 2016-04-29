package com.issart.datasource.dao;

import java.sql.SQLException;
import com.issart.datasource.entity.RSActivity;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Created by vurazov on 29.04.2016.
 */
public class RSActivityDaoImpl extends BaseDaoImpl<RSActivity, Integer> implements RSActivityDao {

    public RSActivityDaoImpl (ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, RSActivity.class);
    }

    private QueryBuilder<RSActivity, Integer> getQueryBuilder() {
        return queryBuilder().selectColumns("activityTypeId", "activityTypeName",
            "activityTypeDefaultLoyaltyScore", "activityTypeDefaultExpirienceScore",
            "activityTypeDefaultCommunicationScore");
    }
}
