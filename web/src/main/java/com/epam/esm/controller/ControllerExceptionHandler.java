package com.epam.esm.controller;

import com.epam.esm.service.ServiceException;
import com.sun.corba.se.impl.io.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ControllerExceptionHandler class
 * used to handle some exceptions from controller
 */
@EnableWebMvc
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * ExceptionHandler for ServiceException
     *
     * @param ex exception in service layer
     * @return a Response Entity with exception detail
     */
    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<Object> handleEntityNotFoundEx(ServiceException ex) {
        ResultUtil resultUtil = new ResultUtil("Something went wrong.", ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(resultUtil, HttpStatus.BAD_REQUEST);
    }

    /**
     * ExceptionHandler for TypeMismatchException
     *
     * @param exception exception type mismatch
     * @return a Response Entity with exception detail
     */
    @ExceptionHandler(TypeMismatchException.class)
    protected ResponseEntity<Object> handleTypeMismatchException(ConstraintViolationException exception) {
        ResultUtil resultUtil = new ResultUtil("TypeMismatchException", exception.getCause().toString());
        return new ResponseEntity<>(resultUtil, HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return a Response Entity with HttpMessageNotReadableException detail
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResultUtil resultUtil = new ResultUtil("Malformed JSON Request", ex.getMessage());
        return new ResponseEntity<>(resultUtil, status);
    }

    /**
     * @param ex      MethodArgumentNotValidException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return a Response Entity with MethodArgumentNotValidException detail
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ResultUtil resultUtil;
        resultUtil = new ResultUtil("Method Argument Not Valid", errors, HttpStatus.BAD_REQUEST);
        if (errors.size() == 0) {
            resultUtil = new ResultUtil("Method Argument Not Valid", HttpStatus.BAD_REQUEST.toString());
        }
        return new ResponseEntity<>(resultUtil, status);
    }

    /**
     * @param ex      NoHandlerFoundException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return a Response Entity with NoHandlerFoundException detail
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("path", request.getContextPath());
        responseBody.put("message", "The URL you have reached is not in service at this time (404).");
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}
