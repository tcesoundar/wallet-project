package com.wallet.currencies;

import com.wallet.Currency;
import com.wallet.CurrencyType;

public class Dollar extends Currency {

    public Dollar() {
        super(CurrencyType.DOLLAR);
    }

    public Dollar(double value) {
        super(value, CurrencyType.DOLLAR);
    }

}
