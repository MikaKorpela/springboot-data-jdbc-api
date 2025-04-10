package com.pikecape.springboot.data.repository;

import com.pikecape.springboot.data.model.Duck;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DuckRepository {

  private final JdbcTemplate jdbcTemplate;

  public DuckRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Duck> findAll() {
    String sql = "SELECT * FROM DUCKS";

    return jdbcTemplate.query(
        sql,
        preparedStatement -> {},
        new DuckRowMapper());
  }

  public Duck findByUid(String uid) {
    String sql = "SELECT * FROM DUCKS WHERE UID = ?";

    return jdbcTemplate.queryForObject(
        sql,
        new DuckRowMapper(),
        uid
    );
  }

  public int create(Duck duck) {
    String sql = "INSERT INTO DUCKS (UID, NAME, CREATED_AT) VALUES (?,?,?)";

    return jdbcTemplate.update(
        sql,
        preparedStatement -> {
          preparedStatement.setString(1, duck.getUid());
          preparedStatement.setString(2, duck.getName());
          preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
        }
    );
  }

  public int update(String uid, Duck duck) {
    String sql = "UPDATE DUCKS SET NAME = ?, UPDATED_AT =? WHERE UID = ?";

    return jdbcTemplate.update(
        sql,
        preparedStatement -> {
          preparedStatement.setString(1, duck.getName());
          preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
          preparedStatement.setString(3, duck.getUid());
        });
  }

  public int delete(String uid) {
    String sql = "DELETE FROM DUCKS WHERE UID = ?";

    return jdbcTemplate.update(
        sql,
        preparedStatement -> {
          preparedStatement.setString(1, uid);
        }
    );
  }
}
