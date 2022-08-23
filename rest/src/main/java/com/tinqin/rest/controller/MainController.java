package com.tinqin.rest.controller;

import com.tinqin.api.base.Error;
import com.tinqin.api.model.bookbygenre.BooksGenreRequest;
import com.tinqin.api.model.bookbygenre.BooksGenreResponse;
import com.tinqin.api.model.bookdetails.BookDetailsRequest;
import com.tinqin.api.model.bookdetails.BookDetailsResponse;
import com.tinqin.api.model.bookpayment.BookPaymentRequest;
import com.tinqin.api.model.bookpayment.BookPaymentResponse;
import com.tinqin.api.model.booksbyprice.BooksByPriceRequest;
import com.tinqin.api.model.booksbyprice.BooksByPriceResponse;
import com.tinqin.api.model.checktransaction.CheckTransactionRequest;
import com.tinqin.api.model.checktransaction.CheckTransactionResponse;
import com.tinqin.api.model.publisherchange.PublisherChangeRequest;
import com.tinqin.api.model.publisherchange.PublisherChangeResponse;
import com.tinqin.api.operation.*;
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
    private final BookDetailsProcessor bookDetailsProcessor;
    private final OrderBooksByPriceProcessor orderBooksByPriceProcessor;
    private final BookPaymentProcessor bookPaymentProcessor;
    private final CheckTransactionProcessor checkTransactionProcessor;

    public MainController(BooksByGenreProcessor booksByGenreProcessor, PublisherChangeProcessor publisherChangeProcessor, BookDetailsProcessor bookDetailsProcessor, OrderBooksByPriceProcessor orderBooksByPriceProcessor, BookPaymentProcessor bookPaymentProcessor, CheckTransactionProcessor checkTransactionProcessor) {
        this.booksByGenreProcessor = booksByGenreProcessor;
        this.publisherChangeProcessor = publisherChangeProcessor;
        this.bookDetailsProcessor = bookDetailsProcessor;
        this.orderBooksByPriceProcessor = orderBooksByPriceProcessor;
        this.bookPaymentProcessor = bookPaymentProcessor;
        this.checkTransactionProcessor = checkTransactionProcessor;
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

    @PostMapping("/getBookDetails")

    public ResponseEntity<?> getBookDetails(@RequestBody final BookDetailsRequest bookDetailsRequest){
        Either<Error, BookDetailsResponse> response=bookDetailsProcessor.process(bookDetailsRequest);
        if(response.isLeft()){
            return ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @PostMapping("/sortAllBooks")

    public ResponseEntity<?> sortAllBooks(@RequestBody final BooksByPriceRequest bookDetailsRequest){
        Either<Error, BooksByPriceResponse> response=orderBooksByPriceProcessor.process(bookDetailsRequest);
        if(response.isLeft()){
            return ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @PostMapping("/bookPayment")

    public ResponseEntity<?> bookPayment(@RequestBody final BookPaymentRequest bookPaymentRequest){
        Either<Error, BookPaymentResponse> response=bookPaymentProcessor.process(bookPaymentRequest);
        if(response.isLeft()){
            return ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @PostMapping("/checkTransaction")

    public ResponseEntity<?> checkTransaction(@RequestBody final CheckTransactionRequest checkTransactionRequest){
        Either<Error, CheckTransactionResponse> response=checkTransactionProcessor.process(checkTransactionRequest);
        if(response.isLeft()){
            return ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

}
