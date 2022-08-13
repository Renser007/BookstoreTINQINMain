package com.tinqin.domain.data.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "books")
@Getter
@Setter(AccessLevel.PRIVATE)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String bookName;

    @ManyToOne
    @JoinColumn(name="seriesId")
    private Series series;

    @ManyToOne
    @JoinColumn(name="authorId")
    private Author author;

    @ManyToOne
    @JoinColumn(name="genreId")
    private Genre genre;

    private Double price;

    @ManyToOne
    @JoinColumn(name="publisherId")
    private Publisher publisher;

    public Book(String bookName, Series series, Author author, Genre genre, Double price, Publisher publisher) {
        this.bookName = bookName;
        this.series = series;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.publisher = publisher;
    }

    public Book() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId) && Objects.equals(bookName, book.bookName) && Objects.equals(series, book.series) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre) && Objects.equals(price, book.price) && Objects.equals(publisher, book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookName, series, author, genre, price, publisher);
    }
}
