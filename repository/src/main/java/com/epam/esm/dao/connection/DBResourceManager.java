package com.epam.esm.dao.connection;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBResourceManager {

    private static final Logger LOGGER = Logger.getLogger(DBResourceManager.class);
    public static DBResourceManager dbResourceManager;
    public final Properties properties = new Properties();

    public void loadProperties(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path).getAbsolutePath());
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Could not load property file!");
        }
    }
}

