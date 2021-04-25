package com.wallet;

import com.wallet.currencies.Dollar;
import com.wallet.currencies.Pound;
import com.wallet.currencies.Rupee;
import com.wallet.exceptions.CurrencyTypeNotFoundException;
import com.wallet.exceptions.InsufficientAmountInWalletException;
import com.wallet.exceptions.NegativeCreditValueException;
import com.wallet.exceptions.NegativeDebitValueException;

public class WalletApplication {

    public static void main(String[] args) throws NegativeCreditValueException, NegativeDebitValueException, InsufficientAmountInWalletException, CurrencyTypeNotFoundException {

        Wallet wallet = new Wallet(new Dollar());
        Rupee rupee = new Rupee(74.85);
        Dollar dollar = new Dollar(5);

        wallet.credit(rupee);
        wallet.credit(dollar);

        System.out.println("Wallet amount " + wallet.getPreferredCurrency().getValue());

        Currency currency1 = new Rupee(12);
        Currency currency2 = new Dollar(10);

        System.out.println(currency1.getCurrencyType() + " " + currency2.getCurrencyType());

        Wallet wallet1 = new Wallet(new Pound());
        Pound pound1 = new Pound(5);
        Pound pound2 = new Pound(3);
        boolean isPoundCredited = wallet1.credit(pound1);
        System.out.println("Pound credit status " + isPoundCredited);
        boolean isPoundDebited = wallet1.debit(pound2);
        System.out.println("Pound Debit status " + isPoundDebited);
        System.out.println("Wallet Amount " + wallet1.getPreferredCurrency().getValue());

        CurrencyConverter currencyConverter = new CurrencyConverter();
        Currency convertedCurrency = currencyConverter.convert(new Dollar(4), new Rupee());
        System.out.println("converted Currency Type " + convertedCurrency.getCurrencyType());
        System.out.println("converted Value " + convertedCurrency.getValue());

    }

}
