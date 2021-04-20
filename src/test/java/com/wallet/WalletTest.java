package com.wallet;

import com.wallet.currencies.Dollar;
import com.wallet.currencies.Pound;
import com.wallet.currencies.Rupee;
import com.wallet.exception.CurrencyTypeNotFoundException;
import com.wallet.exception.InsufficientAmountInWalletException;
import com.wallet.exception.NegativeCreditValueException;
import com.wallet.exception.NegativeDebitValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    @Test
    void shouldReturnTrueIfRupeesCanBeCreditedToWallet() throws NegativeCreditValueException, NegativeDebitValueException, InsufficientAmountInWalletException, CurrencyTypeNotFoundException {

        Wallet wallet = new Wallet(new Rupee());
        Rupee rupee = new Rupee(50);

        boolean isRupeesCredited = wallet.credit(rupee);
        boolean isRupeesDebited = wallet.debit(rupee);

        assertTrue(isRupeesCredited);
        assertTrue(isRupeesDebited);

    }

    @Test
    void shouldReturnTrueIfDollarCanBeCreditedToWallet() throws NegativeCreditValueException, CurrencyTypeNotFoundException {

        Wallet wallet = new Wallet(new Rupee());
        Dollar dollar = new Dollar(1);

        boolean isDollarsCredited = wallet.credit(dollar);

        assertTrue(isDollarsCredited);

    }

    @Test
    void shouldReturnTotalWalletAmountInRupeesIfPreferredCurrencyTypeIsRupee() throws NegativeCreditValueException, CurrencyTypeNotFoundException {

        double expectedValue = 124.85;
        Wallet wallet = new Wallet(new Rupee());
        Rupee rupee = new Rupee(50);
        Dollar dollar = new Dollar(1);

        wallet.credit(rupee);
        wallet.credit(dollar);
        double actualValue = wallet.getAvailableAmount();

        assertEquals(expectedValue, actualValue, Math.abs(expectedValue-actualValue));

    }

    @Test
    void shouldReturnTotalWalletAmountInDollarsIfPreferredCurrencyTypeIsDollar() throws NegativeCreditValueException, CurrencyTypeNotFoundException {

        Wallet wallet = new Wallet(new Dollar());
        Rupee rupee1 = new Rupee(74.85);
        Dollar dollar = new Dollar(1);
        Rupee rupee2 = new Rupee(149.7);

        wallet.credit(rupee1);
        wallet.credit(dollar);
        wallet.credit(rupee2);
        double availableAmountInWallet = wallet.getAvailableAmount();

        assertEquals(4, availableAmountInWallet);

    }

    @Test
    void shouldReturnTrueIfDollarCanBeDebited() throws NegativeCreditValueException, NegativeDebitValueException, InsufficientAmountInWalletException, CurrencyTypeNotFoundException {

        Wallet wallet = new Wallet(new Dollar());
        Dollar dollar1 = new Dollar(5);
        Dollar dollar2 = new Dollar(3);

        boolean isDollarCredited = wallet.credit(dollar1);
        boolean isDollarDebited = wallet.debit(dollar2);
        double actualValue = wallet.getAvailableAmount();

        assertTrue(isDollarCredited);
        assertTrue(isDollarDebited);
        assertEquals(2, actualValue);

    }

    @Test
    void shouldGiveReasonIfCurrencyCannotBeCredit() {

        Wallet wallet = new Wallet(new Rupee());
        Rupee rupee = new Rupee(-23);

        NegativeCreditValueException negativeCreditValueException = assertThrows(NegativeCreditValueException.class, () -> wallet.credit(rupee));

        assertEquals("Credit value should not be negative", negativeCreditValueException.getMessage());

    }

    @Test
    void shouldGiveReasonIfCurrencyCannotBeDebit() {

        Wallet wallet = new Wallet(new Rupee());
        Rupee rupee = new Rupee(-45);

        NegativeDebitValueException negativeDebitValueException = assertThrows(NegativeDebitValueException.class, () -> wallet.debit(rupee));

        assertEquals("Debit value should not be Negative", negativeDebitValueException.getMessage());

    }

    @Test
    void shouldGiveReasonIfWalletNotHaveSufficientAmount() throws NegativeCreditValueException, CurrencyTypeNotFoundException {

        Wallet wallet = new Wallet(new Rupee());
        Rupee rupee1 = new Rupee(10);
        Rupee rupee2 = new Rupee(50);

        wallet.credit(rupee1);
        InsufficientAmountInWalletException insufficientAmountInWalletException = assertThrows(InsufficientAmountInWalletException.class, () -> wallet.debit(rupee2));

        assertEquals("Insufficient Amount in Wallet", insufficientAmountInWalletException.getMessage());

    }

    @Test
    void shouldReturnTrueIfPoundCanBeCredit() throws NegativeCreditValueException, CurrencyTypeNotFoundException {

        Wallet wallet = new Wallet(new Pound());
        Pound pound = new Pound(10);

        boolean isPoundCredited = wallet.credit(pound);

        assertTrue(isPoundCredited);

    }

    @Test
    void shouldReturnTrueIfPoundCanBeDebited() throws NegativeCreditValueException, NegativeDebitValueException, InsufficientAmountInWalletException, CurrencyTypeNotFoundException {

        Wallet wallet = new Wallet(new Pound());
        Pound pound1 = new Pound(5);
        Pound pound2 = new Pound(3);

        wallet.credit(pound1);
        boolean isPoundDebited = wallet.debit(pound2);

        assertAll(() -> assertTrue(isPoundDebited), () -> assertEquals(2, wallet.getAvailableAmount()));

    }
    
}

