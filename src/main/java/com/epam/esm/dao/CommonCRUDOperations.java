package com.epam.esm.dao;

import java.util.List;

public interface CommonCRUDOperations {
    void executeUpdate(String sqlUpdateStatement, List<Object> params) throws DAOException;
}
