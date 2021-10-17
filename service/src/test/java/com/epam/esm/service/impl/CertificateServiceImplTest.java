package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.CertificatesDAOImpl;
import com.epam.esm.entity.Certificate;
import com.epam.esm.service.ServiceException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CertificateServiceImplTest {
    private static final String patternHhMm = "yyyy-MM-dd'T'HH:mm'Z'";
    private static final Certificate notValidCertificate = new Certificate("test", 0.0, 0, null, null, null);
    private static final Certificate validCertificate = new Certificate(1, "test", 1.0, 2, "gege", "gegr", "geg");
    private static final CertificatesDAOImpl certificatesDAO = Mockito.mock(CertificatesDAOImpl.class);
    private static final CertificateServiceImpl certificateService = new CertificateServiceImpl(certificatesDAO);

    @Mock
    CertificatesDAOImpl dao;
    @InjectMocks
    CertificateServiceImpl service = new CertificateServiceImpl(dao);

    @Test
    void createCertificatesOk() throws ServiceException, DAOException {
        CertificatesDAOImpl certificatesDAO = Mockito.mock(CertificatesDAOImpl.class);
        CertificateServiceImpl certificateService = new CertificateServiceImpl(certificatesDAO);
        certificateService.createCertificate(validCertificate);
        Mockito.verify(certificatesDAO).createCertificates(validCertificate);
    }

    @Test
    void createCertificatesNotValidParam() {
        assertThrows(ServiceException.class, () -> service.createCertificate(notValidCertificate), "CreateCertificate fail due to null value of params.");
    }

    @Test
    void createCertificatesExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificatesDAO).createCertificates(validCertificate);
        assertThrows(ServiceException.class, () -> certificateService.createCertificate(validCertificate), "CreateCertificate fail due to null value of params.");
    }

    @Test
    void deleteCertificates() throws Exception {
        CertificatesDAOImpl certificatesDAO = Mockito.mock(CertificatesDAOImpl.class);
        CertificateServiceImpl certificateService = new CertificateServiceImpl(certificatesDAO);
        certificateService.deleteCertificate(validCertificate);
        Mockito.verify(certificatesDAO).deleteCertificates(validCertificate);
    }

    @Test
    void deleteCertificatesExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificatesDAO).deleteCertificates(validCertificate);
        assertThrows(ServiceException.class, () -> certificateService.deleteCertificate(validCertificate), "CreateCertificate fail due to null value of params.");
    }

    @Test
    void deleteCertificatesNotValidParam() {
        assertThrows(ServiceException.class, () -> service.deleteCertificate(notValidCertificate), "DeleteCertificate fail due to null value of params.");
    }

    @Test
    void updateCertificates() throws Exception {
        certificateService.updateCertificate(validCertificate);
        Mockito.verify(certificatesDAO).updateCertificates(validCertificate);
    }

    @Test
    void updateCertificatesExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificatesDAO).updateCertificates(validCertificate);
        assertThrows(ServiceException.class, () -> certificateService.updateCertificate(validCertificate), "UpdateCertificate fail");
    }

    @Test
    void getCertificates() throws ServiceException, DAOException {
        CertificatesDAOImpl certificatesDAO = Mockito.mock(CertificatesDAOImpl.class);
        CertificateServiceImpl certificateService = new CertificateServiceImpl(certificatesDAO);
        certificateService.getCertificates();
        Mockito.verify(certificatesDAO).getCertificates();
    }

    @Test
    void getCertificatesExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificatesDAO).getCertificates();
        assertThrows(ServiceException.class, certificateService::getCertificates, "GetCertificates fail");
    }
}