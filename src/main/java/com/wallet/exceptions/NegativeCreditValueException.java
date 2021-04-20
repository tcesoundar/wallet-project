package com.wallet.exceptions;

public class NegativeCreditValueException extends Exception {

    public NegativeCreditValueException(String message) {
        super(message);
    }

}
