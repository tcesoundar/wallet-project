package com.wallet;

import com.wallet.exception.InsufficientAmountInWalletException;
import com.wallet.exception.NegativeCreditValueException;
import com.wallet.exception.NegativeDebitValueException;

public class Wallet {

    private final Currency.CurrencyType preferredCurrencyType;
    private double availableAmountInWallet;

    public Wallet(Currency.CurrencyType preferredCurrencyType) {
        this.preferredCurrencyType = preferredCurrencyType;
    }

    public double getAvailableAmount() {
        return availableAmountInWallet;
    }

    public boolean credit(Currency currency) throws NegativeCreditValueException {
        double creditValue = getValueInPreferredCurrencyType(currency);

        if(isNegative(creditValue))
            throw new NegativeCreditValueException("Credit value should not be negative");

        availableAmountInWallet += creditValue;
        return true;
    }

    public boolean debit(Currency currency) throws NegativeDebitValueException, InsufficientAmountInWalletException {
        double debitValue = getValueInPreferredCurrencyType(currency);

        if(isNegative(debitValue))
            throw new NegativeDebitValueException("Debit value should not be Negative");

        if(isDebitValueExceedsWalletBalance(debitValue))
            throw new InsufficientAmountInWalletException("Insufficient Amount in Wallet");

        availableAmountInWallet -= debitValue;
        return true;
    }

    private double getValueInPreferredCurrencyType(Currency currency) {
        double preferredCurrencyValue = Double.MIN_VALUE;

        if(preferredCurrencyType.equals(Currency.CurrencyType.RUPEE))
            preferredCurrencyValue = currency.convertToRupees();

        if(preferredCurrencyType.equals(Currency.CurrencyType.DOLLAR))
            preferredCurrencyValue = currency.covertToDollars();

        if(preferredCurrencyType.equals(Currency.CurrencyType.POUND))
            preferredCurrencyValue = currency.covertToPounds();

        return preferredCurrencyValue;
    }

    private boolean isNegative(double value) {
        return value <= 0;
    }

    private boolean isDebitValueExceedsWalletBalance(double debitValue) {
        return debitValue > getAvailableAmount();
    }

}
