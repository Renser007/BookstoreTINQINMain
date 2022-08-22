package com.tinqin.core.processor;

import com.tinqin.api.base.Error;
import com.tinqin.api.error.BookNotFoundError;
import com.tinqin.api.error.PublisherChangeNotPossibleError;
import com.tinqin.api.error.PublisherNotFoundError;
import com.tinqin.api.model.book.BookRequest;
import com.tinqin.api.model.book.BookResponse;
import com.tinqin.api.model.publisher.PublisherRequest;
import com.tinqin.api.model.publisher.PublisherResponse;
import com.tinqin.api.model.publisherchange.PublisherChangeRequest;
import com.tinqin.api.model.publisherchange.PublisherChangeResponse;
import com.tinqin.api.operation.BookProcessor;
import com.tinqin.api.operation.PublisherChangeProcessor;
import com.tinqin.api.operation.PublisherProcessor;
import com.tinqin.core.exception.BookNotFoundException;
import com.tinqin.core.exception.PublisherChangeNotPossibleException;
import com.tinqin.core.exception.PublisherNotFoundException;
import com.tinqin.domain.data.entity.Publisher;
import com.tinqin.domain.data.repository.BookRepository;
import com.tinqin.domain.data.repository.PublisherRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PublisherChangeProcessorCore implements PublisherChangeProcessor {

    private final BookProcessor bookProcessor;
    private  final PublisherProcessor publisherProcessor;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public PublisherChangeProcessorCore(BookProcessor bookProcessor, PublisherProcessor publisherProcessor, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.bookProcessor = bookProcessor;
        this.publisherProcessor = publisherProcessor;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }


    @Override
    public Either<Error, PublisherChangeResponse> process(final PublisherChangeRequest input) {
        return Try.of(() -> {
            final BookResponse bookResponse = bookProcessor.process(new BookRequest(input.getBookId()))
                    .getOrElseThrow(PublisherChangeNotPossibleException::new);
            final PublisherResponse oldPublisherResponse = publisherProcessor.process(new PublisherRequest(input.getOldPublisherId()))
                    .getOrElseThrow(PublisherChangeNotPossibleException::new);
            final PublisherResponse newPublisherResponse = publisherProcessor.process(new PublisherRequest(input.getNewPublisherId()))
                    .getOrElseThrow(PublisherChangeNotPossibleException::new);

            return Stream.of(bookRepository.findBookByBookName(bookResponse.getBookName())
                            .orElseThrow(BookNotFoundException::new))
                    .map(b -> {
                        Publisher publisher =publisherRepository.findPublisherByPublisherName(newPublisherResponse.getPublisherName())
                                .orElseThrow(PublisherNotFoundException::new);
                        b.setPublisher(publisher);
                        bookRepository.save(b);
                        return publisher;
                    })
                    .map(p ->
                        PublisherChangeResponse.builder()
                                .bookName(bookResponse.getBookName())
                                .newPublisher(p.getPublisherName())
                                .oldPublisher(oldPublisherResponse.getPublisherName())
                                .build())
                    .findFirst()
                    .orElseThrow(PublisherChangeNotPossibleException::new);
        }).toEither()
                .mapLeft(throwable -> {
                    if (throwable instanceof BookNotFoundException){
                        return new BookNotFoundError();
                    }
                    if (throwable instanceof PublisherNotFoundException){
                        return new PublisherNotFoundError();
                    }
                    if (throwable instanceof PublisherChangeNotPossibleException){
                        return  new PublisherChangeNotPossibleError();
                    }
                    return new PublisherChangeNotPossibleError();
                });
    }
}
