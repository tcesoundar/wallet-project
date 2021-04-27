package com.wallet.currencies;

import com.wallet.Currency;
import com.wallet.CurrencyType;
import com.wallet.ExchangeRate;

import java.util.ArrayList;
import java.util.List;

public class Rupee extends Currency {

    public Rupee() {

        super(CurrencyType.RUPEE);

    }

    public Rupee(double value) {

        super(value, CurrencyType.RUPEE);

    }

    @Override
    public List<ExchangeRate> getExchangeRate(CurrencyType currencyType) {

        List<ExchangeRate> exchangeRateList = new ArrayList<>();
        exchangeRateList.add(new ExchangeRate(CurrencyType.DOLLAR, 74.85));
        exchangeRateList.add(new ExchangeRate(CurrencyType.POUND, 103.4));
        return exchangeRateList;

    }

}
