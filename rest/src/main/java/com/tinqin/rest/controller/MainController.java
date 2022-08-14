package com.tinqin.rest.controller;

import com.tinqin.api.base.Error;
import com.tinqin.api.model.bookbygenre.BooksGenreRequest;
import com.tinqin.api.model.bookbygenre.BooksGenreResponse;
import com.tinqin.api.model.publisherchange.PublisherChangeRequest;
import com.tinqin.api.model.publisherchange.PublisherChangeResponse;
import com.tinqin.api.operation.BooksByGenreProcessor;
import com.tinqin.api.operation.PublisherChangeProcessor;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final BooksByGenreProcessor booksByGenreProcessor;
    private final PublisherChangeProcessor publisherChangeProcessor;

    public MainController(BooksByGenreProcessor booksByGenreProcessor, PublisherChangeProcessor publisherChangeProcessor) {
        this.booksByGenreProcessor = booksByGenreProcessor;
        this.publisherChangeProcessor = publisherChangeProcessor;
    }

    @PostMapping("/booksByGenre")

    public ResponseEntity<?> showBooksByGenre(@RequestBody final BooksGenreRequest booksGenreRequest){
        Either<Error, BooksGenreResponse> response=booksByGenreProcessor.process(booksGenreRequest);
        if(response.isLeft()){
            return ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @PostMapping("/changePublisher")

    public ResponseEntity<?> changePublisher(@RequestBody final PublisherChangeRequest publisherChangeRequest){
        Either<Error, PublisherChangeResponse> response=publisherChangeProcessor.process(publisherChangeRequest);
        if(response.isLeft()){
            return ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

}
