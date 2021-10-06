package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TagServiceImplTest {
    private static final Tag tag = new Tag(1, "testName");

    @Test
    void createTag() throws ServiceException {
        TagServiceImpl tagService = Mockito.mock(TagServiceImpl.class);
        tagService.createTag(tag);
        Mockito.verify(tagService).createTag(tag);
    }

    @Test
    void createTagExc() throws ServiceException {
        TagServiceImpl tagService = Mockito.mock(TagServiceImpl.class);
        Mockito.doThrow(new ServiceException()).when(tagService).createTag(tag);
        assertThrows(ServiceException.class, () -> tagService.createTag(tag), "Expected ServiceException");
    }

    @Test
    void deleteTag() throws ServiceException {
        TagServiceImpl tagService = Mockito.mock(TagServiceImpl.class);
        tagService.deleteTag(tag);
        Mockito.verify(tagService).deleteTag(tag);
    }

    @Test
    void deleteTagExc() throws ServiceException {
        TagServiceImpl tagService = Mockito.mock(TagServiceImpl.class);
        Mockito.doThrow(new ServiceException()).when(tagService).deleteTag(tag);
        assertThrows(ServiceException.class, () -> tagService.deleteTag(tag), "Expected ServiceException");
    }

    @Test
    void getTags() throws ServiceException {
        TagServiceImpl tagService = Mockito.mock(TagServiceImpl.class);
        tagService.getTags();
        Mockito.verify(tagService).getTags();
    }

    @Test
    void getTagsExc() throws ServiceException {
        TagServiceImpl tagService = Mockito.mock(TagServiceImpl.class);
        Mockito.doThrow(new ServiceException()).when(tagService).getTags();
        assertThrows(ServiceException.class, tagService::getTags, "Expected ServiceException");
    }
}