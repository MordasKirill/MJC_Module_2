package com.epam.esm.service.impl;

import com.epam.esm.dao.CommonCRUDOperations;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.CommonCRUDOperationsImpl;
import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.SpringConfig;
import com.epam.esm.service.TagService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Import({SpringConfig.class})
public class TagServiceImpl implements TagService {
    private TagDAOImpl tagDAO;

    public TagServiceImpl(TagDAOImpl tagDAO){
        this.tagDAO = tagDAO;
    }

    @Override
    public void createTag(Tag tag) throws ServiceException{
        try {
            tagDAO.createTag(tag);
        } catch (DAOException e) {
            throw new ServiceException("CreateTag fail.", e);
        }
    }

    @Override
    public void deleteTag(Tag tag) throws ServiceException{
        try {
            tagDAO.deleteTag(tag);
        } catch (DAOException e) {
            throw new ServiceException("DeleteTag fail.", e);
        }
    }

    @Override
    public List<Tag> getTags() throws ServiceException{
        try {
            return tagDAO.getTags();
        } catch (DAOException e) {
            throw new ServiceException("GetTags fail.", e);
        }
    }
}
