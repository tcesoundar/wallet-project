package com.wallet;

import com.wallet.exceptions.CurrencyTypeNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CurrencyConverter {

    private final List<ExchangeRate> exchangeRateList;

    public CurrencyConverter() {
        this.exchangeRateList = new ArrayList<>();
        constructExchangeRate();
    }

    private void constructExchangeRate() {
        exchangeRateList.add(new ExchangeRate(CurrencyType.RUPEE, CurrencyType.DOLLAR, 74.85));
        exchangeRateList.add(new ExchangeRate(CurrencyType.RUPEE, CurrencyType.POUND, 103.4));
        exchangeRateList.add(new ExchangeRate(CurrencyType.DOLLAR, CurrencyType.RUPEE, 0.013));
        exchangeRateList.add(new ExchangeRate(CurrencyType.DOLLAR, CurrencyType.POUND, 1.40));
        exchangeRateList.add(new ExchangeRate(CurrencyType.POUND, CurrencyType.RUPEE, 0.0096));
        exchangeRateList.add(new ExchangeRate(CurrencyType.POUND, CurrencyType.DOLLAR, 0.72));
    }

    public Currency convert(Currency from, Currency to) throws CurrencyTypeNotFoundException {

        CurrencyType fromCurrencyType = from.getCurrencyType();
        CurrencyType toCurrencyType = to.getCurrencyType();

        if(fromCurrencyType.equals(toCurrencyType))
            return from;

        for (ExchangeRate exchangeRateObj: exchangeRateList) {
            if(isExchangeRatePresent(fromCurrencyType, toCurrencyType, exchangeRateObj)) {
                double rate = exchangeRateObj.getRate();
                to.setValue(from.getValue()/rate);
                return to;
            }
        }

        throw new CurrencyTypeNotFoundException("Currency Type Not Found Exception");
    }

    private boolean isExchangeRatePresent(CurrencyType fromCurrencyType, CurrencyType toCurrencyType, ExchangeRate exchangeRateObj) {
        return exchangeRateObj.getFrom().equals(fromCurrencyType) && exchangeRateObj.getTo().equals(toCurrencyType);
    }

    private static class ExchangeRate {
        private final CurrencyType from;
        private final CurrencyType to;
        private final double rate;

        public ExchangeRate(CurrencyType from, CurrencyType to, double rate) {
            this.from = from;
            this.to = to;
            this.rate = rate;
        }

        public CurrencyType getFrom() {
            return from;
        }

        public CurrencyType getTo() {
            return to;
        }

        public double getRate() {
            return rate;
        }
    }

}
