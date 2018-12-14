package com.dev.training.uownme.common.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "balances")
public class Balance {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User debtor;

    @ManyToOne
    private User owner;

    private Float amount;

}
