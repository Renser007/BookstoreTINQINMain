package com.tinqin.api.model.publisherchange;

import com.tinqin.api.base.OperationResult;
import lombok.*;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class PublisherChangeResponse implements OperationResult {

    private String bookName;
    private String oldPublisher;
    private String newPublisher;

}
