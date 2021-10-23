package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagService {
    /**
     * @param name to create new tag
     * @throws ServiceException exception in Service layer
     */
    void createTag(String name) throws ServiceException;

    /**
     * @param id to delete chosen tag
     * @throws ServiceException exception in Service layer
     */
    void deleteTag(int id) throws ServiceException;

    /**
     * @return List of tag entity's
     * @throws ServiceException exception in Service layer
     */
    List<Tag> getTags() throws ServiceException;

    /**
     * @return List of tag entity's
     * @throws ServiceException exception in Service layer
     */
    Tag getTag(int id) throws ServiceException;

    /**
     * @param id id to be checked
     * @return true/false is exists
     * @throws ServiceException exception in Service layer
     */
    boolean isTagExist(int id) throws ServiceException;
}
