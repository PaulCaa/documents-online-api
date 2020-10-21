package ar.com.pablocaamano.documents.api.controller;

import ar.com.pablocaamano.commons.builder.ResponseBuilder;
import ar.com.pablocaamano.commons.exception.http.CommonHttpException;
import ar.com.pablocaamano.commons.rest.Error;
import ar.com.pablocaamano.commons.rest.RestResponse;
import ar.com.pablocaamano.commons.util.TimeGenerator;
import ar.com.pablocaamano.documents.api.model.DTO.DocumentDTO;
import ar.com.pablocaamano.documents.api.model.Document;
import ar.com.pablocaamano.documents.api.service.DocumentsService;
import ar.com.pablocaamano.documents.api.utils.ApiUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/documents")
public class DocumentsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentsController.class);

    @Autowired
    private DocumentsService service;

    @ApiOperation(value = "Get Document by Id", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = " Resquest sucess"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "User forbidden"),
            @ApiResponse(code = 404, message = "Not found document"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RestResponse> getDecumentById(
            @PathVariable int id,
            @RequestParam(name = "sector") int sector,
            @RequestParam(name = "entity") int entity,
            @RequestHeader(name = "trace", required = false) String trace,
            @RequestHeader(name = "user", required = true) String user,
            @RequestHeader(name = "password", required = true) String password,
            HttpServletRequest http)
    {
        trace = ApiUtils.traceValidation(trace);
        LOGGER.info("[" + trace + "] get document by id: " + id + ", of entity: " + entity + " and sector: " + sector);
        try {
            ApiUtils.validateParam("sector", sector);
            ApiUtils.validateParam("entity", entity);
            // TODO validate user
            Document result = this.service.findDocument(trace,id);
            return new ResponseEntity<RestResponse>(ResponseBuilder.init()
                        .addRequestCode(ApiUtils.RESPONSE_OK_CODE)
                        .addRequestMethod(http.getMethod())
                        .addRequestUri(http.getRequestURI())
                        .addTimestamp(TimeGenerator.generateStringDateTime())
                        .addTraceRequest(trace)
                        .addData(new DocumentDTO())
                        .build()
                    , HttpStatus.OK);
        } catch (CommonHttpException exception) {
            LOGGER.info("[" + trace + "] error getting document by id: " + id);
            Error error = new Error(exception.getMessage(), exception);
            return new ResponseEntity<RestResponse>(ResponseBuilder.init()
                    .addRequestCode(exception.getCode())
                    .addRequestMethod(http.getMethod())
                    .addRequestUri(http.getRequestURI())
                    .addTimestamp(TimeGenerator.generateStringDateTime())
                    .addError(error)
                    .build()
                    , exception.getStatus());
        } catch (Exception exception) {
            LOGGER.info("[" + trace + "] error getting document by id: " + id + ", of entity: " + entity + " and sector: " + sector);
            Error error = new Error(exception.getMessage(), exception);
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

    @ApiOperation(value = "Inset a new Document", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = " Resquest sucess"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "User forbidden"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping(value = "/insert", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RestResponse> insertDocument(
            @RequestBody DocumentDTO document,
            @RequestHeader(name = "trace", required = false) String trace,
            @RequestHeader(name = "user", required = true) String user,
            @RequestHeader(name = "password", required = true) String password,
            HttpServletRequest http
    ) {
        trace = ApiUtils.traceValidation(trace);
        try {
            // TODO validate request body
            LOGGER.info("[" + trace + "] insert a new document in entity: " + document.getEntity().getName() + " and sector: " + document.getSector().getName());
            // TODO validate user
            // TODO call service to insert register
            return new ResponseEntity<RestResponse>(ResponseBuilder.init()
                    .addRequestCode(ApiUtils.RESPONSE_OK_CODE)
                    .addRequestMethod(http.getMethod())
                    .addRequestUri(http.getRequestURI())
                    .addTimestamp(TimeGenerator.generateStringDateTime())
                    .addTraceRequest(trace)
                    .addData(document)
                    .build()
                    , HttpStatus.OK);
        } catch (CommonHttpException exception) {
            LOGGER.info("[" + trace + "] error to insert new document");
            Error error = new Error(exception.getMessage(), exception);
            return new ResponseEntity<RestResponse>(ResponseBuilder.init()
                    .addRequestCode(exception.getCode())
                    .addRequestMethod(http.getMethod())
                    .addRequestUri(http.getRequestURI())
                    .addTimestamp(TimeGenerator.generateStringDateTime())
                    .addError(error)
                    .build()
                    , exception.getStatus());
        } catch (Exception exception) {
            LOGGER.info("[" + trace + "] error to insert new document");
            Error error = new Error(exception.getMessage(), exception);
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
