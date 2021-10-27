package com.epam.esm.service.impl;

import com.epam.esm.dao.CertificateDAO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.entity.Certificate;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateDAO certificatesDAO;

    @Autowired
    public CertificateServiceImpl(CertificateDAO certificatesDAO) {
        this.certificatesDAO = certificatesDAO;
    }

    @Override
    public void createCertificate(Certificate certificate) throws ServiceException {
        try {
            if (Optional.ofNullable(certificate.getName()).isPresent() &&
                    Optional.ofNullable(certificate.getDescription()).isPresent() &&
                    Optional.ofNullable(certificate.getPrice()).isPresent() &&
                    Optional.ofNullable(certificate.getDuration()).isPresent()) {
                certificatesDAO.createCertificate(certificate);
            } else {
                throw new ServiceException("CreateCertificate fail due to null value of params.");
            }
        } catch (DAOException e) {
            throw new ServiceException("CreateCertificate fail", e);
        }
    }

    @Override
    public void deleteCertificate(Integer id) throws ServiceException {
        try {
            if (!Optional.ofNullable(id).isPresent() || !isCertificateExist(id)) {
                throw new ServiceException("Cant find certificate with id:" + id);
            } else {
                certificatesDAO.deleteCertificates(id);
            }
        } catch (DAOException e) {
            throw new ServiceException("DeleteCertificate fail", e);
        }
    }

    @Override
    public void updateCertificate(Integer id, Certificate certificate) throws ServiceException {
        try {
            if (!Optional.ofNullable(id).isPresent() || !isCertificateExist(id)) {
                throw new ServiceException("Cant find certificate with id:" + id);
            } else {
                certificatesDAO.updateCertificates(certificate);
            }
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
    public Certificate getCertificate(Integer id) throws ServiceException {
        try {
            if (!Optional.ofNullable(id).isPresent() || !isCertificateExist(id)) {
                throw new ServiceException("Cant find certificate with id:" + id);
            }
            return certificatesDAO.getCertificate(id);
        } catch (DAOException e) {
            throw new ServiceException("GetCertificate fail", e);
        }
    }

    @Override
    public boolean isCertificateExist(Integer id) throws ServiceException {
        try {
            return certificatesDAO.isCertificateExist(id);
        } catch (DAOException e) {
            throw new ServiceException("Check certificate fail", e);
        }
    }
}
