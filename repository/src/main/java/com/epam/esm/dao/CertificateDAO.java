package com.epam.esm.dao;

import com.epam.esm.entity.Certificate;

import java.util.List;

public interface CertificateDAO {
    /**
     * @return List with Certificate entity's
     * @throws DAOException exception in DAO layer
     */
    List<Certificate> getCertificates() throws DAOException;

    /**
     * @param certificate entity used to create Certificate
     * @throws DAOException exception in DAO layer
     */
    void createCertificates(Certificate certificate) throws DAOException;

    /**
     * * @param certificate entity used to delete Certificate
     * delete chosen certificate
     *
     * @throws DAOException exception in DAO layer
     */
    void deleteCertificates(Certificate certificate) throws DAOException;

    /**
     * @param certificate entity used to update Certificate
     *                    update info of chosen certificate
     * @throws DAOException exception in DAO layer
     */
    void updateCertificates(Certificate certificate) throws DAOException;

    /**
     * @param id id to be checked
     * @return true/false is exists
     * @throws DAOException exception in DAO layer
     */
    boolean isCertificateExist(int id) throws DAOException;
}
