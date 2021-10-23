package com.epam.esm.dao.impl;

import com.epam.esm.dao.CertificateTagDAO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.mappers.CertificateTagMapper;
import com.epam.esm.entity.Certificate;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CertificateTagDAOImpl implements CertificateTagDAO {
    private static final String SELECT_FROM_CERTIFICATE_TAG = "select * from certificate_has_tag left join certificate on (certificate.id = cerf_id) where certificate_has_tag.tag_id = ?";
    private static final String SELECT_FROM_CERTIFICATE_LIKE = "select certificate.*, group_concat(tag.name) from certificate join certificate_has_tag on certificate_has_tag.cerf_id = certificate.id join tag on tag.id = certificate_has_tag.tag_id where certificate.name like ?";
    private static final String SELECT_FROM_CERTIFICATE_WHERE_ASC = "select certificate.*, group_concat(tag.name) from certificate join certificate_has_tag on certificate_has_tag.cerf_id = certificate.id join tag on tag.id = certificate_has_tag.tag_id group by certificate.id order by certificate.name asc, certificate.price desc";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Certificate> getCertificatesByTag(int id) throws DAOException {
        return jdbcTemplate.query(SELECT_FROM_CERTIFICATE_TAG, new CertificateTagMapper(), id);
    }

    @Override
    public List<Certificate> getCertificatesByNamePart(String name) throws DAOException {
        return jdbcTemplate.query(SELECT_FROM_CERTIFICATE_LIKE, new CertificateTagMapper(), name);
    }

    public List<Certificate> getCertificatesSortedByPrice() throws DAOException {
        return jdbcTemplate.query(SELECT_FROM_CERTIFICATE_WHERE_ASC, new CertificateTagMapper());
    }
}
