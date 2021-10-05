package com.epam.esm.dao;

import com.epam.esm.entity.Certificate;

import java.util.List;

public interface CertificateDAO {
    List<Certificate> getCertificates() throws DAOException;

    void createCertificates(Certificate certificate) throws DAOException;

    void deleteCertificates(Certificate certificate) throws DAOException;

    void updateCertificates(Certificate certificate) throws DAOException;
}
