package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.CertificatesDAOImpl;
import com.epam.esm.entity.Certificate;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificatesDAOImpl certificatesDAO;

    @Override
    public void createCertificates(Certificate certificate) throws ServiceException {
        try {
            if (certificate.getName() != null ||
                    certificate.getDescription() != null ||
                    certificate.getPrice() != 0 ||
                    certificate.getDuration() != 0 ||
                    certificate.getCreateDate() != null ||
                    certificate.getLastUpdateDate() != null) {

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
                throw new ServiceException("CreateCertificate fail due to null value of params.");
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
    public String getCurrentDate(String pattern) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat(pattern);
        df.setTimeZone(tz);
        return df.format(new Date());
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
