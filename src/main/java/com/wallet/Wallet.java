package com.wallet;

import com.wallet.exceptions.CurrencyTypeNotFoundException;
import com.wallet.exceptions.InsufficientAmountInWalletException;
import com.wallet.exceptions.NegativeCreditValueException;
import com.wallet.exceptions.NegativeDebitValueException;

import java.util.HashMap;

public class Wallet {

    private final CurrencyConverter currencyConverter;
    private final HashMap<String, Double> currencyTypeVsValue;
    private final Currency preferredCurrency;
    private double availableAmountInWallet;

    public Wallet(Currency preferredCurrency) {

        this.preferredCurrency = preferredCurrency;
        currencyTypeVsValue = new HashMap<>();
        availableAmountInWallet = 0;
        currencyConverter = new CurrencyConverter();

    }

    public Currency getPreferredCurrency() {
        preferredCurrency.setValue(availableAmountInWallet);
        return preferredCurrency;
    }

    public boolean credit(Currency currency) throws NegativeCreditValueException, CurrencyTypeNotFoundException {

        if(isNegative(currency.getValue())) {
            throw new NegativeCreditValueException("Credit value should not be negative");
        }

        Currency creditCurrency = currencyConverter.convert(currency, preferredCurrency);
        availableAmountInWallet += creditCurrency.getValue();
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

        Currency debitCurrency = currencyConverter.convert(currency, preferredCurrency);
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

        String currencyType = currency.getType(currency);

        if (currencyTypeVsValue.containsKey(currencyType)) {
            Double existingValue = currencyTypeVsValue.get(currencyType);
            currencyTypeVsValue.put(currencyType, existingValue + currency.getValue());
        } else {
            currencyTypeVsValue.put(currencyType, currency.getValue());
        }

    }

    private void debitFromCorrespondingCurrencyType(Currency currency) throws CurrencyTypeNotFoundException {

        String currencyType = currency.getType(currency);

        if(currencyTypeVsValue.containsKey(currencyType)) {
            Double existingValue = currencyTypeVsValue.get(currencyType);
            currencyTypeVsValue.put(currencyType, existingValue - currency.getValue());
        } else {
            throw new CurrencyTypeNotFoundException("Currency Type Not Found Exception");
        }

    }

}
