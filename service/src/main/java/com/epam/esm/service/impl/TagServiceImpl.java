package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final TagDAOImpl tagDAO;

    public TagServiceImpl(TagDAOImpl tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    public void createTag(Tag tag) throws ServiceException {
        try {
            tagDAO.createTag(tag);
        } catch (DAOException e) {
            throw new ServiceException("CreateTag fail.", e);
        }
    }

    @Override
    public void deleteTag(Tag tag) throws ServiceException {
        try {
            tagDAO.deleteTag(tag);
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
    public boolean isTagExist(int id) throws ServiceException {
        try {
            return tagDAO.isTagExist(id);
        } catch (DAOException e) {
            throw new ServiceException("Check certificate fail", e);
        }
    }
}
