package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagService {
    /**
     * @param tag to create new tag
     * @throws ServiceException exception in Service layer
     */
    void createTag(Tag tag) throws ServiceException;

    /**
     * @param tag to delete chosen tag
     * @throws ServiceException exception in Service layer
     */
    void deleteTag(Tag tag) throws ServiceException;

    /**
     * @return List of tag entity's
     * @throws ServiceException exception in Service layer
     */
    List<Tag> getTags() throws ServiceException;

    /**
     * @param id id to be checked
     * @return true/false is exists
     * @throws ServiceException exception in Service layer
     */
    boolean isTagExist(int id) throws ServiceException;
}
