package com.pikecape.springboot.data.repository;

import com.pikecape.springboot.data.model.Duck;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class DuckRowMapper implements RowMapper<Duck> {
  @Override
  public Duck mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
    return Duck.builder()
        .id(resultSet.getInt("ID"))
        .uid(resultSet.getString("UID"))
        .name(resultSet.getString("NAME"))
        .createdAt(resultSet.getTimestamp("CREATED_AT").toLocalDateTime())
        .updatedAt(resultSet.getTimestamp("UPDATED_AT") != null
            ? resultSet.getTimestamp("UPDATED_AT").toLocalDateTime()
            : null
        )
        .build();
  }
}
