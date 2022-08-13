package com.tinqin.core.converter;

import com.tinqin.api.model.bookbygenre.BookModel;
import com.tinqin.domain.data.entity.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookToBookModelConverter implements Converter<Book, BookModel> {

    @Override
    public BookModel convert(Book book) {
        return BookModel.builder()
                .bookName(book.getBookName())
                .author(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName())
                .series(book.getSeries().getSeriesName())
                .price(String.valueOf(book.getPrice()))
                .build();
    }
}
