package com.epam.esm.dao.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.impl.mappers.TagMapper;
import com.epam.esm.entity.Tag;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TagDAOImpl implements TagDAO {
    private static final String SELECT_ID_NAME_FROM_TAG = "select * from tag";
    private static final String SELECT_ID_NAME_FROM_TAG_WHERE = "select * from tag where id = ?";
    private static final String SELECT_TAG = "select * from tag where name = ?";
    private static final String DELETE_FROM_TAG = "delete from tag where id=?";
    private static final String CREATE_TAG = "insert into tag (name) values (?)";
    private static final Logger LOG = Logger.getLogger(TagDAOImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tag> getTags() throws DAOException {
        return jdbcTemplate.query(SELECT_ID_NAME_FROM_TAG, new TagMapper());
    }

    public Tag getTag(int id) throws DAOException {
        return jdbcTemplate.queryForObject(SELECT_ID_NAME_FROM_TAG_WHERE, new TagMapper(), id);
    }

    @Override
    public void createTag(String name) throws DAOException {
        jdbcTemplate.update(CREATE_TAG, name);
    }

    @Override
    public void deleteTag(int id) throws DAOException {
        jdbcTemplate.update(DELETE_FROM_TAG, id);
    }

    @Override
    public boolean isTagExist(int id) throws DAOException {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_ID_NAME_FROM_TAG_WHERE, new TagMapper(), id)).isPresent();
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean isTagExist(String name) throws DAOException {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_TAG, new TagMapper(), name)).isPresent();
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
