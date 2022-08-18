package com.tinqin.api.model.booksbyprice;

import com.tinqin.api.base.OperationInput;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BooksByPriceRequest implements OperationInput {

    private String sortBy;

}
