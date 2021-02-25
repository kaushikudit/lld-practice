package com.example.lld.lldpractice.Exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomException extends RuntimeException{


  private Integer errorCode;
  private String errorMessage;
  private HttpStatus statusCode;
  private String description;
  private Boolean retryable;

  public CustomException() {
    super();
  }

  public CustomException(final String exception) {
    this.errorMessage = exception;
  }

  public CustomException(final Exception ex) {
    super(ex);
  }

  public CustomException(final String error, final int code) {
//    super(error);

    this.errorMessage = error;
    this.errorCode = code;
  }

  public CustomException(HttpStatus statusCode, String errorMessage) {
    this.statusCode = statusCode;
    this.errorMessage = errorMessage;
  }

}
