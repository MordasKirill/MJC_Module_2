package com.epam.esm.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;

/**
 * common configuration for
 * spring context
 */
@ComponentScan("com.epam.esm")
@Configuration
@EnableTransactionManagement
@Import({ConfigDev.class, ConfigProd.class})
public class CommonConfig {

    @Autowired(required = false)
    private ConfigProd configProd;
    @Autowired(required = false)
    private ConfigDev configDev;

    @Bean
    @Profile("prod")
    public JdbcTemplate getJdbcTemplateProd() throws PropertyVetoException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(configProd.getDataSource());
        return jdbcTemplate;
    }

    @Bean
    @Profile("prod")
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(configProd.getDataSource());
        return tm;
    }

    @Bean
    @Profile("dev")
    public JdbcTemplate getJdbcTemplateDevProd() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(configDev.getDataSource());
        return jdbcTemplate;
    }

    @Bean
    @Profile("dev")
    public PlatformTransactionManager transactionManagerDev() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(configDev.getDataSource());
        return tm;
    }
}
