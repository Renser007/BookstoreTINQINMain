package com.tinqin.core.processor;

import com.tinqin.api.base.Error;
import com.tinqin.api.error.OperationFailedError;
import com.tinqin.api.error.PublisherNotFoundError;
import com.tinqin.api.model.publisher.PublisherRequest;
import com.tinqin.api.model.publisher.PublisherResponse;
import com.tinqin.api.operation.PublisherProcessor;
import com.tinqin.core.exception.PublisherNotFoundException;
import com.tinqin.domain.data.entity.Publisher;
import com.tinqin.domain.data.repository.PublisherRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class PublisherProcessorCore implements PublisherProcessor {

    private final ConversionService conversionService;
    private final PublisherRepository publisherRepository;

    public PublisherProcessorCore(ConversionService conversionService, PublisherRepository publisherRepository) {
        this.conversionService = conversionService;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Either<Error, PublisherResponse> process(final PublisherRequest input) {
        return Try.of(() -> {
            final Publisher publisher = publisherRepository.findById(input.getPublisherId())
                    .orElseThrow(PublisherNotFoundException::new);
            return conversionService.convert(publisher, PublisherResponse.class);
        }).toEither()
                .mapLeft(throwable -> {
                    if (throwable instanceof PublisherNotFoundException){
                        return new PublisherNotFoundError();
                    }
                    return new OperationFailedError();
                });
    }
}
