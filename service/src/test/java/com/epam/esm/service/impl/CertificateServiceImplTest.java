package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.CertificatesDAOImpl;
import com.epam.esm.entity.Certificate;
import com.epam.esm.service.ServiceException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;


class CertificateServiceImplTest {
    private static final Certificate notValidCertificate = new Certificate("test", 0.0, 0, null, null, null);
    private static final Certificate validCertificate = new Certificate(1, "test", 1.0, 2, "gege", "gegr", "geg");
    private final CertificatesDAOImpl certificatesDAO = Mockito.mock(CertificatesDAOImpl.class);
    private final CertificateServiceImpl certificateService = new CertificateServiceImpl(certificatesDAO);

    @Test
    void createCertificatesOk() throws ServiceException, DAOException {
        certificateService.createCertificate(validCertificate);
        Mockito.verify(certificatesDAO).createCertificate(validCertificate);
    }

    @Test
    void createCertificatesNotValidParam() {
        assertThrows(ServiceException.class, () -> certificateService.createCertificate(notValidCertificate), "CreateCertificate fail due to null value of params.");
    }

    @Test
    void createCertificatesExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificatesDAO).createCertificate(validCertificate);
        assertThrows(ServiceException.class, () -> certificateService.createCertificate(validCertificate), "CreateCertificate fail due to null value of params.");
    }

    @Test
    void deleteCertificates() throws Exception {
        certificateService.deleteCertificate(validCertificate.getId());
        Mockito.verify(certificatesDAO).deleteCertificates(validCertificate.getId());
    }

    @Test
    void deleteCertificatesExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificatesDAO).deleteCertificates(validCertificate.getId());
        assertThrows(ServiceException.class, () -> certificateService.deleteCertificate(validCertificate.getId()), "CreateCertificate fail due to null value of params.");
    }

    @Test
    void deleteCertificatesNotValidParam() {
        assertThrows(ServiceException.class, () -> certificateService.deleteCertificate(notValidCertificate.getId()), "DeleteCertificate fail due to null value of params.");
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
        certificateService.getCertificates();
        Mockito.verify(certificatesDAO).getCertificates();
    }

    @Test
    void getCertificate() throws ServiceException, DAOException {
        certificateService.getCertificate(validCertificate.getId());
        Mockito.verify(certificatesDAO).getCertificate(validCertificate.getId());
    }

    @Test
    void getCertificatesExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificatesDAO).getCertificates();
        assertThrows(ServiceException.class, certificateService::getCertificates, "GetCertificates fail");
    }

    @Test
    void getCertificateExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificatesDAO).getCertificate(validCertificate.getId());
        assertThrows(ServiceException.class, () -> certificateService.getCertificate(validCertificate.getId()), "UpdateCertificate fail");
    }

    @Test
    void isCertificateExist() throws Exception {
        certificateService.isCertificateExist(validCertificate.getId());
        Mockito.verify(certificatesDAO).isCertificateExist(validCertificate.getId());
    }

    @Test
    void isCertificateExistExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificatesDAO).isCertificateExist(validCertificate.getId());
        assertThrows(ServiceException.class, () -> certificateService.isCertificateExist(validCertificate.getId()), "UpdateCertificate fail");
    }
}