package com.wallet.exception;

public class NegativeDebitValueException extends Exception {

    public NegativeDebitValueException(String message) {
        super(message);
    }

}
