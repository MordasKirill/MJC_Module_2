package com.epam.esm.dao.impl;

import com.epam.esm.dao.CertificateTagDAO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.connection.ConnectionPool;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CertificateTagDAOImpl implements CertificateTagDAO {
    private static final String SELECT_FROM_CERTIFICATE_TAG = "select * from certificate_has_tag left join certificate on (certificate.id = cerf_id) where certificate_has_tag.tag_id = ?";
    private static final String SELECT_FROM_CERTIFICATE_LIKE = "select certificate.*, group_concat(tag.name) from certificate join certificate_has_tag on certificate_has_tag.cerf_id = certificate.id join tag on tag.id = certificate_has_tag.tag_id where certificate.name like ?";
    private static final String SELECT_FROM_CERTIFICATE_WHERE_ASC = "select certificate.*, group_concat(tag.name) from certificate join certificate_has_tag on certificate_has_tag.cerf_id = certificate.id join tag on tag.id = certificate_has_tag.tag_id group by certificate.id order by certificate.price";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_CREATE_DATE = "create_date";
    private static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    private static final Logger LOG = Logger.getLogger(CertificateTagDAOImpl.class);

    @Override
    public List<Certificate> getCertificatesByTag(Tag tag) throws DAOException {
        try {
            return getCertificates(tag.getId(), SELECT_FROM_CERTIFICATE_TAG);
        } catch (DAOException e) {
            throw new DAOException("Fail to get certificates by tag.");
        }
    }

    public List<Certificate> getCertificatesByNamePart(String name) throws DAOException {
        try {
            return getCertificates(name, SELECT_FROM_CERTIFICATE_LIKE);
        } catch (DAOException e) {
            throw new DAOException("Fail to get certificates by namePart.");
        }
    }

    public List<Certificate> getCertificatesSortedByPrice() throws DAOException {
        try {
            return getCertificates(null, SELECT_FROM_CERTIFICATE_WHERE_ASC);
        } catch (DAOException e) {
            throw new DAOException("Fail to get certificates by name.");
        }
    }

    public List<Certificate> getCertificates(Object object, String sql) throws DAOException {
        List<Certificate> certificates = new ArrayList<>();
        Connection connection = ConnectionPool.connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (object != null) {
                preparedStatement.setObject(1, object);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Certificate certificate = new Certificate();
                certificate.setId(resultSet.getInt(COLUMN_ID));
                certificate.setName(resultSet.getString(COLUMN_NAME));
                certificate.setDescription(resultSet.getString(COLUMN_DESCRIPTION));
                certificate.setPrice(resultSet.getDouble(COLUMN_PRICE));
                certificate.setDuration(resultSet.getInt(COLUMN_DURATION));
                certificate.setCreateDate(resultSet.getString(COLUMN_CREATE_DATE));
                certificate.setLastUpdateDate(resultSet.getString(COLUMN_LAST_UPDATE_DATE));
                if (resultSet.getString(8) != null) {
                    certificate.setTagName(resultSet.getString(8));
                }
                certificates.add(certificate);
            }
        } catch (SQLException | RuntimeException exc) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", exc);
            throw new DAOException(exc);
        }
        return certificates;
    }
}
