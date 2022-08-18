package com.tinqin.api.error;

import com.tinqin.api.base.Error;
import org.springframework.http.HttpStatus;

public class WrongSortInputError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_ACCEPTABLE;
    }

    @Override
    public String getMessage() {
        return "Enter either ascending or descending!";
    }
}
