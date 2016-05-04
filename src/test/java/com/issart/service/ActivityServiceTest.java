package com.issart.service;

import com.issart.datasource.entity.*;
import com.issart.exception.DataSourceException;
import com.issart.exception.InvalidParameterException;
import com.issart.exception.ServiceException;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class ActivityServiceTest {
    static ActivityService activityService;
    static UserService userService;

    static RsActivityType rsActivityType1;
    static RsActivityType rsActivityType2;

    @BeforeClass
    public static void setUpClass() throws Exception {
        activityService = new ActivityService();
        userService = new UserService(new SecurityService());

        rsActivityType1 = activityService.createActivityType(RsActivityTypeBuilder.create()
                .rsActivityTypeId(-1)
                .activityTypeName("testA1")
                .activityTypeDefaultCommunicationScore(1)
                .activityTypeDefaultExpirienceScore(2)
                .activityTypeDefaultLoyaltyScore(3)
                .build());
        rsActivityType2 = activityService.createActivityType(RsActivityTypeBuilder.create()
                .rsActivityTypeId(-1)
                .activityTypeName("testB1")
                .activityTypeDefaultCommunicationScore(4)
                .activityTypeDefaultExpirienceScore(5)
                .activityTypeDefaultLoyaltyScore(6)
                .build());

        userService.createUser("fancyUserAdmin", "fancyPassword", true).get();
        userService.createUser("fancyUser", "fancyPassword", false).get();

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        activityService.close();
        userService.close();
        FileUtils.deleteDirectory(FileUtils.getFile(
                System.getProperty("user.home") + "/" + ".scores"
        ));
    }

    @After
    public void tearDown() throws Exception {
        userService.logout("fancyUserAdmin");
        userService.logout("fancyUser");
        userService.logout("fancyUser1");
        rsUser1 = null;
        rsUser2 = null;
        rsUser3 = null;
    }

    RsUser rsUser1;
    RsUser rsUser2;
    RsUser rsUser3;
    @Before
    public void setUp() throws Exception {
        rsUser1 = userService.getUser("fancyUserAdmin", "fancyPassword").get();
        rsUser2 = userService.getUser("fancyUser", "fancyPassword").get();
        rsUser3 = userService.createUser("fancyUser1", "fancyPassword", false).get();
    }

    @Test(expected = InvalidParameterException.class)
    public void testFaultScoresActivityService()  {
        activityService.createActivity(RsActivityBuilder.create()
                .rsActivityType(rsActivityType1)
                .rsActivityUser(rsUser1)
                .rsCreatedByUser(rsUser1)
                .rsActivityDate(DateTime.parse("2016-05-02"))
                .build());
    }

    @Test(expected = IllegalAccessException.class)
    public void testFaultActivityService()  {
        activityService.createActivity(RsActivityBuilder.create()
                .rsActivityType(rsActivityType1)
                .rsActivityUser(rsUser2)
                .rsCreatedByUser(rsUser2)
                .rsActivityTypeCommunicationScore(1)
                .rsActivityTypeExpirienceScore(2)
                .rsActivityTypeLoyaltyScore(3)
                .rsActivityDate(DateTime.parse("2016-05-02"))
                .build());
    }

    @Test
    public void testActivityService() throws Exception {
        RsActivity rsActivity1 = activityService.createActivity(RsActivityBuilder.create()
                .rsActivityType(rsActivityType1)
                .rsActivityUser(rsUser1)
                .rsCreatedByUser(rsUser1)
                .rsActivityDate(DateTime.parse("2016-05-02"))
                .rsActivityTypeCommunicationScore(1)
                .rsActivityTypeExpirienceScore(2)
                .rsActivityTypeLoyaltyScore(3)
                .build());
        RsActivity rsActivity2 = activityService.createActivity(RsActivityBuilder.create()
                .rsActivityType(rsActivityType2)
                .rsActivityUser(rsUser2)
                .rsCreatedByUser(rsUser1)
                .rsApprovedByUser(rsUser1)
                .rsActivityDate(DateTime.parse("2016-05-02"))
                .rsActivityTypeCommunicationScore(1)
                .rsActivityTypeExpirienceScore(2)
                .rsActivityTypeLoyaltyScore(3)
                .build());
        RsActivity rsActivity3 = activityService.createActivity(RsActivityBuilder.create()
                .rsActivityType(rsActivityType2)
                .rsActivityUser(rsUser3)
                .rsCreatedByUser(rsUser1)
                .rsActivityDate(DateTime.parse("2016-05-02"))
                .rsActivityTypeCommunicationScore(1)
                .rsActivityTypeExpirienceScore(2)
                .rsActivityTypeLoyaltyScore(3)
                .build());
        rsActivity1.setRsApprovedByUser(rsUser2);
        try{
            activityService.updateActivity(rsActivity1);
        } catch (IllegalAccessException ex){
            assert true;
        }

        List<RsActivity> activityList1 = activityService.getTotalScoresTypeByUserForPeriod(rsUser1, null, DateTime.parse("2016-05-05"));

        List<RsActivity> activityList2 = activityService.getTotalActivityTypeByUserForPeriod(rsUser1, null, DateTime.parse("2016-05-05"));

        List<RsActivity> activityList3 = activityService.getRSActivitiesByUserForPeriod(rsUser1, DateTime startDateTime, DateTime.parse("2016-05-05"));

        try{
            activityService.updateActivity(rsActivity1);
        } catch (IllegalAccessException ex){
            assert true;
        }
    }
}