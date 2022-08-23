package com.tinqin.api.model.checktransaction;

import com.tinqin.api.base.OperationInput;
import lombok.*;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CheckTransactionRequest implements OperationInput {

    private String email;

    private String uuid;

}
