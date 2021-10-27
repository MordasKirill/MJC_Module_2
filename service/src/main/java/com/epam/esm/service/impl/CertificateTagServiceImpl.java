package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.CertificateTagDAOImpl;
import com.epam.esm.entity.Certificate;
import com.epam.esm.service.CertificateTagService;
import com.epam.esm.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateTagServiceImpl implements CertificateTagService {

    private CertificateTagDAOImpl certificateTagDAO;
    private TagServiceImpl tagService;

    @Autowired
    public CertificateTagServiceImpl(CertificateTagDAOImpl certificateTagDAO, TagServiceImpl tagService) {
        this.certificateTagDAO = certificateTagDAO;
        this.tagService = tagService;
    }

    @Override
    public List<Certificate> getCertificatesByTag(Integer id) throws ServiceException {
        try {
            if (!Optional.ofNullable(id).isPresent() || !tagService.isTagExist(id)) {
                throw new ServiceException("Cant find tag with id:" + id);
            }
            return certificateTagDAO.getCertificatesByTag(id);
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
    public List<Certificate> getCertificatesSorted(String sortParam, String direction) throws ServiceException {
        try {
            return certificateTagDAO.getCertificatesSorted(sortParam, direction);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
