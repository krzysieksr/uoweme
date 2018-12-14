package com.dev.training.uownme.common.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String surname;
    private String mail;
    private String accountNumber;
    private Date created;
    private Date lastLogin;
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "debtor")
    private Set<Balance> balanceSet = new HashSet<>();


    public User(String userName, String password, String firstName, String surname, String mail) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.mail = mail;
    }
}
