package com.bridgelabz.addressbook;

public class InvalidException extends  Exception {
    enum ExceptionType {
        SQL_EXCEPTION,DATA_NOT_FOUND,DATA_NOT_UPDATE,DATA_NOT_ADDED,DATA_NOT_ABLE_READ
    }

    ExceptionType type;

    public InvalidException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
