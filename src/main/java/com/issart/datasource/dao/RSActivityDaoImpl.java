package com.issart.datasource.dao;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.issart.datasource.entity.RsActivity;
import com.issart.datasource.entity.RsActivityType;
import com.issart.datasource.entity.RsUser;
import com.issart.exception.DataSourceException;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.CloseableWrappedIterable;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Created by vurazov on 29.04.2016.
 */
public class RSActivityDaoImpl extends BaseDaoImpl<RsActivity, Integer> implements IRSActivityDao {

    public RSActivityDaoImpl (ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, RsActivity.class);
    }

    private QueryBuilder<RsActivity, Integer> getQueryBuilder() {
        return queryBuilder().selectColumns("id", "rsActivityDate",
            "rsActivityUser", "rsCreatedByUser", "rsApprovedByUser",
            "rsActivityType", "rsActivityTypeLoyaltyScore", "rsActivityTypeExpirienceScore",
            "rsActivityTypeCommunicationScore");
    }

    @Override
    public Optional<RsActivity> find(RsActivity entry) throws DataSourceException {
        try {
            return Optional.ofNullable(
                    getQueryBuilder().where().idEq(entry.getId()).queryForFirst()
            );
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        }
    }

    @Override
    public void modify(RsActivity entry) throws DataSourceException {
        try {
            UpdateBuilder<RsActivity, Integer> updateBuilder = updateBuilder();
            updateBuilder.updateColumnValue("rsActivityDate",  entry.getRsActivityDate());
            updateBuilder.updateColumnValue("rsActivityUser", entry.getRsActivityUser());
            updateBuilder.updateColumnValue("rsCreatedByUser", entry.getRsCreatedByUser());
            updateBuilder.updateColumnValue("rsApprovedByUser", entry.getRsApprovedByUser());
            updateBuilder.updateColumnValue("rsActivityType", entry.getRsActivityType());
            updateBuilder.updateColumnValue("rsActivityTypeLoyaltyScore", entry.getRsActivityTypeLoyaltyScore());
            updateBuilder.updateColumnValue("rsActivityTypeExpirienceScore", entry.getRsActivityTypeExpirienceScore());
            updateBuilder.updateColumnValue("rsActivityTypeCommunicationScore", entry.getRsActivityTypeCommunicationScore());
            updateBuilder.where().idEq(entry.getId());
            updateBuilder.update();
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        }
    }

    @Override
    public Optional<RsActivity> createActivity(RsActivity entry) throws DataSourceException {
        try {
            return Optional.ofNullable(createIfNotExists(entry));
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        }
    }

    @Override
    public List<RsActivity> getScoresRSActivitiesByUserForPeriod(RsUser entry, DateTime startDateTime, DateTime endDateTime) throws DataSourceException {
        GenericRawResults<RsActivity> aRsActivities = null;
        List<RsActivity> rsActivities = new ArrayList<>();
        try {
            aRsActivities = queryRaw(MessageFormat.format(" SELECT rsActivityType, rsActivityUser," +
                            "    SUM(rsActivityTypeLoyaltyScore) rsActivityTypeLoyaltyScore," +
                            "    SUM(rsActivityTypeExpirienceScore) rsActivityTypeExpirienceScore," +
                            "    SUM(rsActivityTypeCommunicationScore) rsActivityTypeCommunicationScore" +
                            " WHERE rsActivityUser_ID = {0} AND  rsApprovedByUser_ID IS NOT NULL" +
                            "    AND rsActivityDate >= {1} AND rsActivityDate <= {2}" +
                            " GROUP BY rsActivityType" +
                            " UNION ALL " +
                            " SELECT rsActivityType, rsActivityUser," +
                            "    SUM(rsActivityTypeLoyaltyScore) rsActivityTypeLoyaltyScore," +
                            "    SUM(rsActivityTypeExpirienceScore) rsActivityTypeExpirienceScore," +
                            "    SUM(rsActivityTypeCommunicationScore) rsActivityTypeCommunicationScore" +
                            " WHERE rsActivityUser_ID = {0} AND  rsApprovedByUser_ID IS NULL" +
                            "    AND rsActivityDate >= {1} AND rsActivityDate <= {2}" +
                            " GROUP BY rsActivityType"
                    , entry.getId(), startDateTime, endDateTime)
                    , new RsActivityMapper());
            rsActivities.addAll(aRsActivities.getResults());
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        } finally {
            closeQuietly(aRsActivities);
        }
        return rsActivities;
    }

    private static <T> void closeQuietly(CloseableWrappedIterable<T> results) {
        if (results != null) {
            try {
                results.close();
            } catch (SQLException e) {

            }
        }
    }

    @Override
    public Optional<List<RsActivity>> getRSActivitiesByUserForPeriod(RsUser entry, DateTime startDateTime, DateTime endDateTime) throws DataSourceException {
        try {
            return Optional.ofNullable(
                    getQueryBuilder().where().eq("rsuser", entry)
                            .ge("rsActivityDate", startDateTime)
                            .and().le("rsActivityDate", endDateTime)
                            .query()

            );
        } catch (SQLException e) {
            throw new DataSourceException(e.getLocalizedMessage());
        }
    }

    private static class RsActivityMapper implements RawRowMapper<RsActivity> {
        @Override
        public RsActivity mapRow(String[] columnNames, String[] resultColumns) throws SQLException {
        RsActivity rsActivity = new RsActivity();

        for (int i = 0; i < columnNames.length; i++) {
            if (StringUtils.equalsIgnoreCase(columnNames[i], "ID")) {
                if(StringUtils.isNotBlank(resultColumns[i]))
                    rsActivity.setId(Integer.parseInt(resultColumns[i]));
            } else if (StringUtils.equalsIgnoreCase(columnNames[i], "rsActivityDate")) {
                if(StringUtils.isNotBlank(resultColumns[i]))
                    rsActivity.setRsActivityDate(new DateTime(Long.parseLong(resultColumns[i]), DateTimeZone.UTC));
            } else if (StringUtils.equalsIgnoreCase(columnNames[i], "rsActivityUser_ID")) {
                if (StringUtils.isNotBlank(resultColumns[i])) {
                    RsUser rsUser = new RsUser();
                    rsUser.setId(Integer.parseInt(resultColumns[i]));
                    rsActivity.setRsActivityUser(rsUser);
                }
            } else if (StringUtils.equalsIgnoreCase(columnNames[i], "rsActivityType_ID")) {
                if (StringUtils.isNotBlank(resultColumns[i])) {
                    RsActivityType rsActivityType = new RsActivityType();
                    rsActivityType.setId(Integer.parseInt(resultColumns[i]));
                    rsActivity.setRsActivityType(rsActivityType);
                }
            } else if (StringUtils.equalsIgnoreCase(columnNames[i], "rsCreatedByUser_ID")) {
                if (StringUtils.isNotBlank(resultColumns[i])) {
                    RsUser rsUser = new RsUser();
                    rsUser.setId(Integer.parseInt(resultColumns[i]));
                    rsActivity.setRsActivityUser(rsUser);
                }
            } else if (StringUtils.equalsIgnoreCase(columnNames[i], "rsApprovedByUser_ID")) {
                if (StringUtils.isNotBlank(resultColumns[i])) {
                    RsUser rsUser = new RsUser();
                    rsUser.setId(Integer.parseInt(resultColumns[i]));
                    rsActivity.setRsActivityUser(rsUser);
                }
            } if (StringUtils.equalsIgnoreCase(columnNames[i], "rsActivityTypeLoyaltyScore")) {
                if(StringUtils.isNotBlank(resultColumns[i]))
                    rsActivity.setId(Integer.parseInt(resultColumns[i]));
            } if (StringUtils.equalsIgnoreCase(columnNames[i], "rsActivityTypeExpirienceScore")) {
                if(StringUtils.isNotBlank(resultColumns[i]))
                    rsActivity.setId(Integer.parseInt(resultColumns[i]));
            } if (StringUtils.equalsIgnoreCase(columnNames[i], "rsActivityTypeCommunicationScore")) {
                if(StringUtils.isNotBlank(resultColumns[i]))
                    rsActivity.setId(Integer.parseInt(resultColumns[i]));
            }
        }
        return rsActivity;
      }
    }
  }

