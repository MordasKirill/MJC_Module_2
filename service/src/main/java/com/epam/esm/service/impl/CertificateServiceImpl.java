package com.epam.esm.service.impl;

import com.epam.esm.dao.CertificateDAO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.entity.Certificate;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    private CertificateDAO certificatesDAO;

    @Autowired
    public CertificateServiceImpl(CertificateDAO certificatesDAO) {
        this.certificatesDAO = certificatesDAO;
    }

    @Override
    public void createCertificate(@Valid Certificate certificate) throws ServiceException {
        try {
            if (Optional.ofNullable(certificate.getName()).isPresent() &&
                    Optional.ofNullable(certificate.getDescription()).isPresent() &&
                    certificate.getPrice() != 0 &&
                    certificate.getDuration() != 0) {
                certificatesDAO.createCertificate(certificate);
            } else {
                throw new ServiceException("CreateCertificate fail due to null value of params.");
            }
        } catch (DAOException e) {
            throw new ServiceException("CreateCertificate fail", e);
        }
    }

    @Override
    public void deleteCertificate(int id) throws ServiceException {
        try {
            if (id != 0) {
                certificatesDAO.deleteCertificates(id);
            } else {
                throw new ServiceException("DeleteCertificate fail due to null value of params.");
            }
        } catch (DAOException e) {
            throw new ServiceException("DeleteCertificate fail", e);
        }
    }

    @Override
    public void updateCertificate(Certificate certificate) throws ServiceException {
        try {
            certificatesDAO.updateCertificates(certificate);
        } catch (DAOException e) {
            throw new ServiceException("UpdateCertificate fail", e);
        }
    }

    @Override
    public List<Certificate> getCertificates() throws ServiceException {
        try {
            return certificatesDAO.getCertificates();
        } catch (DAOException e) {
            throw new ServiceException("GetCertificates fail", e);
        }
    }

    @Override
    public Certificate getCertificate(int id) throws ServiceException {
        try {
            return certificatesDAO.getCertificate(id);
        } catch (DAOException e) {
            throw new ServiceException("GetCertificate fail", e);
        }
    }

    @Override
    public boolean isCertificateExist(int id) throws ServiceException {
        try {
            return certificatesDAO.isCertificateExist(id);
        } catch (DAOException e) {
            throw new ServiceException("Check certificate fail", e);
        }
    }
}
