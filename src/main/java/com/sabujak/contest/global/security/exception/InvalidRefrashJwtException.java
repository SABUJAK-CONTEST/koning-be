package com.sabujak.contest.global.security.exception;


import com.sabujak.contest.global.exception.CustomException;
import com.sabujak.contest.global.exception.ErrorCode;

public class InvalidRefrashJwtException extends CustomException {

  public InvalidRefrashJwtException(ErrorCode errorCode) {
    super(errorCode);
  }
}
