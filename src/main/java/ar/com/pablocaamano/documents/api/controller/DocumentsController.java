package ar.com.pablocaamano.documents.api.controller;

import ar.com.pablocaamano.commons.builder.ResponseBuilder;
import ar.com.pablocaamano.commons.exception.CommonException;
import ar.com.pablocaamano.commons.rest.Error;
import ar.com.pablocaamano.commons.rest.RestResponse;
import ar.com.pablocaamano.commons.util.IdGenerator;
import ar.com.pablocaamano.commons.util.TimeGenerator;
import ar.com.pablocaamano.documents.api.model.DTO.DocumentDTO;
import ar.com.pablocaamano.documents.api.model.Document;
import ar.com.pablocaamano.documents.api.service.DocumentsService;
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
            @ApiResponse(code = 404, message = "Not found document"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RestResponse> getDecumentById(
            @PathVariable int id,
            HttpServletRequest http) {
        String trace = IdGenerator.getStringIdRadom();
        LOGGER.info("[" + trace + "] get document by id: " + id);
        try {
            Document result = this.service.findDocument(trace,id);
            return new ResponseEntity<RestResponse>(ResponseBuilder.init()
                        .addRequestCode("OK")
                        .addRequestMethod(http.getMethod())
                        .addRequestUri(http.getRequestURI())
                        .addTimestamp(TimeGenerator.generateStringDateTime())
                        .addTraceRequest(trace)
                        .addData(new DocumentDTO())
                        .build()
                    , HttpStatus.OK);
        } catch (CommonException exception) {
            LOGGER.info("[" + trace + "] error getting document by id: " + id);
            Error error = new Error(exception.getMessage(), exception);
            return new ResponseEntity<RestResponse>(ResponseBuilder.init()
                    .addRequestCode("ERROR")
                    .addRequestMethod(http.getMethod())
                    .addRequestUri(http.getRequestURI())
                    .addTimestamp(TimeGenerator.generateStringDateTime())
                    .addError(error)
                    .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
