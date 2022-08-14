package com.tinqin.api.model.publisher;


import com.tinqin.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class PublisherResponse implements OperationResult {

    private String publisherName;

}
