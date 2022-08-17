package com.tinqin.domain.client;

import com.tinqin.api.model.bookdetails.feign.FeignBookDetailsRequest;
import com.tinqin.api.model.bookdetails.feign.FeignBookDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "books",url = "http://localhost:8081/")
public interface BookDetailsClient {

    @PostMapping("/getBookDetails")
    FeignBookDetailsResponse getBookDetails(@RequestBody FeignBookDetailsRequest feignBookDetailsRequest);

}
