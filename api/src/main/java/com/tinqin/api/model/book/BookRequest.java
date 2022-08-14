package com.tinqin.api.model.book;

import com.tinqin.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest implements OperationInput {

    private Long bookId;

}
