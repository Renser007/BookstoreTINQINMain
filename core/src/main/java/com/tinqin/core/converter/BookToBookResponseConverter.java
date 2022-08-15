package com.tinqin.core.converter;

import com.tinqin.api.model.book.BookResponse;
import com.tinqin.domain.data.entity.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookToBookResponseConverter implements Converter<Book, BookResponse> {

    @Override
    public BookResponse convert(Book book) {
        return BookResponse.builder()
                .bookName(book.getBookName())
                .author(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName())
                .publisher(book.getPublisher().getPublisherName())
                .build();
    }
}
