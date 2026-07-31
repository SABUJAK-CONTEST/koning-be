package com.sabujak.contest.domain.auth.entity;


import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

public class Auth {

  private Long id;
  private String uuid;
  private String refreshToken;
  private String createdAt;
  private String updatedAt;

  @Builder
  private Auth(Long id, String uuid, String refreshToken, String createdAt, String updatedAt) {
    this.id = id;
    this.uuid = uuid;
    this.refreshToken = refreshToken;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return this.id;
  }

  public String getUuid() {
    return this.uuid;
  }

  public String getRefreshToken() {
    return this.refreshToken;
  }

  public String getCreatedAt() {
    return this.createdAt;
  }

  public String getUpdatedAt() {
    return this.updatedAt;
  }

  public void updateRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public static Auth createAuth(String refreshToken) {

    return Auth.builder()
        .id(null)
        .uuid(UUID.randomUUID().toString())
        .refreshToken(refreshToken)
        .createdAt(LocalDateTime.now().toString())
        .updatedAt(null)
        .build();
  }

  public static Auth createAuth(String uuid, String refreshToken) {
    return Auth.builder()
        .id(null)
        .uuid(uuid)
        .refreshToken(refreshToken)
        .createdAt(LocalDateTime.now().toString())
        .updatedAt(null)
        .build();
  }

  public static Auth of(Long id, String uuid, String refreshToken, String createdAt, String updatedAt) {

    return Auth.builder()
        .id(id)
        .uuid(uuid)
        .refreshToken(refreshToken)
        .createdAt(createdAt)
        .updatedAt(updatedAt)
        .build();

  }



}
