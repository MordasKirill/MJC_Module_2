package com.epam.esm.controller.listener;

import com.epam.esm.dao.connection.ConnectionPool;
import com.epam.esm.dao.connection.DBResourceManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ServletContextListener
 * set up a connection pool when contextInitialized
 * dispose connection poll when contextDestroyed
 */
public class Listener implements ServletContextListener {
    private static final Logger LOG = Logger.getLogger(Listener.class);

    /**
     * set up a connection pool when contextInitialized
     *
     * @param servletContextEvent servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.log(Level.INFO, "ServletContextListener was created!");
        DBResourceManager.dbResourceManager = new DBResourceManager();
        DBResourceManager.dbResourceManager.loadProperties("src/main/resources/db.properties");
        ConnectionPool.connectionPool = new ConnectionPool();
    }

    /**
     * dispose connection poll when contextDestroyed
     *
     * @param servletContextEvent servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.log(Level.INFO, "ServletContextListener was destroyed!");
        ConnectionPool.connectionPool.dispose();
    }
}
