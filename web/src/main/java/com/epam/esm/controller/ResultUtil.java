package com.epam.esm.controller;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ResultUtil {
    private String errorMsg;
    private String debugMsg;
    private HttpStatus status;
    private List<String> errors;

    public ResultUtil(String errorMsg, String debugMsh) {
        this.errorMsg = errorMsg;
        this.debugMsg = debugMsh;
    }

    public ResultUtil(String errorMsg, List<String> errors) {
        this.errorMsg = errorMsg;
        this.errors = errors;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getDebugMsg() {
        return debugMsg;
    }

    public void setDebugMsg(String debugMsg) {
        this.debugMsg = debugMsg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
