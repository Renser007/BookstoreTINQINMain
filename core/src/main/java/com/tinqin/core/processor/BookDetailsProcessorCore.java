package com.tinqin.core.processor;

import com.tinqin.api.base.Error;
import com.tinqin.api.error.BookNotFoundError;
import com.tinqin.api.error.OperationFailedError;
import com.tinqin.api.error.OperationUnavailableError;
import com.tinqin.api.model.book.BookRequest;
import com.tinqin.api.model.book.BookResponse;
import com.tinqin.api.model.bookdetails.BookDetailsRequest;
import com.tinqin.api.model.bookdetails.BookDetailsResponse;
import com.tinqin.api.model.bookdetails.feign.FeignBookDetailsRequest;
import com.tinqin.api.model.bookdetails.feign.FeignBookDetailsResponse;
import com.tinqin.api.operation.BookDetailsProcessor;
import com.tinqin.api.operation.BookProcessor;
import com.tinqin.core.exception.BookNotFoundException;
import com.tinqin.domain.client.BookDetailsClient;
import com.tinqin.domain.data.entity.Book;
import com.tinqin.domain.data.repository.BookRepository;
import feign.FeignException;
import feign.RetryableException;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class BookDetailsProcessorCore implements BookDetailsProcessor {

    private final BookRepository bookRepository;
    private final BookProcessor bookProcessor;
    private final BookDetailsClient bookDetailsClient;

    public BookDetailsProcessorCore(BookRepository bookRepository, BookProcessor bookProcessor, BookDetailsClient bookDetailsClient) {
        this.bookRepository = bookRepository;
        this.bookProcessor = bookProcessor;
        this.bookDetailsClient = bookDetailsClient;
    }

    @Override
    public Either<Error, BookDetailsResponse> process(final BookDetailsRequest input) {
        return Try.of(() -> {

            final Book book = bookRepository.findBookByBookName(input.getBookName()).orElseThrow(BookNotFoundException::new);

            final BookResponse bookResponse = bookProcessor.process(new BookRequest(book.getBookId())).getOrElseThrow(BookNotFoundException::new);

            final FeignBookDetailsResponse feignBookDetailsResponse = bookDetailsClient.getBookDetails(FeignBookDetailsRequest.builder()
                    .bookName(book.getBookName())
                    .build());

            return BookDetailsResponse.builder()
                    .bookName(bookResponse.getBookName())
                    .series(bookResponse.getSeries())
                    .price(bookResponse.getPrice())
                    .author(bookResponse.getAuthor())
                    .genre(bookResponse.getGenre())
                    .publisher(bookResponse.getPublisher())
                    .cover(feignBookDetailsResponse.getCover())
                    .rating(feignBookDetailsResponse.getRating())
                    .dateOfPublishing(feignBookDetailsResponse.getDateOfPublishing())
                    .numberOfPages(feignBookDetailsResponse.getNumberOfPages())
                    .resume(feignBookDetailsResponse.getResume())
                    .build();
        }).toEither()
                .mapLeft(throwable -> {
                    if (throwable instanceof BookNotFoundException){
                        return new BookNotFoundError();
                    }
                    if(throwable instanceof RetryableException) {
                        return new OperationUnavailableError();
                    }
                    if(throwable instanceof FeignException){
                        return new OperationUnavailableError();
                    }
                    return new OperationFailedError();
                });
    }
}
