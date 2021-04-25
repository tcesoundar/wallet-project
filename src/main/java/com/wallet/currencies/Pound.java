package com.wallet.currencies;

import com.wallet.Currency;
import com.wallet.CurrencyType;

public class Pound extends Currency {

    public Pound() {
        super(CurrencyType.POUND);
    }

    public Pound(double value) {
        super(value, CurrencyType.POUND);
    }

}
