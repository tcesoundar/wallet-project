package com.wallet;

public interface Currency {

    double getValue();

    default String getType(Currency currency) {
        return currency.getClass().getSimpleName();
    }

}
