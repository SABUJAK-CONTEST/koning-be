package com.sabujak.contest.global.security.config;

import com.sabujak.contest.global.common.response.ResponseDTO;
import com.sabujak.contest.global.exception.ErrorCode;
import com.sabujak.contest.global.oauth2.CustomOAuth2AuthorizationRequestResolver;
import com.sabujak.contest.global.oauth2.OAuth2LoginSuccessHandler;
import com.sabujak.contest.global.oauth2.OAuth2UserService;
import com.sabujak.contest.global.security.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tools.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final OAuth2UserService oAuth2UserService;
  private final JwtTokenFilter jwtTokenFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, ObjectMapper objectMapper,
      CustomOAuth2AuthorizationRequestResolver customOAuth2AuthorizationRequestResolver,
      OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler) throws Exception {

    // 인증 실패 시 응답 객체
    String invalidAuthenticationResponse = objectMapper
        .writeValueAsString(ResponseDTO.of(ErrorCode.UNAUTHORIZED));

    // 인가 실패 시 응답 객체
    String invalidAuthorizationResponse = objectMapper
        .writeValueAsString(ResponseDTO.of(ErrorCode.ACCESS_DENIED));


    //엔드포인트 설정
    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/users/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/v3/api-docs/**",
                "/oauth2/**",
                "/login/**"
            ).permitAll()
            .anyRequest().authenticated()
        );

    //인증/인가 예외처리
    http
        .exceptionHandling(e -> e
            .authenticationEntryPoint(((request, response, authException) -> {
              response.setContentType("application/json;charset=UTF-8");
              response.setStatus(HttpStatus.UNAUTHORIZED.value());
              response.setContentType("application/json");
              response.getWriter().write(invalidAuthenticationResponse);
            }
            ))

            .accessDeniedHandler((request, response, authException) -> {
              response.setContentType("application/json;charset=UTF-8");
              response.setStatus(HttpStatus.FORBIDDEN.value());
              response.setContentType("application/json");
              response.getWriter().write(invalidAuthorizationResponse);
            }));

    // oauth2 설정
    http
        .oauth2Login(oauth2 -> oauth2
            .authorizationEndpoint(authorization -> authorization
                .baseUri("/oauth2/authorization")
                .authorizationRequestResolver(customOAuth2AuthorizationRequestResolver)
            )
            .redirectionEndpoint(redirection -> redirection
                .baseUri("/login/oauth2/code/*"))
            .userInfoEndpoint(userInfo -> userInfo
                .userService(oAuth2UserService))
            .successHandler(oAuth2LoginSuccessHandler)
        );

    // 필터 설정
    http
        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
