package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
    private static final Tag tag = new Tag(1, "testName");
    @Mock
    private TagDAOImpl tagDAO;
    @InjectMocks
    private TagServiceImpl tagService;

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
    void deleteTagExc() throws DAOException, ServiceException {
        Mockito.when(tagService.isTagExist(tag.getId())).thenReturn(true);
        Mockito.doThrow(new DAOException()).when(tagDAO).deleteTag(tag.getId());
        assertThrows(ServiceException.class, () -> tagService.deleteTag(tag.getId()), "Expected ServiceException");
    }

    @Test
    void deleteTag() throws ServiceException, DAOException {
        Mockito.when(tagService.isTagExist(tag.getId())).thenReturn(true);
        tagService.deleteTag(tag.getId());
        Mockito.verify(tagDAO).deleteTag(tag.getId());
    }

    @Test
    void getTag() throws ServiceException, DAOException {
        Mockito.when(tagService.isTagExist(tag.getId())).thenReturn(true);
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
    void getTagExc() throws DAOException, ServiceException {
        Mockito.when(tagService.isTagExist(tag.getId())).thenReturn(true);
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