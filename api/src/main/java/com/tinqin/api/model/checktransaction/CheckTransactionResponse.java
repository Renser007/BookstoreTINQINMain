package com.tinqin.api.model.checktransaction;

import com.tinqin.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class CheckTransactionResponse implements OperationResult {

    private String status;

}
