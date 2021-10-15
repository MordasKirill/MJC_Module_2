package com.epam.esm.dao;

import java.util.List;

public interface CRUDOperationsDAO {
    /**
     * @param sqlUpdateStatement sql statement
     * @param params             to do operation with DB
     * @throws DAOException exception in DAO layer
     */
    void executeUpdate(String sqlUpdateStatement, List<Object> params) throws DAOException;
}
