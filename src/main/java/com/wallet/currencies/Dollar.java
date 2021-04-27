package com.wallet.currencies;

import com.wallet.Currency;
import com.wallet.CurrencyType;
import com.wallet.ExchangeRate;

import java.util.ArrayList;
import java.util.List;

public class Dollar extends Currency {

    public Dollar() {

        super(CurrencyType.DOLLAR);

    }

    public Dollar(double value) {

        super(value, CurrencyType.DOLLAR);

    }

    @Override
    public List<ExchangeRate> getExchangeRate(CurrencyType currencyType) {

        List<ExchangeRate> exchangeRateList = new ArrayList<>();
        exchangeRateList.add(new ExchangeRate(CurrencyType.RUPEE, 0.013));
        exchangeRateList.add(new ExchangeRate(CurrencyType.POUND, 1.40));
        return exchangeRateList;

    }

}
