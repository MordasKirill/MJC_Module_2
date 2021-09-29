package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagService {
    void createTag(Tag tag) throws ServiceException;
    void deleteTag(Tag tag) throws ServiceException;
    List<Tag> getTags() throws ServiceException;
}
