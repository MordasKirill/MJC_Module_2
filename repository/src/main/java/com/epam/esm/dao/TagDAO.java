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
     * @return Single tag of tag entity's
     * @throws DAOException exception in DAO layer
     */
    Tag getTag(int id) throws DAOException;

    /**
     * @param name to create new tag
     * @throws DAOException exception in DAO layer
     */
    void createTag(String name) throws DAOException;

    /**
     * @param id to delete chosen tag
     * @throws DAOException exception in DAO layer
     */
    void deleteTag(int id) throws DAOException;

    /**
     * @param id id to be checked
     * @return true/false is exists
     * @throws DAOException exception in DAO layer
     */
    boolean isTagExist(int id) throws DAOException;

    /**
     * @param name id to be checked
     * @return true/false is exists
     * @throws DAOException exception in DAO layer
     */
    boolean isTagExist(String name) throws DAOException;
}
