package com.epam.esm.dao.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.entity.Certificate;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
class CertificatesDAOImplTest {
    @Autowired
    private CertificatesDAOImpl certificatesDAO;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void bef() throws FileNotFoundException, SQLException {
        RunScript.execute(dataSource.getConnection(), new FileReader(new File("src/test/resources/certificates_script.sql").getAbsolutePath()));
    }

    @AfterEach
    void after() throws SQLException, FileNotFoundException {
        RunScript.execute(dataSource.getConnection(), new FileReader(new File("src/test/resources/drop.sql").getAbsolutePath()));
    }

    @Test
    void createCertificate() throws DAOException {
        List<String> tagList = new ArrayList<>();
        tagList.add("food");
        Certificate certificate = new Certificate("hello!", 11.20, "desc", tagList, 5);
        certificatesDAO.createCertificate(certificate);
        Assertions.assertEquals(6, certificatesDAO.getCertificates().size());
    }

    @Test
    void getCertificates() throws DAOException {
        Assertions.assertEquals(5, certificatesDAO.getCertificates().size());
    }

    @Test
    void getCertificate() throws DAOException {
        Assertions.assertNotNull(certificatesDAO.getCertificate(5));
    }

    @Test
    void deleteCertificates() throws DAOException {
        certificatesDAO.deleteCertificates(9);
        Assertions.assertFalse(certificatesDAO.isCertificateExist(9));
    }

    @Test
    void updateCertificates() throws DAOException {
        certificatesDAO.updateCertificates(5, new Certificate("hello", 0.1, "test description", 5));
        Certificate certificate = new Certificate(5, "hello", 0.1, 5, new Date(), new Date(), "test description");
        Assertions.assertTrue(certificatesDAO.getCertificates().contains(certificate));
    }
}