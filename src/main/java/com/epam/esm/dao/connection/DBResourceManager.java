package com.epam.esm.dao.connection;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBResourceManager {

    public static DBResourceManager dbResourceManager;
    public FileInputStream fileInputStream;
    public Properties properties = new Properties();
    private static final Logger LOGGER = Logger.getLogger(DBResourceManager.class);

    public void loadProperties(String path){
        try {
            fileInputStream = new FileInputStream(new File(path).getAbsolutePath());
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Could not load property file!");
        }
    }

    private final static DBResourceManager instance = new DBResourceManager();

    private final ResourceBundle bundle = ResourceBundle.getBundle("test");

    public static DBResourceManager getInstance() {
        return instance;
    }


    public String getValue(String key) {
        return bundle.getString(key);
    }
}

