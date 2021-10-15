package com.epam.esm.repository.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.connection.ConnectionPool;
import com.epam.esm.dao.connection.DBResourceManager;
import com.epam.esm.dao.impl.CRUDOperationsDAOImpl;
import com.epam.esm.dao.impl.CertificatesDAOImpl;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.List;

class CRUDOperationsDAOImplTest {

    private static final String DELETE_FROM_CERTIFICATE = "delete from certificate where id=?";
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASS = "";
    private static Connection connection = null;

    @Mock
    private CRUDOperationsDAOImpl crudOperationsDAO = new CRUDOperationsDAOImpl();
    @InjectMocks
    CertificatesDAOImpl certificatesDAO = new CertificatesDAOImpl(crudOperationsDAO);

    @BeforeAll
    static void setConnectionPool() throws Exception {
        DBResourceManager.dbResourceManager = new DBResourceManager();
        DBResourceManager.dbResourceManager.loadProperties("src/test/resources/test.properties");
        ConnectionPool.connectionPool = new ConnectionPool();
        ConnectionPool.connectionPool.initPool();
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        RunScript.execute(connection, new FileReader(new File("src/test/resources/certificates_script.sql").getAbsolutePath()));
    }

    @AfterAll
    static void tearDown() throws Exception {
        RunScript.execute(connection, new FileReader(new File("src/test/resources/drop.sql").getAbsolutePath()));
    }

    @Test
    void executeUpdate() throws DAOException {
        List<Object> params = new LinkedList<>();
        params.add(5);
        crudOperationsDAO.executeUpdate(DELETE_FROM_CERTIFICATE, params);
        List<Object> params1 = new LinkedList<>();
        params1.add(4);
        crudOperationsDAO.executeUpdate(DELETE_FROM_CERTIFICATE, params1);
        Assertions.assertEquals(3, certificatesDAO.getCertificates().size());
    }
}