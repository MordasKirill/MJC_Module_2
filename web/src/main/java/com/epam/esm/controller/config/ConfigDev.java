package com.epam.esm.controller.config;

import com.epam.esm.dao.connection.DBParameter;
import com.epam.esm.dao.connection.DBResourceManager;
import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * configuration class for profile "dev"
 */
@ComponentScan("com.epam.esm")
@Configuration
@EnableTransactionManagement
@Profile("dev")
public class ConfigDev {

    @Bean
    public JdbcConnectionPool getDataSource() {
        DBResourceManager.dbResourceManager = new DBResourceManager();
        DBResourceManager.dbResourceManager.loadProperties("test.properties");
        return JdbcConnectionPool.create(DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_URL), DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_USER),
                DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_PASSWORD));
    }
}
