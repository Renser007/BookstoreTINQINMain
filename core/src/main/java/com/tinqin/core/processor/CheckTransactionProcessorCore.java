package com.tinqin.core.processor;

import com.tinqin.api.base.Error;
import com.tinqin.api.error.OperationFailedError;
import com.tinqin.api.error.TransactionNotFoundError;
import com.tinqin.api.error.WrongTransactionError;
import com.tinqin.api.model.checktransaction.CheckTransactionRequest;
import com.tinqin.api.model.checktransaction.CheckTransactionResponse;
import com.tinqin.api.model.checktransaction.feign.FeignCheckPaymentRequest;
import com.tinqin.api.model.checktransaction.feign.FeignCheckPaymentResponse;
import com.tinqin.api.operation.CheckTransactionProcessor;
import com.tinqin.core.exception.EmailNotFoundException;
import com.tinqin.core.exception.TransactionNotFoundException;
import com.tinqin.core.exception.WrongInputException;
import com.tinqin.domain.client.BookPaymentClient;
import com.tinqin.domain.data.entity.Transaction;
import com.tinqin.domain.data.entity.User;
import com.tinqin.domain.data.repository.TransactionRepository;
import com.tinqin.domain.data.repository.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CheckTransactionProcessorCore implements CheckTransactionProcessor {

    private final TransactionRepository transactionRepository;
    private final BookPaymentClient bookPaymentClient;
    private final UserRepository userRepository;

    public CheckTransactionProcessorCore(TransactionRepository transactionRepository, BookPaymentClient bookPaymentClient, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.bookPaymentClient = bookPaymentClient;
        this.userRepository = userRepository;
    }

    @Override
    public Either<Error, CheckTransactionResponse> process(CheckTransactionRequest input) {
        return Try.of(() -> {

            final User user = userRepository.findByEmail(input.getEmail()).orElseThrow(EmailNotFoundException::new);

            final Transaction transaction = transactionRepository.findById(input.getUuid())
                    .orElseThrow(TransactionNotFoundException::new);

            if (!Objects.equals(transaction.getUser().getUserId(), user.getUserId())){
                throw new WrongInputException();
            }

            final FeignCheckPaymentResponse feignCheckPaymentResponse = bookPaymentClient.checkPayment(FeignCheckPaymentRequest.builder()
                            .uuid(transaction.getUuid())
                    .build());

            if (!Objects.equals(feignCheckPaymentResponse.getStatus(), transaction.getStatus())){
                transaction.setStatus(feignCheckPaymentResponse.getStatus());
                transactionRepository.save(transaction);
            }

            return CheckTransactionResponse.builder()
                    .status(feignCheckPaymentResponse.getStatus())
                    .build();


        }).toEither()
                .mapLeft(throwable -> {
                    if (throwable instanceof WrongInputException){
                        return new WrongTransactionError();
                    }
                    if (throwable instanceof TransactionNotFoundException){
                        return new TransactionNotFoundError();
                    }
                    return new OperationFailedError();
                });
    }
}
