package com.sabujak.contest.domain.auth.repository;

import com.sabujak.contest.domain.auth.entity.Auth;
import com.sabujak.contest.domain.auth.entity.AuthRowMapper;
import com.sabujak.contest.domain.user.entity.UserRowMapper;
import com.sabujak.contest.global.exception.ErrorCode;
import com.sabujak.contest.global.exception.SaveFailedException;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {

  private final JdbcTemplate jdbcTemplate;
  private final RowMapper<Auth> authRowMapper = new AuthRowMapper();

  public AuthRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void update(Auth auth) {

    String sql = "update auth set "
        + "uuid = ?, "
        + "refresh_token = ?, "
        + "updated_at = ? "
        + "where id = ?";
    jdbcTemplate.update(sql, auth.getUuid(), auth.getRefreshToken(), LocalDateTime.now().toString(), auth.getId());
  }

  public Auth save(Auth auth) {

    String saveSql = "insert into auth (uuid, refresh_token, created_at, updated_at) values (?, ?, ?, ?)";
    jdbcTemplate.update(saveSql, auth.getUuid(), auth.getRefreshToken(), auth.getCreatedAt(), auth.getUpdatedAt());

    String returnSql = "select * from auth where uuid = ?";

    Auth response = jdbcTemplate.query(returnSql, authRowMapper, auth.getUuid())
        .stream()
        .findFirst()
        .orElseThrow(
            () -> new SaveFailedException(ErrorCode.SAVE_FAILED_EXCEPTION)
        );

    return response;

  }

  public Optional<Auth> findByUuid(String uuid) {
    String sql = "select * from auth where uuid = ?";
    return jdbcTemplate.query(sql, authRowMapper, uuid)
        .stream()
        .findFirst();
  }



}
