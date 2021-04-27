package com.wallet.currencies;

import com.wallet.Currency;
import com.wallet.CurrencyType;
import com.wallet.ExchangeRate;

import java.util.ArrayList;
import java.util.List;

public class Pound extends Currency {

    public Pound() {

        super(CurrencyType.POUND);

    }

    public Pound(double value) {

        super(value, CurrencyType.POUND);

    }

    @Override
    public List<ExchangeRate> getExchangeRate(CurrencyType currencyType) {

        List<ExchangeRate> exchangeRateList = new ArrayList<>();
        exchangeRateList.add(new ExchangeRate(CurrencyType.RUPEE, 0.0096));
        exchangeRateList.add(new ExchangeRate(CurrencyType.DOLLAR, 0.72));
        return exchangeRateList;

    }

}
