package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void deleteTag(Integer id) throws ServiceException {
        try {
            if (!Optional.ofNullable(id).isPresent() || !isTagExist(id)) {
                throw new ServiceException("Cant find tag with id:" + id);
            }
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
    public Tag getTag(Integer id) throws ServiceException {
        try {
            if (!Optional.ofNullable(id).isPresent() || !isTagExist(id)) {
                throw new ServiceException("Cant find tag with id:" + id);
            }
            return tagDAO.getTag(id);
        } catch (DAOException e) {
            throw new ServiceException("GetTags fail.", e);
        }
    }

    @Override
    public boolean isTagExist(Integer id) throws ServiceException {
        try {
            return tagDAO.isTagExist(id);
        } catch (DAOException e) {
            throw new ServiceException("Check certificate fail", e);
        }
    }
}
