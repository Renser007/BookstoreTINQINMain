package com.tinqin.api.model.bookdetails.feign;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class FeignBookDetailsResponse {

    private String dateOfPublishing;
    private String numberOfPages;
    private String resume;
    private String rating;
    private String cover;

}
