package com.epam.esm.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;

@ComponentScan("com.epam.esm")
@Configuration
@EnableTransactionManagement
@Import({ConfigDev.class, ConfigProd.class})
public class CommonConfig {

    @Autowired(required = false)
    ConfigProd config;
    @Autowired(required = false)
    ConfigDev configDev;

    @Bean
    @Profile("prod")
    public JdbcTemplate getJdbcTemplateProd() throws PropertyVetoException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(config.getDataSource());
        return jdbcTemplate;
    }

    @Bean
    @Profile("prod")
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(config.getDataSource());
        return tm;
    }

    @Bean
    @Profile("dev")
    public JdbcTemplate getJdbcTemplateDevProd() throws PropertyVetoException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(config.getDataSource());
        return jdbcTemplate;
    }

    @Bean
    @Profile("dev")
    public PlatformTransactionManager transactionManagerDev() throws PropertyVetoException {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(config.getDataSource());
        return tm;
    }
}
