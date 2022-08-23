package com.tinqin.api.error;

import com.tinqin.api.base.Error;
import org.springframework.http.HttpStatus;

public class TransactionNotFoundError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "Transaction not found!";
    }
}
