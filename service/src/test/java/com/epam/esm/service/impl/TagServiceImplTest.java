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
    private final TagDAOImpl tagDAO = Mockito.mock(TagDAOImpl.class);
    private final TagServiceImpl tagService = new TagServiceImpl(tagDAO);

    @Test
    void createTag() throws ServiceException, DAOException {
        tagService.createTag(tag.getName());
        Mockito.verify(tagDAO).createTag(tag.getName());
    }

    @Test
    void createTagExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(tagDAO).createTag(tag.getName());
        assertThrows(ServiceException.class, () -> tagService.createTag(tag.getName()), "Expected ServiceException");
    }

    @Test
    void deleteTagExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(tagDAO).deleteTag(tag.getId());
        assertThrows(ServiceException.class, () -> tagService.deleteTag(tag.getId()), "Expected ServiceException");
    }

    @Test
    void deleteTag() throws ServiceException, DAOException {
        tagService.deleteTag(tag.getId());
        Mockito.verify(tagDAO).deleteTag(tag.getId());
    }

    @Test
    void getTag() throws ServiceException, DAOException {
        tagService.getTag(tag.getId());
        Mockito.verify(tagDAO).getTag(tag.getId());
    }

    @Test
    void getTags() throws ServiceException, DAOException {
        tagService.getTags();
        Mockito.verify(tagDAO).getTags();
    }

    @Test
    void getTagsExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(tagDAO).getTags();
        assertThrows(ServiceException.class, tagService::getTags, "Expected ServiceException");
    }

    @Test
    void getTagExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(tagDAO).getTag(tag.getId());
        assertThrows(ServiceException.class, () -> tagService.getTag(tag.getId()), "Expected ServiceException");
    }

    @Test
    void isTagExist() throws ServiceException, DAOException {
        tagService.isTagExist(tag.getId());
        Mockito.verify(tagDAO).isTagExist(tag.getId());
    }

    @Test
    void isTagExistExc() throws DAOException {
        Mockito.doThrow(new DAOException()).when(tagDAO).isTagExist(tag.getId());
        assertThrows(ServiceException.class, () -> tagService.isTagExist(tag.getId()), "Expected ServiceException");
    }
}