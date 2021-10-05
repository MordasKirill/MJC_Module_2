package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagDAO {
    List<Tag> getTags() throws DAOException;

    void createTag(Tag tag) throws DAOException;

    void deleteTag(Tag tag) throws DAOException;
}
