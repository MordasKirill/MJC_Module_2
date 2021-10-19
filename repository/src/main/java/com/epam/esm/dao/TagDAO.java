package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagDAO {
    /**
     * @return List of tag entity's
     * @throws DAOException exception in DAO layer
     */
    List<Tag> getTags() throws DAOException;

    /**
     * @param tag to create new tag
     * @throws DAOException exception in DAO layer
     */
    void createTag(Tag tag) throws DAOException;

    /**
     * @param tag to delete chosen tag
     * @throws DAOException exception in DAO layer
     */
    void deleteTag(Tag tag) throws DAOException;

    /**
     * @param id id to be checked
     * @return true/false is exists
     * @throws DAOException exception in DAO layer
     */
    boolean isTagExist(int id) throws DAOException;
}
