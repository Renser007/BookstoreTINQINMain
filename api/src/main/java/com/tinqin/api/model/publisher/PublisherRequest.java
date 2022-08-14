package com.tinqin.api.model.publisher;


import com.tinqin.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublisherRequest implements OperationInput {

    private Long publisherId;

}
