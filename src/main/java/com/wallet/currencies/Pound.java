package com.wallet.currencies;

import com.wallet.Currency;

public class Pound implements Currency {

    double poundValue;

    public Pound() {
        this.poundValue = 0;
    }

    public Pound(double poundValue) {
        this.poundValue = poundValue;
    }

    @Override
    public double getValue() {
        return poundValue;
    }

}
