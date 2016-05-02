package com.issart.datasource.dao;

import com.issart.datasource.entity.RsActivity;
import com.issart.datasource.entity.RsUser;
import com.issart.exception.DataSourceException;
import com.j256.ormlite.dao.Dao;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Optional;

/**
 * Created by vurazov on 29.04.2016.
 */
public interface IRSActivityDao extends Dao<RsActivity, Integer> {

    Optional<RsActivity> find(RsActivity entry) throws DataSourceException;

    List<RsActivity> getScoresRSActivitiesByUserForPeriod(RsUser entry, DateTime startDateTime, DateTime endDateTime) throws DataSourceException;

    Optional<List<RsActivity>> getRSActivitiesByUserForPeriod(RsUser entry, DateTime startDateTime, DateTime endDateTime) throws DataSourceException;

    void modify(RsActivity entry) throws DataSourceException;

    Optional<RsActivity> createActivity(RsActivity entry) throws DataSourceException;
}
