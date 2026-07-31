package com.sabujak.contest.domain.auth.entity;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AuthRowMapper implements RowMapper<Auth> {


  @Override
  public Auth mapRow(ResultSet rs, int rowNum) throws SQLException {
    Long id = rs.getLong("id");
    String uuid = rs.getString("uuid");
    String refreshToken = rs.getString("refresh_token");
    String createdAt = rs.getString("created_at");
    String updatedAt = rs.getString("updated_at");

    return Auth.of(id, uuid, refreshToken, createdAt, updatedAt);
  }
}
