package ar.com.pablocaamano.documents.api.utils;

import ar.com.pablocaamano.commons.util.IdGenerator;
import ar.com.pablocaamano.documents.api.exception.http.BadParameterHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

/**
 * Utils for controllers
 * @author Caamaño, Pablo
 * @url http://pablocaamano.com.ar
 */
public class ApiUtils {

    public static final String RESPONSE_OK_CODE = "OK";
    public static final String RESPONSE_LOGIN_ERROR_CODE = "ERR-01";
    public static final String RESPONSE_INTERNAL_ERROR_CODE = "ERR-02";
    public static final String RESPONSE_REQUEST_ERROR_CODE = "ERR-03";

    /**
     * Validate trace in and generate when this is blank
     * @param trace String
     * @return String example: 236339d7-fe16-4162-a7a0-e9764aa4a7be
     */
    public static String traceValidation(String trace) {
        if(StringUtils.isEmpty(trace)){
            trace = IdGenerator.getStringIdRadom();
        }
        return trace;
    }

    /**
     * Validate required String param
     * @param name String, name or key of parameter
     * @param value String value of param
     */
    public static void validateParam(String name, String value) {
        if(StringUtils.isEmpty(value)){
            throw new BadParameterHttpException("The param '" + name + "' is null or empty", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Validate required int param
     * @param name String, name or key of parameter
     * @param value int value of param
     */
    public static void validateParam(String name, int value) {
        if(value == 0){
            throw new BadParameterHttpException("The param '" + name + "' is null or empty", HttpStatus.BAD_REQUEST);
        }
    }
}
