package com.tinqin.api.error;

import com.tinqin.api.base.Error;
import org.springframework.http.HttpStatus;

public class OperationUnavailableError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.SERVICE_UNAVAILABLE;
    }

    @Override
    public String getMessage() {
        return "Operation is unavailable!";
    }
}
