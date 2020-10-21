package ar.com.pablocaamano.documents.api.exception;

import ar.com.pablocaamano.commons.exception.CommonException;

public class BadParameterException extends CommonException {

    private static final String DEFAULT_MESSAGE = "Null or void required parameter received";

    public BadParameterException() {
        super(DEFAULT_MESSAGE);
    }

    public  BadParameterException(String message) {
        super(message);
    }
}
