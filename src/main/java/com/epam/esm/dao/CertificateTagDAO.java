package com.epam.esm.dao;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;

import java.util.List;

public interface CertificateTagDAO {
    List<Certificate> getCertificatesByTag(Tag tag) throws DAOException;

    List<Certificate> getCertificatesByNamePart(String name) throws DAOException;

    List<Certificate> getCertificatesSortedByPrice() throws DAOException;
}
