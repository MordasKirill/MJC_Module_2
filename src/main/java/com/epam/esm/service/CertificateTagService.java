package com.epam.esm.service;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;

import java.util.List;

public interface CertificateTagService {
    List<Certificate> getCertificatesByTag(Tag tag) throws ServiceException;

    List<Certificate> getCertificatesByNamePart(String name) throws ServiceException;

    List<Certificate> getCertificatesSortedByPrice() throws ServiceException;
}
