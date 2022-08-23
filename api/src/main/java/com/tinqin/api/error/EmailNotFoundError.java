package com.tinqin.api.error;

import com.tinqin.api.base.Error;
import org.springframework.http.HttpStatus;

public class EmailNotFoundError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "Email not found!";
    }
}
