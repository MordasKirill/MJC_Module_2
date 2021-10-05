package com.epam.esm.controller.listener;

import com.epam.esm.dao.connection.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listener implements ServletContextListener {
    private static final Logger LOG = Logger.getLogger(Listener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.log(Level.INFO, "ServletContextListener was created!");
        ConnectionPool.connectionPool = new ConnectionPool();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.log(Level.INFO, "ServletContextListener was destroyed!");
        ConnectionPool.connectionPool.dispose();
    }
}
