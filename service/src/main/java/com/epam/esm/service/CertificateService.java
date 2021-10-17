package com.epam.esm.service;

import com.epam.esm.entity.Certificate;

import java.util.List;

public interface CertificateService {
    /**
     * @param certificate entity used to create Certificate
     * @throws ServiceException exception in Service layer
     */
    void createCertificate(Certificate certificate) throws ServiceException;

    /**
     * * @param certificate entity used to delete Certificate
     * delete chosen certificate
     *
     * @throws ServiceException exception in Service layer
     */
    void deleteCertificate(Certificate certificate) throws ServiceException;

    /**
     * @param certificate entity used to update Certificate
     *                    update info of chosen certificate
     * @throws ServiceException exception in Service layer
     */
    void updateCertificate(Certificate certificate) throws ServiceException;


    /**
     * @return List with Certificate entity's
     * @throws ServiceException exception in Service layer
     */
    List<Certificate> getCertificates() throws ServiceException;
}
