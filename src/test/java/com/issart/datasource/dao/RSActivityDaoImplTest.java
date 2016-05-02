package com.issart.datasource.dao;

import com.issart.context.ApplicationContext;
import com.issart.datasource.DataSource;
import com.issart.datasource.entity.RsActivityType;
import com.issart.datasource.entity.RsActivityTypeBuilder;
import com.issart.datasource.entity.RsUserBuilder;
import com.issart.datasource.entity.RsUser;
import com.issart.exception.DataSourceException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RSActivityDaoImplTest {
    protected DataSource dataSource;
    protected ApplicationContext applicationContext;

/*
    List<RsActivity> getScoresRSActivitiesByUserForPeriod(RsUser entry, DateTime startDateTime, DateTime endDateTime) throws DataSourceException;

    Optional<List<RsActivity>> getRSActivitiesByUserForPeriod(RsUser entry, DateTime startDateTime, DateTime endDateTime) throws DataSourceException;

* */

    @Test
    public void testRSActivitiesByUserForPeriod() {


    }

    @Test
    public void testScoresRSActivitiesByUserForPeriod() throws DataSourceException {
        RsUser rsUser1 = RsUserBuilder.create()
                .userId(-1)
                .userName("test")
                .userPasswordHash("test")
                .userPasswordSalt("test")
                .userHireDate(DateTime.now())
                .userDismissDate(new DateTime(0))
                .userIsAdministrator(false)
                .build();

        RsUser rsUser2 = RsUserBuilder.create()
                .userId(-1)
                .userName("test")
                .userPasswordHash("test")
                .userPasswordSalt("test")
                .userHireDate(DateTime.now())
                .userDismissDate(new DateTime(0))
                .userIsAdministrator(true)
                .build();

        rsUser1 = dataSource.getIRSUserDAO().createUser(rsUser1).get();
        rsUser2 = dataSource.getIRSUserDAO().createUser(rsUser2).get();

        RsActivityType rsActivityType1 = RsActivityTypeBuilder.create()
                .rsActivityTypeId(-1)
                .activityTypeName("testA1")
                .activityTypeDefaultCommunicationScore(1)
                .activityTypeDefaultExpirienceScore(2)
                .activityTypeDefaultLoyaltyScore(3)
                .build();
        rsActivityType1 = dataSource.getIRSActiveTypeDAO().createActivityType(rsActivityType1).get();
        RsActivityType rsActivityType2 = RsActivityTypeBuilder.create()
                .rsActivityTypeId(-1)
                .activityTypeName("testB1")
                .activityTypeDefaultCommunicationScore(2)
                .activityTypeDefaultExpirienceScore(3)
                .activityTypeDefaultLoyaltyScore(4)
                .build();
        rsActivityType2 = dataSource.getIRSActiveTypeDAO().createActivityType(rsActivityType1).get();
        assertEquals(dataSource.getIRSActiveTypeDAO().getListActivityType().get().size(), 2);

    }

    @Before
    public void setUp() throws Exception {
        this.applicationContext = ApplicationContext.getApplicationContext();
        try {
            this.dataSource = new DataSource(this.applicationContext.getConnectionSource());
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
        FileUtils.deleteDirectory(FileUtils.getFile(
                System.getProperty("user.home") + "/" + ".scores"
        ));
    }
}
