package com.wallet.currencies;

import com.wallet.CurrencyType;
import com.wallet.Currency;

public class Rupee extends Currency {

    public Rupee() {
        super(CurrencyType.RUPEE);

    }

    public Rupee(double value) {
        super(value, CurrencyType.RUPEE);
    }

}
