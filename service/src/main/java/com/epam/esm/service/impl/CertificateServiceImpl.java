package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.CertificatesDAOImpl;
import com.epam.esm.entity.Certificate;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificatesDAOImpl certificatesDAO;

    public CertificateServiceImpl(CertificatesDAOImpl certificatesDAO) {
        this.certificatesDAO = certificatesDAO;
    }

    @Override
    public void createCertificates(Certificate certificate) throws ServiceException {
        try {
            if (certificate.getName() != null &&
                    certificate.getDescription() != null &&
                    certificate.getPrice() != 0 &&
                    certificate.getDuration() != 0) {
                certificatesDAO.createCertificates(certificate);
            } else {
                throw new ServiceException("CreateCertificate fail due to null value of params.");
            }
        } catch (DAOException e) {
            throw new ServiceException("CreateCertificate fail", e);
        }
    }

    @Override
    public void deleteCertificates(Certificate certificate) throws ServiceException {
        try {
            if (certificate.getId() != 0) {
                certificatesDAO.deleteCertificates(certificate);
            } else {
                throw new ServiceException("DeleteCertificate fail due to null value of params.");
            }
        } catch (DAOException e) {
            throw new ServiceException("DeleteCertificate fail", e);
        }
    }

    @Override
    public void updateCertificates(Certificate certificate) throws ServiceException {
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
}
