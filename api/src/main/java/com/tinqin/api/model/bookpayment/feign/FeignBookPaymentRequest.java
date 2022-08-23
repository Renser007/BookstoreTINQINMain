package com.tinqin.api.model.bookpayment.feign;

import lombok.*;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class FeignBookPaymentRequest {

    private Double cost;

    private String label;

    private String email;

}
