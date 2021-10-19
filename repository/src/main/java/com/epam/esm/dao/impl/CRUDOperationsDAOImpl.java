package com.epam.esm.dao.impl;

import com.epam.esm.dao.CRUDOperationsDAO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.connection.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Objects;

@Repository
public class CRUDOperationsDAOImpl implements CRUDOperationsDAO {
    private static final Logger LOG = Logger.getLogger(CRUDOperationsDAO.class);

    @Override
    public void executeUpdate(String sqlUpdateStatement, List<Object> params) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.pooledDataSource.getConnection();
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
            try {
                Objects.requireNonNull(connection).close();
            } catch (SQLException e) {
                LOG.log(Level.ERROR, "FAIL DB: Fail to close connection.", e);
            }
        }
    }

    @Override
    public void executeCallable(String sql, List<Object> params) throws DAOException {
        Connection connection = null;
        try {
            connection = ConnectionPool.pooledDataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(sql);
            for (Object param : params) {
                callableStatement.setObject(params.indexOf(param) + 1, param);
            }
            callableStatement.executeUpdate();
            LOG.info("SUCCESS DB: Update executed.");
        } catch (SQLException exc) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to write DB.", exc);
            throw new DAOException(exc);
        } finally {
            try {
                Objects.requireNonNull(connection).close();
            } catch (SQLException e) {
                LOG.log(Level.ERROR, "FAIL DB: Fail to close connection.", e);
            }
        }
    }

    public boolean isExists(int id, String sqlUpdateStatement) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.pooledDataSource.getConnection();
            statement = connection.prepareStatement(sqlUpdateStatement);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            LOG.info("SUCCESS DB: Query executed.");
            return resultSet.next();
        } catch (SQLException exc) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to read DB.", exc);
            throw new DAOException(exc);
        } finally {
            try {
                Objects.requireNonNull(connection).close();
            } catch (SQLException e) {
                LOG.log(Level.ERROR, "FAIL DB: Fail to close connection.", e);
            }
        }
    }
}
