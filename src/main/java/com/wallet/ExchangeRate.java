package com.wallet;

public class ExchangeRate {

    private final CurrencyType toCurrencyType;
    private final double rate;

    public ExchangeRate(CurrencyType toCurrencyType, double rate) {

        this.toCurrencyType = toCurrencyType;
        this.rate = rate;

    }

    public CurrencyType getToCurrencyType() {

        return toCurrencyType;

    }

    public double getRate() {

        return rate;

    }

}