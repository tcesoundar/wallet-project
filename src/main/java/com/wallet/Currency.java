package com.wallet;

import com.wallet.exceptions.CurrencyTypeNotFoundException;

public abstract class Currency {

    private double value;
    private final CurrencyType currencyType;
    private final CurrencyConverter currencyConverter;

    public Currency(CurrencyType currencyType) {
        this.value = 0;
        this.currencyType = currencyType;
        this.currencyConverter = new CurrencyConverter();
    }

    public Currency(double value, CurrencyType currencyType) {
        this.value = value;
        this.currencyType = currencyType;
        this.currencyConverter = new CurrencyConverter();
    }

    public double getValue(){
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public Currency convert(Currency currency, Currency preferredCurrency) throws CurrencyTypeNotFoundException {
        return currencyConverter.convert(currency, preferredCurrency);
    }

}
