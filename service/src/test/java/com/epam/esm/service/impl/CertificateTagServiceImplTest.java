package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.CertificateTagDAOImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CertificateTagServiceImplTest {
    private static final Tag tag = new Tag("tagName");
    @Mock
    private CertificateTagDAOImpl certificateTagDAO;
    @InjectMocks
    private CertificateTagServiceImpl certificateTagService;

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
        String param = "name";
        String direction = "asc";
        certificateTagService.getCertificatesSorted(param, direction);
        Mockito.verify(certificateTagDAO).getCertificatesSorted(param, direction);
    }

    @Test
    void getCertificatesSortedByPriceExc() throws DAOException {
        String param = "name";
        String direction = "asc";
        Mockito.doThrow(new DAOException()).when(certificateTagDAO).getCertificatesSorted(param, direction);
        assertThrows(ServiceException.class, () -> certificateTagService.getCertificatesSorted(param, direction), "Expected ServiceException");
    }
}