package com.epam.esm.dao;

import com.epam.esm.entity.Certificate;

import java.util.List;

public interface CertificateTagDAO {
    /**
     * @param id to get certificates by tag id
     * @return List with Certificate entity's
     * @throws DAOException exception in DAO layer
     */
    List<Certificate> getCertificatesByTag(int id) throws DAOException;

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
    List<Certificate> getCertificatesSorted(String sortParam, String direction) throws DAOException;
}
