package com.tinqin.domain.data.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Transaction> transactions=new HashSet<>();

    public User(String email, Set<Transaction> transactions) {
        this.email = email;
        this.transactions = transactions;
    }

    public User() {
    }
}
