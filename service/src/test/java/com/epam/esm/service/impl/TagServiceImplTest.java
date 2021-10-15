package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.ServiceException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TagServiceImplTest {
    private static final Tag tag = new Tag(1, "testName");
    private static final TagDAOImpl tagDAO = Mockito.mock(TagDAOImpl.class);
    private static final TagServiceImpl tagService = new TagServiceImpl(tagDAO);

    @Test
    void createTag() throws ServiceException, DAOException {
        tagService.createTag(tag);
        Mockito.verify(tagDAO).createTag(tag);
    }

    @Test
    void createTagExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(tagDAO).createTag(tag);
        assertThrows(ServiceException.class, () -> tagService.createTag(tag), "Expected ServiceException");
    }

    @Test
    void deleteTag() throws ServiceException, DAOException {
        TagDAOImpl tagDAO = Mockito.mock(TagDAOImpl.class);
        TagServiceImpl tagService = new TagServiceImpl(tagDAO);
        tagService.deleteTag(tag);
        Mockito.verify(tagDAO).deleteTag(tag);
    }

    @Test
    void deleteTagExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(tagDAO).deleteTag(tag);
        assertThrows(ServiceException.class, () -> tagService.deleteTag(tag), "Expected ServiceException");
    }

    @Test
    void getTags() throws ServiceException, DAOException {
        TagDAOImpl tagDAO = Mockito.mock(TagDAOImpl.class);
        TagServiceImpl tagService = new TagServiceImpl(tagDAO);
        tagService.getTags();
        Mockito.verify(tagDAO).getTags();
    }

    @Test
    void getTagsExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(tagDAO).getTags();
        assertThrows(ServiceException.class, tagService::getTags, "Expected ServiceException");
    }
}