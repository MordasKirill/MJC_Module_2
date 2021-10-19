package com.epam.esm.dao;

import java.util.List;

public interface CRUDOperationsDAO {
    /**
     * Method for executing sql statement
     *
     * @param sqlUpdateStatement sql statement
     * @param params             to do operation with DB
     * @throws DAOException exception in DAO layer
     */
    void executeUpdate(String sqlUpdateStatement, List<Object> params) throws DAOException;

    /**
     * Method for executing stored procedure
     *
     * @param sqlUpdateStatement sql statement
     * @param params             to do operation with DB
     * @throws DAOException exception in DAO layer
     */
    void executeCallable(String sqlUpdateStatement, List<Object> params) throws DAOException;

    /**
     * @param id                 id to be checked
     * @param sqlUpdateStatement sql statement
     * @return true/false is exists
     * @throws DAOException exception in DAO layer
     */
    boolean isExists(int id, String sqlUpdateStatement) throws DAOException;
}
