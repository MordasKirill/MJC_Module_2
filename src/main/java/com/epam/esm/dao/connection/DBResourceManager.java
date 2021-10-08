package com.epam.esm.dao.connection;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBResourceManager {

    private static final Logger LOGGER = Logger.getLogger(DBResourceManager.class);
    private final static DBResourceManager instance = new DBResourceManager();
    public static DBResourceManager dbResourceManager;
    private final ResourceBundle bundle = ResourceBundle.getBundle("test");
    public FileInputStream fileInputStream;
    public Properties properties = new Properties();

    public static DBResourceManager getInstance() {
        return instance;
    }

    public void loadProperties(String path) {
        try {
            fileInputStream = new FileInputStream(new File(path).getAbsolutePath());
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Could not load property file!");
        }
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}

