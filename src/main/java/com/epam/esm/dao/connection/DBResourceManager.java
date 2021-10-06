package com.epam.esm.dao.connection;

import java.util.ResourceBundle;

public class DBResourceManager {

    private final static DBResourceManager instance = new DBResourceManager();

    private final ResourceBundle bundle = ResourceBundle.getBundle("test");

    public static DBResourceManager getInstance() {
        return instance;
    }


    public String getValue(String key) {
        return bundle.getString(key);
    }
}

