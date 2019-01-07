package com.dev.training.uownme.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
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
    private Boolean enabled = true;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "debtor")
    private Collection<Balance> balanceSet;

    public User(String userName, String password, String firstName, String surname, String mail) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.mail = mail;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

}
