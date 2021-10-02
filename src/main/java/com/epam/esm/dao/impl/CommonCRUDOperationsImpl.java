package com.epam.esm.dao.impl;

import com.epam.esm.dao.CommonCRUDOperations;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.connection.ConnectionPool;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommonCRUDOperationsImpl implements CommonCRUDOperations {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_CREATE_DATE = "create_date";
    private static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    private static final Logger LOG = Logger.getLogger(CommonCRUDOperations.class);
    @Override
    public void executeUpdate(String sqlUpdateStatement, List<Object> params) throws DAOException{
        Connection connection = ConnectionPool.connectionPool.retrieve();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlUpdateStatement);
            for (Object param : params) {
                statement.setObject(params.indexOf(param) + 1, param);
            }
            statement.executeUpdate();
            LOG.info("SUCCESS DB: Update executed.");
        } catch (SQLException exc) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to write DB.", exc);
            throw new DAOException(exc);
        } finally {
            ConnectionPool.connectionPool.putBack(connection);
            assert statement != null;
            ConnectionPool.connectionPool.closeConnection(statement);
        }
    }

    public List<Certificate> getCertificates(Object object, String sql) throws DAOException{
        List<Certificate> certificates = new ArrayList<>();
        Connection connection = ConnectionPool.connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (object != null){
                preparedStatement.setObject(1, object);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Certificate certificate = new Certificate();
                certificate.setId(resultSet.getInt(COLUMN_ID));
                certificate.setName(resultSet.getString(COLUMN_NAME));
                certificate.setDescription(resultSet.getString(COLUMN_DESCRIPTION));
                certificate.setPrice(resultSet.getDouble(COLUMN_PRICE));
                certificate.setDuration(resultSet.getInt(COLUMN_DURATION));
                certificate.setCreateDate(resultSet.getString(COLUMN_CREATE_DATE));
                certificate.setLastUpdateDate(resultSet.getString(COLUMN_LAST_UPDATE_DATE));
                certificates.add(certificate);
            }
        } catch (SQLException | RuntimeException exc){
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", exc);
            throw new DAOException(exc);
        }
        return certificates;
    }
}
