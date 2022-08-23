package com.tinqin.api.model.bookpayment;

import com.tinqin.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class BookPaymentResponse implements OperationResult {

    private String status;

    private String uuid;

}
