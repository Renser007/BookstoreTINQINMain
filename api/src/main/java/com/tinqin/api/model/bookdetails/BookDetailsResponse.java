package com.tinqin.api.model.bookdetails;

import com.tinqin.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class BookDetailsResponse implements OperationResult {

    private String bookName;
    private String series;
    private String price;
    private String author;
    private String genre;
    private String publisher;
    private String cover;
    private String rating;
    private String dateOfPublishing;
    private String numberOfPages;
    private String resume;

}
