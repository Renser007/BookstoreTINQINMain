package com.tinqin.core.processor;

import com.tinqin.api.base.Error;
import com.tinqin.api.error.BookNotFoundError;
import com.tinqin.api.error.EmailNotFoundError;
import com.tinqin.api.error.OperationFailedError;
import com.tinqin.api.model.bookpayment.BookPaymentRequest;
import com.tinqin.api.model.bookpayment.BookPaymentResponse;
import com.tinqin.api.model.bookpayment.feign.FeignBookPaymentRequest;
import com.tinqin.api.model.bookpayment.feign.FeignBookPaymentResponse;
import com.tinqin.api.operation.BookPaymentProcessor;
import com.tinqin.core.exception.BookNotFoundException;
import com.tinqin.core.exception.EmailNotFoundException;
import com.tinqin.domain.client.BookPaymentClient;
import com.tinqin.domain.data.entity.Book;
import com.tinqin.domain.data.entity.Transaction;
import com.tinqin.domain.data.entity.User;
import com.tinqin.domain.data.repository.BookRepository;
import com.tinqin.domain.data.repository.TransactionRepository;
import com.tinqin.domain.data.repository.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class BookPaymentProcessorCore implements BookPaymentProcessor {

    private final BookRepository bookRepository;
    private final BookPaymentClient bookPaymentClient;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;



    public BookPaymentProcessorCore(BookRepository bookRepository, BookPaymentClient bookPaymentClient, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.bookRepository = bookRepository;
        this.bookPaymentClient = bookPaymentClient;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Either<Error, BookPaymentResponse> process(BookPaymentRequest input) {
        return Try.of(() -> {

            final Book bookToBuy = bookRepository.findBookByBookName(input.getBookName())
                    .orElseThrow(BookNotFoundException::new);

            final User user = userRepository.findByEmail(input.getEmail()).orElseThrow(EmailNotFoundException::new);

            final FeignBookPaymentResponse feignBookPaymentResponse = bookPaymentClient.openPayment(FeignBookPaymentRequest.builder()
                            .label(bookToBuy.getBookName())
                            .cost(bookToBuy.getPrice())
                            .email(user.getEmail())
                    .build());

            final Transaction transaction = new Transaction(feignBookPaymentResponse.getUuid(), feignBookPaymentResponse.getStatus(), user);

            transactionRepository.save(transaction);


            return BookPaymentResponse.builder()
                    .status(feignBookPaymentResponse.getStatus())
                    .uuid(feignBookPaymentResponse.getUuid())
                    .build();

        }).toEither()
                .mapLeft(throwable -> {
                    if (throwable instanceof BookNotFoundException){
                        return new BookNotFoundError();
                    }
                    if (throwable instanceof EmailNotFoundException){
                        return new EmailNotFoundError();
                    }
                    return new OperationFailedError();
                });
    }
}
