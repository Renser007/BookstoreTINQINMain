package com.tinqin.api.model.bookbygenre;

import com.tinqin.api.base.OperationResult;
import lombok.*;

import java.util.List;


@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BooksGenreResponse implements OperationResult {
    private List<BookModel> booksByGenre;
}
