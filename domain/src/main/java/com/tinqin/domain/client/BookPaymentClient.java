package com.tinqin.domain.client;

import com.tinqin.api.model.bookpayment.feign.FeignBookPaymentRequest;
import com.tinqin.api.model.bookpayment.feign.FeignBookPaymentResponse;
import com.tinqin.api.model.checktransaction.feign.FeignCheckPaymentRequest;
import com.tinqin.api.model.checktransaction.feign.FeignCheckPaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "payments",url = "http://localhost:8082/")
public interface BookPaymentClient {

    @PostMapping("/openPayment")
    FeignBookPaymentResponse openPayment(@RequestBody FeignBookPaymentRequest feignBookPaymentRequest);


    @PostMapping("/checkPayment")
    FeignCheckPaymentResponse checkPayment(@RequestBody FeignCheckPaymentRequest feignCheckPaymentRequest);
}
