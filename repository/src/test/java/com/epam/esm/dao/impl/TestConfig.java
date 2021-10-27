package com.epam.esm.dao.impl;

import com.epam.esm.dao.connection.DBParameter;
import com.epam.esm.dao.connection.DBResourceManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.beans.PropertyVetoException;

@ComponentScan("com.epam.esm")
@Configuration
public class TestConfig {
    @Bean
    public ComboPooledDataSource getDataSource() throws PropertyVetoException {
        DBResourceManager.dbResourceManager = new DBResourceManager();
        DBResourceManager.dbResourceManager.loadProperties("test.properties");
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_DRIVER));
        comboPooledDataSource.setJdbcUrl(DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_URL));
        comboPooledDataSource.setUser(DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_USER));
        comboPooledDataSource.setPassword(DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_PASSWORD));
        return comboPooledDataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() throws PropertyVetoException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        return jdbcTemplate;
    }


    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        DataSourceTransactionManager tm =
                new DataSourceTransactionManager();
        tm.setDataSource(getDataSource());
        return tm;
    }
}
