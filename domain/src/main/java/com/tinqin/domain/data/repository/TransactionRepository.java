package com.tinqin.domain.data.repository;

import com.tinqin.domain.data.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
