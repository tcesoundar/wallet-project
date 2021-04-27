package com.wallet;

import com.wallet.currencies.Dollar;
import com.wallet.currencies.Pound;
import com.wallet.currencies.Rupee;
import com.wallet.exceptions.CurrencyTypeNotFoundException;
import com.wallet.exceptions.NegativeCreditValueException;
import com.wallet.exceptions.NegativeDebitValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    @Test
    void shouldReturnTrueIfRupeesCanBeCreditedToWallet() throws NegativeCreditValueException, NegativeDebitValueException, CurrencyTypeNotFoundException {

        Wallet wallet = new Wallet();
        Rupee rupee = new Rupee(50);

        boolean isRupeesCredited = wallet.credit(rupee);
        boolean isRupeesDebited = wallet.debit(rupee);

        assertTrue(isRupeesCredited);
        assertTrue(isRupeesDebited);

    }

    @Test
    void shouldReturnTrueIfDollarCanBeCreditedToWallet() throws NegativeCreditValueException {

        Wallet wallet = new Wallet();
        Dollar dollar = new Dollar(1);

        boolean isDollarsCredited = wallet.credit(dollar);

        assertTrue(isDollarsCredited);

    }

    @Test
    void shouldReturnTotalWalletAmountInRupeesIfPreferredCurrencyTypeIsRupee() throws NegativeCreditValueException, CurrencyTypeNotFoundException {

        double expectedValue = 124.85;
        Wallet wallet = new Wallet();
        Rupee rupee = new Rupee(50);
        Dollar dollar = new Dollar(1);

        wallet.credit(rupee);
        wallet.credit(dollar);
        Currency preferredCurrency = wallet.getTotalAmount(new Rupee());
        double actualValue = preferredCurrency.getValue();

        assertEquals(expectedValue, actualValue, Math.abs(expectedValue-actualValue));

    }

    @Test
    void shouldReturnTotalWalletAmountInDollarsIfPreferredCurrencyTypeIsDollar() throws NegativeCreditValueException, CurrencyTypeNotFoundException {

        Wallet wallet = new Wallet();
        Rupee rupee1 = new Rupee(74.85);
        Dollar dollar = new Dollar(1);
        Rupee rupee2 = new Rupee(149.7);

        wallet.credit(rupee1);
        wallet.credit(dollar);
        wallet.credit(rupee2);
        Currency preferredCurrency = wallet.getTotalAmount(new Dollar());
        double actualValue = preferredCurrency.getValue();

        assertEquals(4, actualValue);

    }

    @Test
    void shouldReturnTrueIfDollarCanBeDebited() throws NegativeCreditValueException, NegativeDebitValueException, CurrencyTypeNotFoundException {

        Wallet wallet = new Wallet();
        Dollar dollar1 = new Dollar(5);
        Dollar dollar2 = new Dollar(3);

        boolean isDollarCredited = wallet.credit(dollar1);
        boolean isDollarDebited = wallet.debit(dollar2);
        Currency preferredCurrency = wallet.getTotalAmount(new Dollar());
        double actualValue = preferredCurrency.getValue();

        assertTrue(isDollarCredited);
        assertTrue(isDollarDebited);
        assertEquals(2, actualValue);

    }

    @Test
    void shouldGiveReasonIfCurrencyCannotBeCredit() {

        Wallet wallet = new Wallet();
        Rupee rupee = new Rupee(-23);

        NegativeCreditValueException negativeCreditValueException = assertThrows(NegativeCreditValueException.class, () -> wallet.credit(rupee));

        assertEquals("Credit value should not be negative", negativeCreditValueException.getMessage());

    }

    @Test
    void shouldGiveReasonIfCurrencyCannotBeDebit() {

        Wallet wallet = new Wallet();
        Rupee rupee = new Rupee(-45);

        NegativeDebitValueException negativeDebitValueException = assertThrows(NegativeDebitValueException.class, () -> wallet.debit(rupee));

        assertEquals("Debit value should not be Negative", negativeDebitValueException.getMessage());

    }

    @Test
    void shouldReturnTrueIfPoundCanBeCredit() throws NegativeCreditValueException {

        Wallet wallet = new Wallet();
        Pound pound = new Pound(10);

        boolean isPoundCredited = wallet.credit(pound);

        assertTrue(isPoundCredited);

    }

    @Test
    void shouldReturnTrueIfPoundCanBeDebited() throws NegativeCreditValueException, NegativeDebitValueException, CurrencyTypeNotFoundException {

        Wallet wallet = new Wallet();
        Pound pound1 = new Pound(5);
        Pound pound2 = new Pound(3);

        wallet.credit(pound1);
        boolean isPoundDebited = wallet.debit(pound2);

        assertAll(() -> assertTrue(isPoundDebited), () -> assertEquals(2, wallet.getTotalAmount(new Pound()).getValue()));

    }
    
}

