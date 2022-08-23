package com.tinqin.domain.data.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class Transaction {

    @Id
    private String uuid;

    private String status;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public Transaction(String uuid, String status, User user) {
        this.uuid = uuid;
        this.status = status;
        this.user = user;
    }

    public Transaction() {
    }
}
