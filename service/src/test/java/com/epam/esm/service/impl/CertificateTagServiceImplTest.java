package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.CertificateTagDAOImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.ServiceException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CertificateTagServiceImplTest {
    private static final Tag tag = new Tag("tagName");
    private final CertificateTagDAOImpl certificateTagDAO = Mockito.mock(CertificateTagDAOImpl.class);
    private final CertificateTagServiceImpl certificateTagService = new CertificateTagServiceImpl(certificateTagDAO);

    @Test
    void getCertificatesByTag() throws DAOException, ServiceException {
        certificateTagService.getCertificatesByTag(tag.getId());
        Mockito.verify(certificateTagDAO).getCertificatesByTag(tag.getId());
    }

    @Test
    void getCertificatesByTagExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificateTagDAO).getCertificatesByTag(tag.getId());
        assertThrows(ServiceException.class, () -> certificateTagService.getCertificatesByTag(tag.getId()), "Expected ServiceException");
    }

    @Test
    void getCertificatesByNamePart() throws DAOException, ServiceException {
        certificateTagService.getCertificatesByNamePart(tag.getName());
        Mockito.verify(certificateTagDAO).getCertificatesByNamePart(tag.getName());
    }

    @Test
    void getCertificatesByNamePartExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificateTagDAO).getCertificatesByNamePart(tag.getName());
        assertThrows(ServiceException.class, () -> certificateTagService.getCertificatesByNamePart(tag.getName()), "Expected ServiceException");
    }

    @Test
    void getCertificatesSortedByPrice() throws DAOException, ServiceException {
        certificateTagService.getCertificatesSortedByPrice();
        Mockito.verify(certificateTagDAO).getCertificatesSortedByPrice();
    }

    @Test
    void getCertificatesSortedByPriceExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificateTagDAO).getCertificatesSortedByPrice();
        assertThrows(ServiceException.class, certificateTagService::getCertificatesSortedByPrice, "Expected ServiceException");
    }
}