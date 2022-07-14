package com.example.bicepgamers.database.hsqldb;

public class PersistenceException extends RuntimeException{
    public PersistenceException(final Exception cause) {
        super(cause);
    }
}
