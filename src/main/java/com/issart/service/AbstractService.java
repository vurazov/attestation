package com.issart.service;

import com.issart.context.ApplicationContext;
import com.issart.datasource.DataSource;
import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractService implements AutoCloseable {
    protected DataSource dataSource;
    protected ApplicationContext applicationContext;
    protected static final Logger log = LoggerFactory.getLogger(AbstractService.class);

    public AbstractService() {
        this.applicationContext = ApplicationContext.getApplicationContext();
        try {
            this.dataSource = new DataSource(this.applicationContext.getConnectionSource());
        } catch (ConfigurationException e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }
    public void close() throws Exception{
        applicationContext.close();
    }
}
