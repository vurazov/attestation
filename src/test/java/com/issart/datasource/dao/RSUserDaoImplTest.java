package com.issart.datasource.dao;

import com.issart.context.ApplicationContext;
import com.issart.datasource.DataSource;
import com.issart.datasource.entity.RsUserBuilder;
import com.issart.datasource.entity.RsUser;
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

/**
 * Created by vurazov on 29.04.2016.
 */
public class RSUserDaoImplTest {
    protected DataSource dataSource;
    protected ApplicationContext applicationContext;

    @Test
    public void testUserDao() throws Exception {
        RsUser rsUser1 = RsUserBuilder.create()
                .userId(-1)
                .userName("test")
                .userPasswordHash("test")
                .userPasswordSalt("test")
                .userHireDate(DateTime.now())
                .userDismissDate(new DateTime(0))
                .userIsAdministrator(true)
                .build();

        RsUser rsUser2 = RsUserBuilder.create()
                .userId(-1)
                .userName("test1")
                .userPasswordHash("test1")
                .userPasswordSalt("test1")
                .userHireDate(DateTime.now())
                .userDismissDate(DateTime.now())
                .userIsAdministrator(true)
                .build();

        assertTrue(dataSource.getIRSUserDAO().createUser(rsUser1).isPresent());
        assertTrue(dataSource.getIRSUserDAO().find(rsUser1).isPresent());
        assertTrue(dataSource.getIRSUserDAO().createUser(rsUser2).isPresent());
        assertTrue(dataSource.getIRSUserDAO().find(rsUser2).isPresent());

        Optional<List<RsUser>> users = dataSource.getIRSUserDAO().getUnDismissedUsers();
        assertTrue(users.isPresent());
        assertEquals(users.get().size(), 1);
        assertEquals(dataSource.getIRSUserDAO().queryForAll().size(), 2);
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