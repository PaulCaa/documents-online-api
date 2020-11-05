package ar.com.pablocaamano.documents.api.exception.http;

import ar.com.pablocaamano.commons.exception.http.CommonHttpException;
import org.springframework.http.HttpStatus;

public class LoginValidationException extends CommonHttpException {


    public LoginValidationException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public LoginValidationException(String message, HttpStatus httpStatus, Throwable cause) {
        super(message, httpStatus, cause);
    }

    public LoginValidationException(String message, HttpStatus httpStatus, String code) {
        super(message, httpStatus, code);
    }

    public LoginValidationException(String message, HttpStatus httpStatus, Throwable cause, String code) {
        super(message, httpStatus, cause, code);
    }
}
