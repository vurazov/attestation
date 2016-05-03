package com.issart.datasource;

import com.issart.exception.DataSourceException;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.issart.datasource.dao.*;
import com.issart.datasource.entity.*;

import java.sql.SQLException;
/**
 * Class represent datasource to the local  database
 */
public class DataSource {

    private ConnectionSource connectionSource;
    private IRSUserDao rsUserDAO;
    private IRSActivityTypeDao rsActivityTypeDao;
    private IRSActivityDao rsActivityDao;

    public DataSource(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public IRSUserDao getIRSUserDAO() throws DataSourceException {
        if (rsUserDAO == null) {
            try {
                rsUserDAO = DaoManager.<IRSUserDao, RsUser>createDao(connectionSource, RsUser.class);
                if (!rsUserDAO.isTableExists())
                    TableUtils.createTable(connectionSource, RsUser.class);
            } catch (SQLException e) {
                throw new DataSourceException(e.getLocalizedMessage(), e);
            }
        }
        return rsUserDAO;
    }

    public IRSActivityTypeDao getIRSActiveTypeDAO() throws DataSourceException {
        if (rsActivityTypeDao == null) {
            try {
                rsActivityTypeDao = DaoManager.<IRSActivityTypeDao, RsActivityType>createDao(connectionSource, RsActivityType.class);
                if (!rsActivityTypeDao.isTableExists())
                    TableUtils.createTable(connectionSource, RsActivityType.class);
            } catch (SQLException e) {
                throw new DataSourceException(e.getLocalizedMessage(), e);
            }
        }
        return rsActivityTypeDao;
    }

    public IRSActivityDao getIRSActivityDao() throws DataSourceException {
        if (rsActivityDao == null) {
            try {
                rsActivityDao = DaoManager.<IRSActivityDao, RsActivity>createDao(connectionSource, RsActivity.class);
                if (!rsActivityDao.isTableExists())
                    TableUtils.createTable(connectionSource, RsActivity.class);
            } catch (SQLException e) {
                throw new DataSourceException(e.getLocalizedMessage(), e);
            }
        }
        return rsActivityDao;
    }
}
