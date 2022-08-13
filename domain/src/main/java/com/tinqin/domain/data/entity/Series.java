package com.tinqin.domain.data.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "series")
@Getter
@Setter
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seriesId;

    private String seriesName;

    private Integer numberOfBooks;

    @OneToMany(mappedBy = "series")
    private Set<Book> books = new HashSet<>();

    public Series(String seriesName, Integer numberOfBooks) {
        this.seriesName = seriesName;
        this.numberOfBooks = numberOfBooks;
    }

    public Series() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return Objects.equals(seriesId, series.seriesId) && Objects.equals(seriesName, series.seriesName) && Objects.equals(numberOfBooks, series.numberOfBooks) && Objects.equals(books, series.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seriesId, seriesName, numberOfBooks, books);
    }
}
