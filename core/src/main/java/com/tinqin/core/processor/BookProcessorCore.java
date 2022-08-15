package com.tinqin.core.processor;

import com.tinqin.api.base.Error;
import com.tinqin.api.error.BookNotFoundError;
import com.tinqin.api.error.OperationFailedError;
import com.tinqin.api.model.book.BookRequest;
import com.tinqin.api.model.book.BookResponse;
import com.tinqin.api.operation.BookProcessor;
import com.tinqin.core.exception.BookNotFoundException;
import com.tinqin.domain.data.entity.Book;
import com.tinqin.domain.data.repository.BookRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class BookProcessorCore implements BookProcessor {

    private final ConversionService conversionService;
    private final BookRepository bookRepository;

    public BookProcessorCore(ConversionService conversionService, BookRepository bookRepository) {
        this.conversionService = conversionService;
        this.bookRepository = bookRepository;
    }

    @Override
    public Either<Error, BookResponse> process(final BookRequest input) {
        return Try.of(() -> {
            final Book book = bookRepository.findById(input.getBookId()).orElseThrow(BookNotFoundException::new);
            return conversionService.convert(book, BookResponse.class);
        }).toEither()
                .mapLeft(throwable -> {
                    if (throwable instanceof BookNotFoundException)
                    {
                        return new BookNotFoundError();
                    }
                    return new OperationFailedError();
                });
    }
}
