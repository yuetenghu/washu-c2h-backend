package com.yuetenghu.washuc2hbackend;

public abstract class Account {

    private final int id;
    private final String surname;
    private final String givenName;

    public Account(int id, String surname, String givenName) {
        this.id = id;
        this.surname = surname;
        this.givenName = givenName;
    }

    public String getSurname() {
        return this.surname;
    };

    public String getGivenName() {
        return this.givenName;
    }

    public int getId() {
        return this.id;
    }
}
