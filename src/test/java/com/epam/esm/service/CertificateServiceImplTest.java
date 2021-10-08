package com.epam.esm.service;

import com.epam.esm.dao.impl.CertificatesDAOImpl;
import com.epam.esm.entity.Certificate;
import com.epam.esm.service.impl.CertificateServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CertificateServiceImplTest {
    private static final String patternHhMm = "yyyy-MM-dd'T'HH:mm'Z'";
    private static final Certificate certificate = new Certificate("test", 0.0, 0, "0.0", "0", "stuff");

    @InjectMocks
    CertificateServiceImpl service = new CertificateServiceImpl();

    @BeforeAll
    static void setUp() {
    }

    @Test
    void createCertificates() throws ServiceException {
        CertificateServiceImpl certificateService = Mockito.mock(CertificateServiceImpl.class);
        certificateService.createCertificates(certificate);
        Mockito.verify(certificateService).createCertificates(certificate);
    }

    @Test
    void createCertificatesExc() throws ServiceException {
        CertificateServiceImpl certificateService = Mockito.mock(CertificateServiceImpl.class);
        Mockito.doThrow(new ServiceException()).when(certificateService).createCertificates(certificate);
        assertThrows(ServiceException.class, () -> certificateService.createCertificates(certificate), "Expected ServiceException");
    }

    @Test
    void deleteCertificates() throws Exception {
        CertificateServiceImpl certificateService = Mockito.mock(CertificateServiceImpl.class);
        certificateService.deleteCertificates(certificate);
        Mockito.verify(certificateService).deleteCertificates(certificate);
    }

    @Test
    void deleteCertificatesExc() throws ServiceException {
        CertificateServiceImpl certificateService = Mockito.mock(CertificateServiceImpl.class);
        Mockito.doThrow(new ServiceException()).when(certificateService).deleteCertificates(certificate);
        assertThrows(ServiceException.class, () -> certificateService.deleteCertificates(certificate), "Expected ServiceException");
    }

    @Test
    void updateCertificates() throws Exception {
        CertificateServiceImpl certificateService = Mockito.mock(CertificateServiceImpl.class);
        certificateService.updateCertificates(certificate);
        Mockito.verify(certificateService).updateCertificates(certificate);
    }

    @Test
    void updateCertificatesExc() throws ServiceException {
        CertificateServiceImpl certificateService = Mockito.mock(CertificateServiceImpl.class);
        Mockito.doThrow(new ServiceException()).when(certificateService).updateCertificates(certificate);
        assertThrows(ServiceException.class, () -> certificateService.updateCertificates(certificate), "Expected ServiceException");
    }

    @Test
    void getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTF+3"));
        boolean actual = dateFormat.format(new Date()).equals(service.getCurrentDate(patternHhMm));
        assertTrue(actual);
    }

    @Test
    void getCertificates() throws ServiceException {
        CertificateServiceImpl certificateService = Mockito.mock(CertificateServiceImpl.class);
        certificateService.getCertificates();
        Mockito.verify(certificateService).getCertificates();
    }

    @Test
    void getCertificatesExc() throws ServiceException {
        CertificateServiceImpl certificateService = Mockito.mock(CertificateServiceImpl.class);
        Mockito.doThrow(new ServiceException()).when(certificateService).getCertificates();
        assertThrows(ServiceException.class, certificateService::getCertificates, "Expected ServiceException");
    }
}