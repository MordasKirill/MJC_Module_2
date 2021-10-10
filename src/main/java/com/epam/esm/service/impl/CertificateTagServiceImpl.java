package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.CertificateTagDAOImpl;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.CertificateTagService;
import com.epam.esm.service.ServiceException;

import java.util.List;

public class CertificateTagServiceImpl implements CertificateTagService {
    private CertificateTagDAOImpl certificateTagDAO;

    public CertificateTagServiceImpl(CertificateTagDAOImpl certificateTagDAO) {
        this.certificateTagDAO = certificateTagDAO;
    }

    @Override
    public List<Certificate> getCertificatesByTag(Tag tag) throws ServiceException {
        try {
            return certificateTagDAO.getCertificatesByTag(tag);
        } catch (DAOException e) {
            throw new ServiceException("Fail to get certificates  by tag.", e);
        }
    }

    @Override
    public List<Certificate> getCertificatesByNamePart(String name) throws ServiceException {
        try {
            return certificateTagDAO.getCertificatesByNamePart(name);
        } catch (DAOException e) {
            throw new ServiceException("Fail to get certificates  by name.", e);
        }
    }

    @Override
    public List<Certificate> getCertificatesSortedByPrice() throws ServiceException {
        try {
            return certificateTagDAO.getCertificatesSortedByPrice();
        } catch (DAOException e) {
            throw new ServiceException("Fail to get certificates  by name.", e);
        }
    }
}
