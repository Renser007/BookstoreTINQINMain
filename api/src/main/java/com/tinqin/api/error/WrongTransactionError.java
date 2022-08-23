package com.tinqin.api.error;

import com.tinqin.api.base.Error;
import org.springframework.http.HttpStatus;

public class WrongTransactionError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return "Wrong uuid!";
    }
}
