package com.tinqin.core.processor;

import com.tinqin.api.base.Error;
import com.tinqin.api.error.OperationFailedError;
import com.tinqin.api.error.WrongSortInputError;
import com.tinqin.api.model.book.BookResponse;
import com.tinqin.api.model.booksbyprice.BooksByPriceRequest;
import com.tinqin.api.model.booksbyprice.BooksByPriceResponse;
import com.tinqin.api.operation.BookProcessor;
import com.tinqin.api.operation.OrderBooksByPriceProcessor;
import com.tinqin.core.exception.WrongInputException;
import com.tinqin.domain.data.entity.Book;
import com.tinqin.domain.data.repository.BookRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderBooksByPriceProcessorCore implements OrderBooksByPriceProcessor {

    private final BookRepository bookRepository;
    private final BookProcessor bookProcessor;
    private final ConversionService conversionService;

    public OrderBooksByPriceProcessorCore(BookRepository bookRepository, BookProcessor bookProcessor, ConversionService conversionService) {
        this.bookRepository = bookRepository;
        this.bookProcessor = bookProcessor;
        this.conversionService = conversionService;
    }

    @Override
    public Either<Error, BooksByPriceResponse> process(BooksByPriceRequest input) {
        return Try.of(() -> {
            final String sortBY = input.getSortBy();
            final List<Book> allBooks = bookRepository.findAll();

            if (Objects.equals(sortBY, "descending")){

                List<BookResponse> sortedBooks = allBooks.stream()
                        .sorted(Comparator.comparing(Book::getPrice).reversed())
                        .map(book -> {
                            return conversionService.convert(book, BookResponse.class);
                        }).collect(Collectors.toList());

                return BooksByPriceResponse.builder()
                        .books(sortedBooks)
                        .build();
            }
            if (Objects.equals(sortBY, "ascending")){
                List<BookResponse> sortedBooks = allBooks.stream()
                        .sorted(Comparator.comparing(Book::getPrice))
                        .map(book -> {
                            return conversionService.convert(book, BookResponse.class);
                        }).collect(Collectors.toList());

                return BooksByPriceResponse.builder()
                        .books(sortedBooks)
                        .build();
            }
            throw new WrongInputException();

        }).toEither()
                .mapLeft(throwable -> {
                    if (throwable instanceof WrongInputException){
                        return new WrongSortInputError();
                    }
                    return new OperationFailedError();
                });
    }
}
