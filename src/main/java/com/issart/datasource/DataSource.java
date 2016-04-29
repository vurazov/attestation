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
    private IRSUserDao IRSUserDAO;

    public DataSource(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public IRSUserDao getIRSUserDAO() throws DataSourceException {
        if (IRSUserDAO == null) {
            try {
                IRSUserDAO = DaoManager.<IRSUserDao, RsUser>createDao(connectionSource, RsUser.class);
                if (!IRSUserDAO.isTableExists())
                    TableUtils.createTable(connectionSource, RsUser.class);
            } catch (SQLException e) {
                throw new DataSourceException(e.getLocalizedMessage(), e);
            }
        }
        return IRSUserDAO;
    }

}
