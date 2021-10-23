package com.epam.esm.dao.impl;

import com.epam.esm.dao.CertificateDAO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.impl.mappers.CertificateMapper;
import com.epam.esm.dao.impl.mappers.TagMapper;
import com.epam.esm.entity.Certificate;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CertificatesDAOImpl implements CertificateDAO {
    private static final String DELETE_FROM_CERTIFICATE = "delete from certificate where id=?";
    private static final String UPDATE_CERTIFICATE = "update certificate set name = ?, price = ?, description = ? where id = ?";
    private static final String SELECT = "select certificate.*, group_concat(tag.name) from certificate join certificate_has_tag on certificate_has_tag.cerf_id = certificate.id join tag on tag.id = certificate_has_tag.tag_id group by certificate.id";
    private static final String SELECT_SINGLE_CERTIFICATE = "select certificate.*, group_concat(tag.name) from certificate join certificate_has_tag on certificate_has_tag.cerf_id = certificate.id join tag on tag.id = certificate_has_tag.tag_id where certificate.id = ?";
    private static final String SELECT_CERTIFICATE = "select * from certificate where id = ?";
    private static final String INSERT_INTO_CERTIFICATE = "insert into certificate (name, description, price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, NOW(), NOW())";
    private static final String INSERT_INTO_CERTIFICATE_HAS_TAG = "insert into certificate_has_tag (cerf_id, tag_id) VALUES ((select max(id) from certificate), (select tag.id from tag where tag.name = ?))";
    private static final Logger LOG = Logger.getLogger(CertificatesDAOImpl.class);
    @Autowired
    private TagDAO tagDAO;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Certificate> getCertificates() throws DAOException {
        return jdbcTemplate.query(SELECT, new CertificateMapper());
    }

    @Override
    public Certificate getCertificate(int id) throws DAOException {
        return jdbcTemplate.queryForObject(SELECT_SINGLE_CERTIFICATE, new CertificateMapper(), id);
    }

    @Override
    @Transactional
    public void createCertificate(Certificate certificate) throws DAOException {
        jdbcTemplate.update(INSERT_INTO_CERTIFICATE, certificate.getName(), certificate.getDescription(), certificate.getPrice(), certificate.getDuration());
        for (String string : certificate.getTagName()) {
            if (!tagDAO.isTagExist(string)) {
                tagDAO.createTag(string);
            }
            jdbcTemplate.update(INSERT_INTO_CERTIFICATE_HAS_TAG, string);
        }
    }

    @Override
    public void deleteCertificates(int id) throws DAOException {
        jdbcTemplate.update(DELETE_FROM_CERTIFICATE, id);
    }

    @Override
    public void updateCertificates(Certificate certificate) throws DAOException {
        jdbcTemplate.update(UPDATE_CERTIFICATE, certificate.getName(), certificate.getPrice(), certificate.getDescription(), certificate.getId());
    }

    @Override
    public boolean isCertificateExist(int id) throws DAOException {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_CERTIFICATE, new TagMapper(), id)).isPresent();
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
