package com.wallet;

import com.wallet.currencies.Dollar;
import com.wallet.currencies.Pound;
import com.wallet.currencies.Rupee;
import com.wallet.exceptions.CurrencyTypeNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyConverterTest {

    @Test
    void shouldReturnEquivalentRupeeValueForOneDollar() throws CurrencyTypeNotFoundException {
        double expectedValue = 74.85;
        CurrencyConverter currencyConverter = new CurrencyConverter();
        Dollar dollar = new Dollar(1);
        Rupee rupee = new Rupee();

        Currency equivalentCurrency = currencyConverter.convert(dollar, rupee);
        double actualValue = equivalentCurrency.getValue();

        assertEquals(expectedValue, actualValue, Math.abs(actualValue-expectedValue));
    }

    @Test
    void shouldReturnEquivalentRupeeValueForOnePound() throws CurrencyTypeNotFoundException {
        double expectedValue = 103.4;
        CurrencyConverter currencyConverter = new CurrencyConverter();
        Pound pound = new Pound(1);
        Rupee rupee = new Rupee();

        Currency equivalentCurrency = currencyConverter.convert(pound, rupee);
        double actualValue = equivalentCurrency.getValue();

        assertEquals(expectedValue, actualValue, Math.abs(actualValue-expectedValue));
    }

    @Test
    void shouldReturnEquivalentDollarValueForOneRupee() throws CurrencyTypeNotFoundException {
        double expectedValue = 0.013;
        CurrencyConverter currencyConverter = new CurrencyConverter();
        Rupee rupee = new Rupee(1);
        Dollar dollar = new Dollar();

        Currency equivalentCurrency = currencyConverter.convert(rupee, dollar);
        double actualValue = equivalentCurrency.getValue();

        assertEquals(expectedValue, actualValue, Math.abs(actualValue-expectedValue));
    }

    @Test
    void shouldReturnEquivalentDollarValueForOnePound() throws CurrencyTypeNotFoundException {
        double expectedValue = 0.72;
        CurrencyConverter currencyConverter = new CurrencyConverter();
        Pound pound = new Pound(1);
        Dollar dollar = new Dollar();

        Currency equivalentCurrency = currencyConverter.convert(pound, dollar);
        double actualValue = equivalentCurrency.getValue();

        assertEquals(expectedValue, actualValue, Math.abs(actualValue-expectedValue));
    }

    @Test
    void shouldReturnEquivalentPoundValueForOneRupee() throws CurrencyTypeNotFoundException {
        double expectedValue = 0.0096;
        CurrencyConverter currencyConverter = new CurrencyConverter();
        Rupee rupee = new Rupee(1);
        Pound pound = new Pound();

        Currency equivalentCurrency = currencyConverter.convert(rupee, pound);
        double actualValue = equivalentCurrency.getValue();

        assertEquals(expectedValue, actualValue, Math.abs(actualValue-expectedValue));
    }

    @Test
    void shouldReturnEquivalentPoundValueForOneDollar() throws CurrencyTypeNotFoundException {
        double expectedValue = 1.40;
        CurrencyConverter currencyConverter = new CurrencyConverter();
        Dollar dollar = new Dollar(1);
        Pound pound = new Pound();

        Currency equivalentCurrency = currencyConverter.convert(dollar, pound);
        double actualValue = equivalentCurrency.getValue();

        assertEquals(expectedValue, actualValue, Math.abs(expectedValue-actualValue));
    }

}