package com.wallet;

import com.wallet.currencies.Dollar;
import com.wallet.currencies.Rupee;
import com.wallet.exception.CurrencyTypeNotFoundException;
import com.wallet.exception.InsufficientAmountInWalletException;
import com.wallet.exception.NegativeCreditValueException;
import com.wallet.exception.NegativeDebitValueException;

public class WalletApplication {

    public static void main(String[] args) throws NegativeCreditValueException, NegativeDebitValueException, InsufficientAmountInWalletException, CurrencyTypeNotFoundException {
        Wallet wallet = new Wallet(new Dollar());

        Rupee rupee = new Rupee(74.85);
        Dollar dollar = new Dollar(5);

        wallet.credit(rupee);
        wallet.credit(dollar);

        System.out.println("Wallet amount " + wallet.getAvailableAmount());

        Currency currency1 = new Rupee(12);
        Currency currency2 = new Dollar(10);

        System.out.println(currency1.getType(currency1) + " " + currency2.getType(currency2));
    }
}
