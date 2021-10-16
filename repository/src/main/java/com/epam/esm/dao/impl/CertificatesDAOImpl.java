package com.epam.esm.dao.impl;

import com.epam.esm.dao.CertificateDAO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.connection.ConnectionPool;
import com.epam.esm.entity.Certificate;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Repository
public class CertificatesDAOImpl implements CertificateDAO {
    private static final String DELETE_FROM_CERTIFICATE = "delete from certificate where id=?";
    private static final String CALLABLE_STATEMENT = "{call createCertificate(?,?,?,?,?)}";
    private static final String UPDATE_CERTIFICATE = "update certificate set name = ?, price = ?, description = ? where id = ?";
    private static final String SELECT = "select certificate.*, group_concat(tag.name) from certificate join certificate_has_tag on certificate_has_tag.cerf_id = certificate.id join tag on tag.id = certificate_has_tag.tag_id group by certificate.id";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_CREATE_DATE = "create_date";
    private static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    private static final Logger LOG = Logger.getLogger(CertificatesDAOImpl.class);

    private final CRUDOperationsDAOImpl crudOperationsDAO;

    public CertificatesDAOImpl(CRUDOperationsDAOImpl commonCRUDOperations) {
        this.crudOperationsDAO = commonCRUDOperations;
    }

    @Override
    public List<Certificate> getCertificates() throws DAOException {
        List<Certificate> certificates = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.pooledDataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT);
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
                certificate.setTagName(resultSet.getString(8));
                certificates.add(certificate);
            }
        } catch (SQLException | RuntimeException exc) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", exc);
            throw new DAOException(exc);
        } finally {
            try {
                Objects.requireNonNull(connection).close();
            } catch (SQLException e) {
                LOG.log(Level.ERROR, "FAIL DB: Fail to close connection.", e);
            }
        }
        return certificates;
    }

    @Override
    public void createCertificates(Certificate certificate) throws DAOException {
        List<Object> paramList = new LinkedList<>();
        paramList.add(certificate.getName());
        paramList.add(certificate.getPrice());
        paramList.add(certificate.getDuration());
        paramList.add(certificate.getDescription());
        paramList.add(certificate.getTagName());
        crudOperationsDAO.executeCallable(CALLABLE_STATEMENT, paramList);
    }

    @Override
    public void deleteCertificates(Certificate certificate) throws DAOException {
        List<Object> paramList = new LinkedList<>();
        paramList.add(certificate.getId());
        crudOperationsDAO.executeUpdate(DELETE_FROM_CERTIFICATE, paramList);
    }

    @Override
    public void updateCertificates(Certificate certificate) throws DAOException {
        List<Object> paramList = new LinkedList<>();
        paramList.add(certificate.getName());
        paramList.add(certificate.getPrice());
        paramList.add(certificate.getDescription());
        paramList.add(certificate.getId());
        crudOperationsDAO.executeUpdate(UPDATE_CERTIFICATE, paramList);
    }
}
