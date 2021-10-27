package com.epam.esm.controller.config;

import com.epam.esm.dao.connection.DBParameter;
import com.epam.esm.dao.connection.DBResourceManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;

/**
 * configuration class for profile "prod"
 */
@ComponentScan("com.epam.esm")
@Configuration
@EnableTransactionManagement
@Profile("prod")
public class ConfigProd {

    @Bean
    public ComboPooledDataSource getDataSource() throws PropertyVetoException {
        DBResourceManager.dbResourceManager = new DBResourceManager();
        DBResourceManager.dbResourceManager.loadProperties("db.properties");
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_DRIVER));
        comboPooledDataSource.setJdbcUrl(DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_URL));
        comboPooledDataSource.setUser(DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_USER));
        comboPooledDataSource.setPassword(DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_PASSWORD));
        return comboPooledDataSource;
    }
}
