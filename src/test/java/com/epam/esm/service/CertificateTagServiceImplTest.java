package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.impl.CertificateTagServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CertificateTagServiceImplTest {
    private static final Tag tag = new Tag("tagName");

    @Test
    void getCertificatesByTag() throws ServiceException {
        CertificateTagServiceImpl certificateTagServiceImplTest = Mockito.mock(CertificateTagServiceImpl.class);
        certificateTagServiceImplTest.getCertificatesByTag(tag);
        Mockito.verify(certificateTagServiceImplTest).getCertificatesByTag(tag);
    }

    @Test
    void getCertificatesByTagExc() throws ServiceException {
        CertificateTagServiceImpl certificateTagServiceImplTest = Mockito.mock(CertificateTagServiceImpl.class);
        Mockito.doThrow(new ServiceException()).when(certificateTagServiceImplTest).getCertificatesByTag(tag);
        assertThrows(ServiceException.class, () -> certificateTagServiceImplTest.getCertificatesByTag(tag), "Expected ServiceException");
    }

    @Test
    void getCertificatesByNamePart() throws ServiceException {
        CertificateTagServiceImpl certificateTagServiceImplTest = Mockito.mock(CertificateTagServiceImpl.class);
        certificateTagServiceImplTest.getCertificatesByNamePart("name");
        Mockito.verify(certificateTagServiceImplTest).getCertificatesByNamePart("name");
    }

    @Test
    void getCertificatesByNamePartExc() throws ServiceException {
        CertificateTagServiceImpl certificateTagServiceImplTest = Mockito.mock(CertificateTagServiceImpl.class);
        Mockito.doThrow(new ServiceException()).when(certificateTagServiceImplTest).getCertificatesByNamePart("name");
        assertThrows(ServiceException.class, () -> certificateTagServiceImplTest.getCertificatesByNamePart("name"), "Expected ServiceException");
    }

    @Test
    void getCertificatesSortedByPrice() throws ServiceException {
        CertificateTagServiceImpl certificateTagServiceImplTest = Mockito.mock(CertificateTagServiceImpl.class);
        certificateTagServiceImplTest.getCertificatesSortedByPrice();
        Mockito.verify(certificateTagServiceImplTest).getCertificatesSortedByPrice();
    }

    @Test
    void getCertificatesSortedByPriceExc() throws ServiceException {
        CertificateTagServiceImpl certificateTagServiceImplTest = Mockito.mock(CertificateTagServiceImpl.class);
        Mockito.doThrow(new ServiceException()).when(certificateTagServiceImplTest).getCertificatesSortedByPrice();
        assertThrows(ServiceException.class, certificateTagServiceImplTest::getCertificatesSortedByPrice, "Expected ServiceException");
    }
}