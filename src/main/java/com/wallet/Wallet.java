package com.wallet;

import com.wallet.exception.CurrencyTypeNotFoundException;
import com.wallet.exception.InsufficientAmountInWalletException;
import com.wallet.exception.NegativeCreditValueException;
import com.wallet.exception.NegativeDebitValueException;

import java.util.HashMap;

public class Wallet {

    private final HashMap<String, Double> currencyValueHashMap;
    private final Currency preferredCurrency;
    private double availableAmountInWallet;
    private final CurrencyConverter currencyConverter;

    public Wallet(Currency preferredCurrency) {

        this.preferredCurrency = preferredCurrency;
        currencyValueHashMap = new HashMap<>();
        availableAmountInWallet = 0;
        currencyConverter = new CurrencyConverter();

    }

    public double getAvailableAmount() {
        return availableAmountInWallet;
    }

    public boolean credit(Currency currency) throws NegativeCreditValueException, CurrencyTypeNotFoundException {

        if(isNegative(currency.getValue())) {
            throw new NegativeCreditValueException("Credit value should not be negative");
        }

        double creditValue = currencyConverter.convert(currency, preferredCurrency);
        availableAmountInWallet += creditValue;
        creditToCorrespondingCurrencyType(currency);
        return true;

    }

    public boolean debit(Currency currency) throws NegativeDebitValueException, InsufficientAmountInWalletException, CurrencyTypeNotFoundException {

        if(isNegative(currency.getValue())) {
            throw new NegativeDebitValueException("Debit value should not be Negative");
        }

        if(isDebitValueExceedsWalletBalance(currency.getValue())) {
            throw new InsufficientAmountInWalletException("Insufficient Amount in Wallet");
        }

        double debitValue = currencyConverter.convert(currency, preferredCurrency);
        availableAmountInWallet -= debitValue;
        debitFromCorrespondingCurrencyType(currency);
        return true;

    }

    private boolean isNegative(double value) {
        return value <= 0;
    }

    private boolean isDebitValueExceedsWalletBalance(double debitValue) {
        return debitValue > getAvailableAmount();
    }

    private void creditToCorrespondingCurrencyType(Currency currency) {

        String currencyType = currency.getType(currency);

        if (currencyValueHashMap.containsKey(currencyType)) {
            Double existingValue = currencyValueHashMap.get(currencyType);
            currencyValueHashMap.put(currencyType, existingValue + currency.getValue());
        } else {
            currencyValueHashMap.put(currencyType, currency.getValue());
        }

    }

    private void debitFromCorrespondingCurrencyType(Currency currency) throws CurrencyTypeNotFoundException {
        String currencyType = currency.getType(currency);

        for(String key : currencyValueHashMap.keySet()) {
            if(key.equals(currencyType)) {
                Double existingValue = currencyValueHashMap.get(currencyType);
                currencyValueHashMap.put(currencyType, existingValue - currency.getValue());
            }
        }

        throw new CurrencyTypeNotFoundException("Currency Type Not Found Exception");
    }

}
