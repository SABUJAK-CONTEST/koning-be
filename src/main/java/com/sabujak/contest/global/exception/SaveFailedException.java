package com.sabujak.contest.global.exception;

public class SaveFailedException extends CustomException {

  public SaveFailedException(ErrorCode errorCode) {
    super(errorCode);
  }
}
