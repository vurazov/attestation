package com.issart.datasource.dao;

import com.issart.context.ApplicationContext;
import com.issart.datasource.DataSource;
import com.issart.datasource.entity.*;
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
    List<RsActivity> getTotalScoresTypeByUserForPeriod(RsUser entry, DateTime startDateTime, DateTime endDateTime) throws DataSourceException;

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
        rsActivityType2 = dataSource.getIRSActiveTypeDAO().createActivityType(rsActivityType2).get();
        assertEquals(dataSource.getIRSActiveTypeDAO().getListActivityType().get().size(), 2);

        dataSource.getIRSActivityDao().createActivity(RsActivityBuilder.create()
                .rsActivityType(rsActivityType1)
                .rsActivityUser(rsUser1)
                .rsCreatedByUser(rsUser2)
                .rsActivityDate(DateTime.parse("2016-05-01"))
                .rsActivityTypeCommunicationScore(1)
                .rsActivityTypeExpirienceScore(2)
                .rsActivityTypeLoyaltyScore(3)
                .build());
        dataSource.getIRSActivityDao().createActivity(RsActivityBuilder.create()
                .rsActivityType(rsActivityType2)
                .rsActivityUser(rsUser1)
                .rsApprovedByUser(rsUser2)
                .rsCreatedByUser(rsUser2)
                .rsActivityDate(DateTime.parse("2016-05-01"))
                .rsActivityTypeCommunicationScore(1)
                .rsActivityTypeExpirienceScore(2)
                .rsActivityTypeLoyaltyScore(3)
                .build());
        dataSource.getIRSActivityDao().createActivity(RsActivityBuilder.create()
                .rsActivityType(rsActivityType2)
                .rsActivityUser(rsUser1)
                .rsApprovedByUser(rsUser2)
                .rsCreatedByUser(rsUser2)
                .rsActivityDate(DateTime.parse("2016-05-01"))
                .rsActivityTypeCommunicationScore(3)
                .rsActivityTypeExpirienceScore(4)
                .rsActivityTypeLoyaltyScore(5)
                .build());
    //----------------------------------------------------------------------------------
        dataSource.getIRSActivityDao().createActivity(RsActivityBuilder.create()
                .rsActivityType(rsActivityType1)
                .rsActivityUser(rsUser2)
                .rsCreatedByUser(rsUser1)
                .rsActivityDate(DateTime.parse("2016-05-02"))
                .rsActivityTypeCommunicationScore(1)
                .rsActivityTypeExpirienceScore(2)
                .rsActivityTypeLoyaltyScore(3)
                .build());
        dataSource.getIRSActivityDao().createActivity(RsActivityBuilder.create()
                .rsActivityType(rsActivityType2)
                .rsActivityUser(rsUser2)
                .rsApprovedByUser(rsUser1)
                .rsCreatedByUser(rsUser1)
                .rsActivityDate(DateTime.parse("2016-05-02"))
                .rsActivityTypeCommunicationScore(1)
                .rsActivityTypeExpirienceScore(2)
                .rsActivityTypeLoyaltyScore(3)
                .build());
        Optional<List<RsActivity>> activities1 = dataSource.getIRSActivityDao()
                .getRSActivitiesByUserForPeriod(rsUser1, DateTime.parse("2016-01-01"), DateTime.parse("2016-05-01"));
        assertTrue(activities1.isPresent());
        assertEquals(activities1.get().size(), 3);

        Optional<List<RsActivity>> activities2 = dataSource.getIRSActivityDao()
                .getRSActivitiesByUserForPeriod(rsUser2, DateTime.parse("2016-01-01"), DateTime.parse("2016-05-02"));
        assertTrue(activities2.isPresent());
        assertEquals(activities2.get().size(), 2);

        List<RsActivity> rsActivities1 = dataSource.getIRSActivityDao().getTotalScoresTypeByUserForPeriod(rsUser1, DateTime.parse("2016-01-01"), DateTime.parse("2016-05-01"));
        assertEquals(rsActivities1.size(), 2);
        List<RsActivity> rsActivities2 = dataSource.getIRSActivityDao().getTotalScoresTypeByUserForPeriod(rsUser2, DateTime.parse("2016-01-01"), DateTime.parse("2016-05-02"));
        assertEquals(rsActivities2.size(), 2);
        List<RsActivity> rsActivities3 = dataSource.getIRSActivityDao().getTotalScoresTypeByUserForPeriod(rsUser1, DateTime.parse("2016-01-01"), DateTime.parse("2016-05-01"));
        assertEquals(rsActivities3.size(), 2);
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
