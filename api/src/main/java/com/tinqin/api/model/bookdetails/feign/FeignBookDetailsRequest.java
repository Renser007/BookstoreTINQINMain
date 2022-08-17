package com.tinqin.api.model.bookdetails.feign;

import lombok.*;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class FeignBookDetailsRequest {

    private String bookName;

}
