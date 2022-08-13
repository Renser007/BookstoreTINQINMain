package com.tinqin.api.model.bookbygenre;

import com.tinqin.api.base.OperationInput;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BooksGenreRequest implements OperationInput {
    private String genre;
}
