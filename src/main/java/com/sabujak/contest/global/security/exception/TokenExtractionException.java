package com.sabujak.contest.global.security.exception;


import com.sabujak.contest.global.exception.CustomException;
import com.sabujak.contest.global.exception.ErrorCode;

public class TokenExtractionException extends CustomException {

  public TokenExtractionException(ErrorCode errorCode) {
    super(errorCode);
  }
}
