package com.sabujak.contest.domain.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {

  private static final Logger log = LoggerFactory.getLogger(User.class);

  private Long id;
  private String name;
  private String uuid;
  private String email;
  private String role;
  private String provider;
  private String providerId;
  private String createdAt;
  private String updatedAt;

  @Builder
  private User(Long id, String name, String uuid, String email, String role,
      String provider, String providerId, String createdAt, String updatedAt) {
    this.id = id;
    this.name = name;
    this.uuid = uuid;
    this.email = email;
    this.role = role;
    this.provider = provider;
    this.providerId = providerId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getUuid() {
    return this.uuid;
  }

  public String getRole() {
    return this.role;
  }

  public String getProvider() {
    return this.provider;
  }

  public String getProviderId() {
    return this.providerId;
  }

  public String getCreatedAt() {
    return this.createdAt;
  }

  public String getUpdatedAt() {
    return this.updatedAt;
  }

  public String getEmail() {
    return this.email;
  }

  public static User createGuest(String name, String email, String provider, String providerId) {

    String uuid = UUID.randomUUID().toString();
    String role = Role.GUEST.toString();
    String createdAt = LocalDateTime.now().toString();
    String updatedAt = null;

    return User.builder()
        .id(null)
        .name(name)
        .email(email)
        .provider(provider)
        .providerId(providerId)
        .uuid(uuid)
        .role(role)
        .createdAt(createdAt)
        .updatedAt(updatedAt)
        .build();
  }

  public static User createMember(String name, String email, String provider, String providerId) {

    String uuid = UUID.randomUUID().toString();
    String role = Role.MEMBER.toString();
    String createdAt = LocalDateTime.now().toString();
    String updatedAt = null;

    return User.builder()
        .id(null)
        .name(name)
        .email(email)
        .provider(provider)
        .providerId(providerId)
        .uuid(uuid)
        .role(role)
        .createdAt(createdAt)
        .updatedAt(updatedAt)
        .build();
  }

  public static User of(Long id, String name, String uuid, String email, String role,
      String provider, String providerId, String createdAt, String updatedAt) {
    if (id == null || name == null) {
      log.info("id 또는 name 필드는 null일 수 없습니다. id: {}, name: {}", id, name);
      throw new IllegalArgumentException("id 또는 name 필드는 null일 수 없습니다.");
    }

    return User.builder()
        .id(id)
        .name(name)
        .uuid(uuid)
        .email(email)
        .role(role)
        .provider(provider)
        .providerId(providerId)
        .createdAt(createdAt)
        .updatedAt(updatedAt)
        .build();
  }

}
