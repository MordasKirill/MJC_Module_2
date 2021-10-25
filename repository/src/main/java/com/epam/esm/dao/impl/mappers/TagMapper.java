package com.epam.esm.dao.impl.mappers;

import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper for tag
 * used to put result set value
 * to object fields correctly
 */
public class TagMapper implements RowMapper<Tag> {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    /**
     * @param rs     ResultSet value
     * @param rowNum number of rows
     * @return business object Tag
     * @throws SQLException in case of unforeseen ex
     */
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tag tag = new Tag();
        tag.setId(rs.getInt(COLUMN_ID));
        tag.setName(rs.getString(COLUMN_NAME));
        return tag;
    }
}
