package com.wallet;

import com.wallet.exceptions.CurrencyTypeNotFoundException;
import com.wallet.exceptions.NegativeCreditValueException;
import com.wallet.exceptions.NegativeDebitValueException;

import java.util.ArrayList;
import java.util.List;

public class Wallet {

    private final List<Currency> currencyList;

    public Wallet() {

        currencyList = new ArrayList<>();

    }

    public boolean credit(Currency currency) throws NegativeCreditValueException {

        if (isNegative(currency.getValue())) {

            throw new NegativeCreditValueException("Credit value should not be negative");

        }

        creditToCorrespondingCurrencyType(currency);
        return true;

    }

    public boolean debit(Currency currency) throws NegativeDebitValueException, CurrencyTypeNotFoundException {

        if (isNegative(currency.getValue())) {

            throw new NegativeDebitValueException("Debit value should not be Negative");

        }

        debitFromCorrespondingCurrencyType(currency);
        return true;

    }

    public Currency getTotalAmount(Currency preferredCurrency) throws CurrencyTypeNotFoundException {

        double totalAmountInPreferredCurrency = 0;

        for (Currency fromCurrency : currencyList) {

            Currency convertedCurrency = fromCurrency.convertTo(preferredCurrency);
            totalAmountInPreferredCurrency += convertedCurrency.getValue();

        }

        preferredCurrency.setValue(totalAmountInPreferredCurrency);

        return preferredCurrency;

    }

    private boolean isNegative(double value) {

        return value <= 0;

    }

    private void creditToCorrespondingCurrencyType(Currency creditCurrency) {

        CurrencyType creditCurrencyType = creditCurrency.getCurrencyType();

        for (Currency currency : currencyList) {

            CurrencyType currencyType = currency.getCurrencyType();

            if (currencyType.equals(creditCurrencyType)) {

                double existingValue = currency.getValue();
                double creditValue = creditCurrency.getValue();
                currency.setValue(existingValue + creditValue);
                return;

            }

        }

        currencyList.add(creditCurrency);

    }

    private void debitFromCorrespondingCurrencyType(Currency debitCurrency) throws CurrencyTypeNotFoundException {

        CurrencyType debitCurrencyType = debitCurrency.getCurrencyType();

        for (Currency currency : currencyList) {

            CurrencyType currencyType = currency.getCurrencyType();

            if (currencyType.equals(debitCurrencyType)) {

                double existingValue = currency.getValue();
                double debitValue = debitCurrency.getValue();
                currency.setValue(existingValue - debitValue);
                return;

            }

        }

        throw new CurrencyTypeNotFoundException("Currency Type Not Found Exception");

    }

}
