package com.sabujak.contest.global.security.exception;


import com.sabujak.contest.global.exception.CustomException;
import com.sabujak.contest.global.exception.ErrorCode;

public class InvalidAccessJwtException extends CustomException {

  public InvalidAccessJwtException(ErrorCode errorCode) {
    super(errorCode);
  }
}
