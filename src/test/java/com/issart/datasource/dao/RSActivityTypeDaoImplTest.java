package com.issart.datasource.dao;

import com.issart.context.ApplicationContext;
import com.issart.datasource.DataSource;
import com.issart.datasource.entity.RsActivityType;
import com.issart.datasource.entity.RsActivityTypeBuilder;
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

public class RSActivityTypeDaoImplTest {
    protected DataSource dataSource;
    protected ApplicationContext applicationContext;

    @Test
     public void testListActivityType() throws Exception {
        RsActivityType rsActivityType = RsActivityTypeBuilder.create()
                .rsActivityTypeId(-1)
                .activityTypeName("testA1")
                .activityTypeDefaultCommunicationScore(1)
                .activityTypeDefaultExpirienceScore(2)
                .activityTypeDefaultLoyaltyScore(3)
                .build();
        assertTrue(dataSource.getIRSActiveTypeDAO().createActivityType(rsActivityType).isPresent());
        RsActivityType rsActivityType1 = RsActivityTypeBuilder.create()
                .rsActivityTypeId(-1)
                .activityTypeName("testB1")
                .activityTypeDefaultCommunicationScore(2)
                .activityTypeDefaultExpirienceScore(3)
                .activityTypeDefaultLoyaltyScore(4)
                .build();
        assertTrue(dataSource.getIRSActiveTypeDAO().createActivityType(rsActivityType1).isPresent());
        assertTrue(dataSource.getIRSActiveTypeDAO().getListActivityType().isPresent());
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
