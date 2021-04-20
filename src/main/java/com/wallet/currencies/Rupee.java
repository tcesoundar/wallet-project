package com.wallet.currencies;

import com.wallet.Currency;

public class Rupee implements Currency {

    private final double rupeeValue;

    public Rupee() {
        this.rupeeValue = 0;
    }

    public Rupee(double rupeeValue) {
        this.rupeeValue = rupeeValue;
    }

    @Override
    public double getValue() {
        return rupeeValue;
    }

}
