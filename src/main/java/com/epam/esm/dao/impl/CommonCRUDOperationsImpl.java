package com.epam.esm.dao.impl;

import com.epam.esm.dao.CommonCRUDOperations;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.connection.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CommonCRUDOperationsImpl implements CommonCRUDOperations {
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
}
