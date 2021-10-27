package com.epam.esm.dao.impl;

import com.epam.esm.dao.CertificateDAO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.impl.mappers.CertificateMapper;
import com.epam.esm.dao.impl.mappers.TagMapper;
import com.epam.esm.entity.Certificate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CertificatesDAOImpl implements CertificateDAO {
    private static final String DELETE_FROM_CERTIFICATE = "delete from certificate where id=?";
    private static final String UPDATE_CERTIFICATE = "update certificate set name = ?, price = ?, description = ? where id = ?";
    private static final String SELECT = "select certificate.*, group_concat(tag.name) from certificate join certificate_tag on certificate_tag.cerf_id = certificate.id join tag on tag.id = certificate_tag.tag_id group by certificate.id";
    private static final String SELECT_SINGLE_CERTIFICATE = "select certificate.*, group_concat(tag.name) from certificate join certificate_tag on certificate_tag.cerf_id = certificate.id join tag on tag.id = certificate_tag.tag_id where certificate.id = ?";
    private static final String SELECT_CERTIFICATE = "select * from certificate where id = ?";
    private static final String INSERT_INTO_CERTIFICATE = "insert into certificate (name, description, price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, NOW(), NOW())";
    private static final String INSERT_INTO_CERTIFICATE_HAS_TAG = "insert into certificate_tag (cerf_id, tag_id) VALUES (?, (select tag.id from tag where tag.name = ?))";
    private static final Logger LOG = Logger.getLogger(CertificatesDAOImpl.class);
    private final TagDAO tagDAO;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CertificatesDAOImpl(TagDAO tagDAO, JdbcTemplate jdbcTemplate) {
        this.tagDAO = tagDAO;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Certificate> getCertificates() throws DAOException {
        return jdbcTemplate.query(SELECT, new CertificateMapper());
    }

    @Override
    public Certificate getCertificate(Integer id) throws DAOException {
        return jdbcTemplate.queryForObject(SELECT_SINGLE_CERTIFICATE, new CertificateMapper(), id);
    }

    @Override
    @Transactional
    public void createCertificate(Certificate certificate) throws DAOException {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_CERTIFICATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, certificate.getName());
            statement.setString(2, certificate.getDescription());
            statement.setDouble(3, certificate.getPrice());
            statement.setInt(4, certificate.getDuration());
            return statement;
        }, holder);
        for (String string : certificate.getTagNames()) {
            if (!tagDAO.isTagExist(string)) {
                tagDAO.createTag(string);
            }
            jdbcTemplate.update(INSERT_INTO_CERTIFICATE_HAS_TAG, Objects.requireNonNull(holder.getKey()).intValue(), string);
        }
    }

    @Override
    public void deleteCertificates(Integer id) throws DAOException {
        jdbcTemplate.update(DELETE_FROM_CERTIFICATE, id);
    }

    @Override
    public void updateCertificates(Integer id, Certificate certificate) throws DAOException {
        jdbcTemplate.update(UPDATE_CERTIFICATE, certificate.getName(), certificate.getPrice(), certificate.getDescription(), id);
    }

    @Override
    public boolean isCertificateExist(Integer id) throws DAOException {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_CERTIFICATE, new TagMapper(), id)).isPresent();
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
