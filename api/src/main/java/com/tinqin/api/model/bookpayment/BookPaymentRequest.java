package com.tinqin.api.model.bookpayment;

import com.tinqin.api.base.OperationInput;
import lombok.*;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BookPaymentRequest implements OperationInput {

    private String bookName;

    private String email;

}
