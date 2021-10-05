package com.epam.esm.repository;

import com.epam.esm.dao.connection.ConnectionPool;
import com.epam.esm.dao.impl.CRUDOperationsDAOImpl;
import com.epam.esm.dao.impl.CertificatesDAOImpl;
import com.epam.esm.entity.Certificate;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CertificatesDAOImplTest {
    private static final String CREATE_CERTIFICATE = "insert into gift_certificate (name, description, price, duration, create_date, last_update_date) values (?,?,?,?,?,?)";
    private static final String SELECT_FROM_GIFT_CERTIFICATE = "select * from gift_certificate";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_CREATE_DATE = "create_date";
    private static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASS = "";
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    @Mock
    private CRUDOperationsDAOImpl commonCRUDOperations;
    @InjectMocks
    CertificatesDAOImpl certificatesDAO = new CertificatesDAOImpl(commonCRUDOperations);

    @BeforeAll
    static void setConnectionPool() throws Exception {
        ConnectionPool.connectionPool = new ConnectionPool();
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        RunScript.execute(connection, new FileReader("C:\\Users\\Kirill\\IdeaProjects\\MJC_2\\src\\test\\resources\\certificates_script.sql"));
    }

    @AfterAll
    static void tearDown() throws Exception {
        RunScript.execute(connection, new FileReader("C:\\Users\\Kirill\\IdeaProjects\\MJC_2\\src\\test\\resources\\drop.sql"));
    }

    @Test
    void createCertificates() throws Exception {
        preparedStatement = connection.prepareStatement(CREATE_CERTIFICATE);
        preparedStatement.setString(1, "It's a test");
        preparedStatement.setString(2, "It's a description");
        preparedStatement.setDouble(3, 11.20);
        preparedStatement.setInt(4, 6);
        preparedStatement.setString(5, "2021-10-04");
        preparedStatement.setString(6, "2021-10-04");
        preparedStatement.executeUpdate();
        //certificatesDAO.createCertificates(new Certificate("It's a test", 11.20, 6, "2021-10-04", "2021-10-04", "It's a description"));
    }

    @Test
    void getCertificates() throws Exception {
        preparedStatement = connection.prepareStatement(SELECT_FROM_GIFT_CERTIFICATE);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Certificate> certificates = new ArrayList<>();
        while (resultSet.next()) {
            Certificate certificate = new Certificate();
            certificate.setId(resultSet.getInt(COLUMN_ID));
            certificate.setName(resultSet.getString(COLUMN_NAME));
            certificate.setDescription(resultSet.getString(COLUMN_DESCRIPTION));
            certificate.setPrice(resultSet.getDouble(COLUMN_PRICE));
            certificate.setDuration(resultSet.getInt(COLUMN_DURATION));
            certificate.setCreateDate(resultSet.getString(COLUMN_CREATE_DATE));
            certificate.setLastUpdateDate(resultSet.getString(COLUMN_LAST_UPDATE_DATE));
            certificates.add(certificate);
        }
        assertEquals(certificates.size(), certificatesDAO.getCertificates().size());
    }

    @Test
    void deleteCertificates() {
    }

    @Test
    void updateCertificates() {
    }
}