package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private TagDAO tagDAO;

    @Autowired
    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    public void createTag(String name) throws ServiceException {
        try {
            tagDAO.createTag(name);
        } catch (DAOException e) {
            throw new ServiceException("CreateTag fail.", e);
        }
    }

    @Override
    public void deleteTag(int id) throws ServiceException {
        try {
            tagDAO.deleteTag(id);
        } catch (DAOException e) {
            throw new ServiceException("DeleteTag fail.", e);
        }
    }

    @Override
    public List<Tag> getTags() throws ServiceException {
        try {
            return tagDAO.getTags();
        } catch (DAOException e) {
            throw new ServiceException("GetTags fail.", e);
        }
    }

    @Override
    public Tag getTag(int id) throws ServiceException {
        try {
            return tagDAO.getTag(id);
        } catch (DAOException e) {
            throw new ServiceException("GetTags fail.", e);
        }
    }

    @Override
    public boolean isTagExist(int id) throws ServiceException {
        try {
            return tagDAO.isTagExist(id);
        } catch (DAOException e) {
            throw new ServiceException("Check certificate fail", e);
        }
    }
}
