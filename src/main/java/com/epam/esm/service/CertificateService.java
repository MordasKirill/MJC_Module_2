package com.epam.esm.service;

import com.epam.esm.entity.Certificate;

import java.util.List;
import java.util.regex.Pattern;

public interface CertificateService {
    void createCertificates(Certificate certificate) throws ServiceException;
    void deleteCertificates(Certificate certificate) throws ServiceException;
    void updateCertificates(Certificate certificate) throws ServiceException;
    String getCurrentDate(String pattern);
    List<Certificate> getCertificates() throws ServiceException;
}
