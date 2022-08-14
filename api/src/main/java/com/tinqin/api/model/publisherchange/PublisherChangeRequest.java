package com.tinqin.api.model.publisherchange;

import com.tinqin.api.base.OperationInput;
import lombok.*;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class PublisherChangeRequest implements OperationInput {

    private Long bookId;
    private Long oldPublisherId;
    private Long newPublisherId;

}
