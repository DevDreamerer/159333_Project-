package com.nzHub.exception;

import com.nzHub.result.ResponseEnum;

public class nzHubException extends RuntimeException {
    private ResponseEnum responseEnum;
    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }
    public void setResponseEnum(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }
    public nzHubException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;
    }
}
