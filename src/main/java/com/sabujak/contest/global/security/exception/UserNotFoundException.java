package com.sabujak.contest.global.security.exception;

import com.sabujak.contest.global.exception.CustomException;
import com.sabujak.contest.global.exception.ErrorCode;

public class UserNotFoundException extends CustomException {

  public UserNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
