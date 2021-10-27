package com.epam.esm.dao.impl;

import com.epam.esm.dao.CertificateTagDAO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.mappers.CertificateMapper;
import com.epam.esm.entity.Certificate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CertificateTagDAOImpl implements CertificateTagDAO {
    private static final String SELECT_FROM_CERTIFICATE_LIKE = "select certificate.*, group_concat(tag.name) from certificate join certificate_has_tag on certificate_has_tag.cerf_id = certificate.id join tag on tag.id = certificate_has_tag.tag_id where %s ";
    private static final String SELECT_FROM_CERTIFICATE_WHERE_ASC = "select certificate.*, group_concat(tag.name) from gift_certificates.certificate join certificate_has_tag on certificate_has_tag.cerf_id = certificate.id join tag on tag.id = certificate_has_tag.tag_id group by certificate.id";
    private static final Logger LOG = Logger.getLogger(CertificateTagDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CertificateTagDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Certificate> getCertificatesByTag(Integer id) throws DAOException {
        return jdbcTemplate.query(String.format(SELECT_FROM_CERTIFICATE_LIKE, "certificate_has_tag.tag_id = ? group by certificate.id"), new CertificateMapper(), id);
    }

    @Override
    public List<Certificate> getCertificatesByNamePart(String name) throws DAOException {
        return jdbcTemplate.query(String.format(SELECT_FROM_CERTIFICATE_LIKE, "certificate.name like ? group by certificate.id"), new CertificateMapper(), name);
    }

    public List<Certificate> getCertificatesSorted(String sortParam, String direction) throws DAOException {
        String orderBy = SELECT_FROM_CERTIFICATE_WHERE_ASC + " order by %s %s";
        try {
            return jdbcTemplate.query(String.format(orderBy, sortParam, direction), new CertificateMapper());
        } catch (BadSqlGrammarException e) {
            throw new DAOException(e.getMostSpecificCause().toString());
        }
    }
}
