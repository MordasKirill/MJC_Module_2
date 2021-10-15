package com.epam.esm.service;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;

import java.util.List;

public interface CertificateTagService {
    /**
     * @param tag to get certificates by tag
     * @return List with Certificate entity's
     * @throws ServiceException exception in Service layer
     */
    List<Certificate> getCertificatesByTag(Tag tag) throws ServiceException;

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
    List<Certificate> getCertificatesSortedByPrice() throws ServiceException;
}
