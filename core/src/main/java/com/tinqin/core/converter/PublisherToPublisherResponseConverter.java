package com.tinqin.core.converter;

import com.tinqin.api.model.publisher.PublisherResponse;
import com.tinqin.domain.data.entity.Publisher;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PublisherToPublisherResponseConverter implements Converter<Publisher, PublisherResponse> {
    @Override
    public PublisherResponse convert(Publisher publisher) {
        return PublisherResponse.builder()
                .publisherName(publisher.getPublisherName())
                .build();
    }
}
