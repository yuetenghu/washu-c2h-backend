package com.yuetenghu.washuc2hbackend;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue
    private Integer id;

    private String surname;
    private String givenName;

    protected Account() {

    }

    public Account(String surname, String givenName) {
        this.surname = surname;
        this.givenName = givenName;
    }

    public Integer getId() {return this.id;}
    public String getSurname() {return this.surname;};
    public String getGivenName() {return this.givenName;}
}
