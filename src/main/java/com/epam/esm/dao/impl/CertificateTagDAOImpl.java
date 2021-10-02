package com.epam.esm.dao.impl;

import com.epam.esm.dao.CertificateTagDAO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;

import java.util.List;

public class CertificateTagDAOImpl implements CertificateTagDAO {
    private static final String SELECT_FROM_CERTIFICATE_TAG = "select * from certificate_has_tag left join certificate on (certificate.id = cerf_id) where certificate_has_tag.tag_id = ?";
    private static final String SELECT_FROM_CERTIFICATE_LIKE = "select * from certificate where name like ?";
    private static final String SELECT_FROM_CERTIFICATE_WHERE_ASC = "select * from certificate order by price asc, name desc";
    private CommonCRUDOperationsImpl commonCRUDOperations;
    public CertificateTagDAOImpl (CommonCRUDOperationsImpl commonCRUDOperations){
        this.commonCRUDOperations = commonCRUDOperations;
    }
    @Override
    public List<Certificate> getCertificatesByTag(Tag tag) throws DAOException{
        try {
            return commonCRUDOperations.getCertificates(tag.getId(), SELECT_FROM_CERTIFICATE_TAG);
        } catch (DAOException e) {
            throw new DAOException("Fail to get certificates by tag.");
        }
    }

    public List<Certificate> getCertificatesByNamePart(String name) throws DAOException{
        try {
            return commonCRUDOperations.getCertificates(name, SELECT_FROM_CERTIFICATE_LIKE);
        } catch (DAOException e) {
            throw new DAOException("Fail to get certificates by namePart.");
        }
    }

    public List<Certificate> getCertificatesByName() throws DAOException{
        try {
            return commonCRUDOperations.getCertificates(null, SELECT_FROM_CERTIFICATE_WHERE_ASC);
        } catch (DAOException e) {
            throw new DAOException("Fail to get certificates by name.");
        }
    }
}
