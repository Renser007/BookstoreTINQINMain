package com.tinqin.api.model.booksbyprice;

import com.tinqin.api.base.OperationResult;
import com.tinqin.api.model.book.BookResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class BooksByPriceResponse implements OperationResult {

    private List<BookResponse> books;

}
