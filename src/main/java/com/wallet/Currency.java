package com.wallet;

import com.wallet.exceptions.CurrencyTypeNotFoundException;

import java.util.List;

public abstract class Currency {

    private double value;
    private final CurrencyType currencyType;

    public Currency(CurrencyType currencyType) {

        this.value = 0;
        this.currencyType = currencyType;

    }

    public Currency(double value, CurrencyType currencyType) {

        this.value = value;
        this.currencyType = currencyType;

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

    public Currency convertTo(Currency toCurrency) throws CurrencyTypeNotFoundException {

        CurrencyType fromCurrencyType = this.getCurrencyType();
        CurrencyType toCurrencyType = toCurrency.getCurrencyType();

        if(fromCurrencyType.equals(toCurrencyType)) {

            return this;

        }

        List<ExchangeRate> exchangeRateList = getExchangeRate(fromCurrencyType);

        for (ExchangeRate exchangeRate : exchangeRateList) {

            CurrencyType exchangeRateToCurrencyType = exchangeRate.getToCurrencyType();

            if(exchangeRateToCurrencyType.equals(toCurrencyType)) {

                double rate = exchangeRate.getRate();
                toCurrency.setValue(this.getValue()/rate);
                return toCurrency;

            }

        }

        throw new CurrencyTypeNotFoundException("Currency Type Not Found Exception");

    }

    public abstract List<ExchangeRate> getExchangeRate(CurrencyType currencyType);

}
