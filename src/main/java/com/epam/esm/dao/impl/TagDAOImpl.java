package com.epam.esm.dao.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.connection.ConnectionPool;
import com.epam.esm.entity.Tag;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TagDAOImpl implements TagDAO {
    private static final String SELECT_ID_NAME_FROM_TAG = "select id, name from tag where id>0";
    private static final String DELETE_FROM_TAG = "delete from tag where id=?";
    private static final String CREATE_TAG = "insert into tag (id, name) values (?,?)";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final Logger LOG = Logger.getLogger(TagDAOImpl.class);
    @Autowired
    private CRUDOperationsDAOImpl commonCRUDOperations;

    @Override
    public List<Tag> getTags() throws DAOException {
        List<Tag> tags = new ArrayList<>();
        Connection connection = ConnectionPool.connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ID_NAME_FROM_TAG);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tag tag = new Tag();
                tag.setId(resultSet.getInt(COLUMN_ID));
                tag.setName(resultSet.getString(COLUMN_NAME));
                tags.add(tag);
            }
        } catch (SQLException | RuntimeException exc) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all tags.", exc);
            throw new DAOException(exc);
        }
        return tags;
    }

    @Override
    public void createTag(Tag tag) throws DAOException {
        List<Object> paramList = new LinkedList<>();
        paramList.add(tag.getId());
        paramList.add(tag.getName());
        commonCRUDOperations.executeUpdate(CREATE_TAG, paramList);
    }

    @Override
    public void deleteTag(Tag tag) throws DAOException {
        List<Object> paramList = new LinkedList<>();
        paramList.add(tag.getId());
        commonCRUDOperations.executeUpdate(DELETE_FROM_TAG, paramList);
    }
}
