package com.epam.esm.dao.impl.mappers;

import com.epam.esm.entity.Certificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CertificateTagMapper implements RowMapper<Certificate> {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_CREATE_DATE = "create_date";
    private static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";

    @Override
    public Certificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        Certificate certificate = new Certificate();
        certificate.setId(rs.getInt(COLUMN_ID));
        certificate.setName(rs.getString(COLUMN_NAME));
        certificate.setDescription(rs.getString(COLUMN_DESCRIPTION));
        certificate.setPrice(rs.getDouble(COLUMN_PRICE));
        certificate.setDuration(rs.getInt(COLUMN_DURATION));
        certificate.setCreateDate(rs.getString(COLUMN_CREATE_DATE));
        certificate.setLastUpdateDate(rs.getString(COLUMN_LAST_UPDATE_DATE));
        return certificate;
    }
}
