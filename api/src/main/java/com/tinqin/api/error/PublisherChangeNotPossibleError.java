package com.tinqin.api.error;

import com.tinqin.api.base.Error;
import org.springframework.http.HttpStatus;

public class PublisherChangeNotPossibleError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_ACCEPTABLE;
    }

    @Override
    public String getMessage() {
        return "Publisher change failed!";
    }
}
