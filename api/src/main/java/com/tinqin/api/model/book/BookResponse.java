package com.tinqin.api.model.book;

import com.tinqin.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class BookResponse implements OperationResult {

    private String bookName;
    private String author;
    private String publisher;

}
