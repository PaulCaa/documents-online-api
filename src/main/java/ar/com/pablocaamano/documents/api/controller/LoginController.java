package ar.com.pablocaamano.documents.api.controller;

import ar.com.pablocaamano.commons.builder.ResponseBuilder;
import ar.com.pablocaamano.commons.exception.http.CommonHttpException;
import ar.com.pablocaamano.commons.rest.Error;
import ar.com.pablocaamano.commons.rest.RestResponse;
import ar.com.pablocaamano.commons.util.TimeGenerator;
import ar.com.pablocaamano.documents.api.security.model.User;
import ar.com.pablocaamano.documents.api.security.service.LoginService;
import ar.com.pablocaamano.documents.api.utils.ApiUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController(value = "/api/login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService service;

    @ApiOperation(value = "Login user", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = " Login sucess"),
            @ApiResponse(code = 403, message = "User forbidden"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/user")
    public ResponseEntity<RestResponse> login(
            @RequestParam(name = "user", required = true) String username,
            @RequestParam(name = "password", required = true) String pwd,
            @RequestHeader(name = "trace", required = false) String trace,
            HttpServletRequest http
    ) {
        trace = ApiUtils.traceValidation(trace);
        try {
            LOGGER.info("[" + trace + "] starting login by user: " + username);
            String token = service.getTokenBy(username,trace);
            User user = new User();
            user.setToken(token);
            LOGGER.info("[" + trace + "] login success");
            return new ResponseEntity<RestResponse>(ResponseBuilder.init()
                    .addRequestCode(ApiUtils.RESPONSE_OK_CODE)
                    .addRequestMethod(http.getMethod())
                    .addRequestUri(http.getRequestURI())
                    .addTimestamp(TimeGenerator.generateStringDateTime())
                    .addTraceRequest(trace)
                    .addData(user)
                    .build()
                    , HttpStatus.OK);
        } catch (CommonHttpException exception) { // CUSTOM ERRORS
            LOGGER.info("[" + trace + "] login fail");
            LOGGER.error("[" + trace + "] login fail", exception);
            Error error = new Error(exception.getMessage(),exception);
            return new ResponseEntity<RestResponse>(ResponseBuilder.init()
                    .addRequestCode(exception.getCode())
                    .addRequestMethod(http.getMethod())
                    .addRequestUri(http.getRequestURI())
                    .addTimestamp(TimeGenerator.generateStringDateTime())
                    .addError(error)
                    .build()
                    , exception.getStatus());
        } catch (Exception exception) { // GENERIC ERROR
            LOGGER.info("[" + trace + "] internal error in login process");
            LOGGER.error("[" + trace + "] internal error in login process", exception);
            Error error = new Error(exception.getMessage(),exception);
            return new ResponseEntity<RestResponse>(ResponseBuilder.init()
                    .addRequestCode(ApiUtils.RESPONSE_INTERNAL_ERROR_CODE)
                    .addRequestMethod(http.getMethod())
                    .addRequestUri(http.getRequestURI())
                    .addTimestamp(TimeGenerator.generateStringDateTime())
                    .addError(error)
                    .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
