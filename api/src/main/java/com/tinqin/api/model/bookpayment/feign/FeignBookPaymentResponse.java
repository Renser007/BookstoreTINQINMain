package com.tinqin.api.model.bookpayment.feign;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class FeignBookPaymentResponse {

    private String uuid;

    private String status;

}
