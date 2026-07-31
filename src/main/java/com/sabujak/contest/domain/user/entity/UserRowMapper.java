package com.sabujak.contest.domain.user.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    Long id = rs.getLong("id");
    String name = rs.getString("name");
    String uuid = rs.getString("uuid");
    String email = rs.getString("email");
    String role = rs.getString("role");
    String provider = rs.getString("provider");
    String providerId = rs.getString("provider_id");
    String createdAt = rs.getString("created_at");
    String updatedAt = rs.getString("updated_at");
    return User.of(id, name, uuid, email, role, provider, providerId, createdAt, updatedAt);
  }

}
