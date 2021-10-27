package com.epam.esm.service;

import com.epam.esm.entity.Certificate;

import java.util.List;

public interface CertificateTagService {
    /**
     * @param id to get certificates by tag id
     * @return List with Certificate entity's
     * @throws ServiceException exception in Service layer
     */
    List<Certificate> getCertificatesByTag(Integer id) throws ServiceException;

    /**
     * @param name to get certificates by name
     * @return List with Certificate entity's
     * @throws ServiceException exception in Service layer
     */
    List<Certificate> getCertificatesByNamePart(String name) throws ServiceException;

    /**
     * @return List with Certificate entity's
     * @throws ServiceException exception in Service layer
     */
    List<Certificate> getCertificatesSorted(String sortParam, String direction) throws ServiceException;
}
