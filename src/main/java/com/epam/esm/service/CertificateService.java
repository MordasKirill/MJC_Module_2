package com.epam.esm.service;

import com.epam.esm.entity.Certificate;

import java.util.List;

public interface CertificateService {
    void createCertificates(Certificate certificate) throws ServiceException;
    void deleteCertificates(Certificate certificate) throws ServiceException;
    void updateCertificates(Certificate certificate) throws ServiceException;
    String getCurrentDate();
    List<Certificate> getCertificates() throws ServiceException;
}
