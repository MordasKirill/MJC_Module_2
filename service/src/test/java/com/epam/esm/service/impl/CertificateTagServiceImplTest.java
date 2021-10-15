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
    private final CertificateTagServiceImpl certificateTagServiceImplTest = new CertificateTagServiceImpl(certificateTagDAO);

    @Test
    void getCertificatesByTag() throws ServiceException, DAOException {
        certificateTagServiceImplTest.getCertificatesByTag(tag);
        Mockito.verify(certificateTagDAO).getCertificatesByTag(tag);
    }

    @Test
    void getCertificatesByTagExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificateTagDAO).getCertificatesByTag(tag);
        assertThrows(ServiceException.class, () -> certificateTagServiceImplTest.getCertificatesByTag(tag), "Expected ServiceException");
    }

    @Test
    void getCertificatesByNamePart() throws ServiceException, DAOException {
        certificateTagServiceImplTest.getCertificatesByNamePart(tag.getName());
        Mockito.verify(certificateTagDAO).getCertificatesByNamePart(tag.getName());
    }

    @Test
    void getCertificatesByNamePartExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificateTagDAO).getCertificatesByNamePart(tag.getName());
        assertThrows(ServiceException.class, () -> certificateTagServiceImplTest.getCertificatesByNamePart(tag.getName()), "Expected ServiceException");
    }

    @Test
    void getCertificatesSortedByPrice() throws ServiceException, DAOException {
        certificateTagServiceImplTest.getCertificatesSortedByPrice();
        Mockito.verify(certificateTagDAO).getCertificatesSortedByPrice();
    }

    @Test
    void getCertificatesSortedByPriceExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(certificateTagDAO).getCertificatesSortedByPrice();
        assertThrows(ServiceException.class, certificateTagServiceImplTest::getCertificatesSortedByPrice, "Expected ServiceException");
    }
}