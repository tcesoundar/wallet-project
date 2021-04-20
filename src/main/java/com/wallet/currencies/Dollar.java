package com.wallet.currencies;

import com.wallet.Currency;

public class Dollar implements Currency {

    private final double dollarValue;

    public Dollar() {
        dollarValue = 0;
    }

    public Dollar(double dollarValue) {
        this.dollarValue = dollarValue;
    }

    @Override
    public double getValue() {
        return dollarValue;
    }

}
