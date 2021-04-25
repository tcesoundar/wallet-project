package com.wallet;

import com.wallet.exceptions.CurrencyTypeNotFoundException;
import com.wallet.exceptions.InsufficientAmountInWalletException;
import com.wallet.exceptions.NegativeCreditValueException;
import com.wallet.exceptions.NegativeDebitValueException;

import java.util.ArrayList;
import java.util.List;

public class Wallet {

    private final List<Currency> currencyList;
    private final Currency preferredCurrency;
    private double availableAmountInWallet;

    public Wallet(Currency preferredCurrency) {
        this.preferredCurrency = preferredCurrency;
        currencyList = new ArrayList<>();
        availableAmountInWallet = 0;
    }

    public Currency getPreferredCurrency() {
        preferredCurrency.setValue(availableAmountInWallet);
        return preferredCurrency;
    }

    public boolean credit(Currency currency) throws NegativeCreditValueException, CurrencyTypeNotFoundException {

        if (isNegative(currency.getValue())) {
            throw new NegativeCreditValueException("Credit value should not be negative");
        }

        Currency creditCurrency = currency.convert(currency, preferredCurrency);
        availableAmountInWallet += creditCurrency.getValue();
        creditToCorrespondingCurrencyType(currency);
        return true;

    }

    public boolean debit(Currency currency) throws NegativeDebitValueException, InsufficientAmountInWalletException, CurrencyTypeNotFoundException {

        if (isNegative(currency.getValue())) {
            throw new NegativeDebitValueException("Debit value should not be Negative");
        }

        if (isDebitValueExceedsWalletBalance(currency.getValue())) {
            throw new InsufficientAmountInWalletException("Insufficient Amount in Wallet");
        }

        Currency debitCurrency = currency.convert(currency, preferredCurrency);
        availableAmountInWallet -= debitCurrency.getValue();
        debitFromCorrespondingCurrencyType(currency);
        return true;

    }

    private boolean isNegative(double value) {
        return value <= 0;
    }

    private boolean isDebitValueExceedsWalletBalance(double debitValue) {
        return debitValue > getPreferredCurrency().getValue();
    }

    private void creditToCorrespondingCurrencyType(Currency currency) {

        CurrencyType currencyType = currency.getCurrencyType();

        for (Currency currencyObj : currencyList) {
            if (currencyObj.getCurrencyType().equals(currencyType)) {
                double existingValue = currencyObj.getValue();
                double creditValue = currency.getValue();
                currencyObj.setValue(existingValue + creditValue);
            } else {
                currencyList.add(currency);
            }
        }

    }

    private void debitFromCorrespondingCurrencyType(Currency currency) throws CurrencyTypeNotFoundException {

        CurrencyType currencyType = currency.getCurrencyType();

        for (Currency currencyObj : currencyList) {
            if (currencyObj.getCurrencyType().equals(currencyType)) {
                double existingValue = currencyObj.getValue();
                double debitValue = currency.getValue();
                currencyObj.setValue(existingValue - debitValue);
            } else {
                throw new CurrencyTypeNotFoundException("Currency Type Not Found Exception");
            }
        }

    }

}
