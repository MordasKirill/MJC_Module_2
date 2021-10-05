package com.epam.esm.dao;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;

import java.util.List;

public interface CertificateTagDAO {
    /**
     * @param tag to get certificates by tag
     * @return List with Certificate entity's
     * @throws DAOException exception in DAO layer
     */
    List<Certificate> getCertificatesByTag(Tag tag) throws DAOException;

    /**
     * @param name to get certificates by name
     * @return List with Certificate entity's
     * @throws DAOException exception in DAO layer
     */
    List<Certificate> getCertificatesByNamePart(String name) throws DAOException;

    /**
     * @return List with Certificate entity's
     * @throws DAOException exception in DAO layer
     */
    List<Certificate> getCertificatesSortedByPrice() throws DAOException;
}
