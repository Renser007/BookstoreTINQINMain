package com.tinqin.api.model.bookdetails;

import com.tinqin.api.base.OperationInput;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsRequest implements OperationInput {

    private String bookName;

}
