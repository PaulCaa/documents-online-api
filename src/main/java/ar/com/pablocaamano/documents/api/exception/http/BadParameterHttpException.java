package ar.com.pablocaamano.documents.api.exception.http;

import ar.com.pablocaamano.commons.exception.http.CommonHttpException;
import ar.com.pablocaamano.documents.api.utils.ApiUtils;
import org.springframework.http.HttpStatus;

public class BadParameterHttpException extends CommonHttpException {

    private static final String DEFAULT_MESSAGE = "Null or void required parameter received";

    public BadParameterHttpException() {
        super(DEFAULT_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR, ApiUtils.RESPONSE_REQUEST_ERROR_CODE);
    }

    public BadParameterHttpException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, ApiUtils.RESPONSE_REQUEST_ERROR_CODE);
    }

    public BadParameterHttpException(HttpStatus httpStatus) {
        super(DEFAULT_MESSAGE, httpStatus, ApiUtils.RESPONSE_REQUEST_ERROR_CODE);
    }

    public BadParameterHttpException(String message, HttpStatus httpStatus) {
        super(message, httpStatus,  ApiUtils.RESPONSE_REQUEST_ERROR_CODE);
    }
}
