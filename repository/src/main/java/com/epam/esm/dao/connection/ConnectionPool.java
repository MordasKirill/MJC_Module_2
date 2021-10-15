package com.epam.esm.dao.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;

public final class ConnectionPool {
    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
    public static ConnectionPool connectionPool;
    public static ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
    private final String url;
    private final String password;
    private final String userName;
    private final String driver;
    private final int initConnCnt;

    public ConnectionPool() {
        this.driver = DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_DRIVER);
        this.url = DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_URL);
        this.userName = DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_USER);
        this.password = DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_PASSWORD);
        this.initConnCnt = Integer.parseInt(DBResourceManager.dbResourceManager.properties.getProperty(DBParameter.DB_POLL_SIZE));
    }

    public void initPool() throws ConnectionPoolException {
        try {
            pooledDataSource.setDriverClass(driver);
            pooledDataSource.setJdbcUrl(url);
            pooledDataSource.setUser(userName);
            pooledDataSource.setPassword(password);
            pooledDataSource.setMinPoolSize(initConnCnt);
            pooledDataSource.setMaxPoolSize(initConnCnt);
        } catch (PropertyVetoException e) {
            LOG.log(Level.ERROR, "FAIL POOL: Error init pool.", e);
            throw new ConnectionPoolException("Error init pool.", e);
        }
    }
}
