import com.wallet.Currency;
import com.wallet.Wallet;
import com.wallet.exception.InsufficientAmountInWalletException;
import com.wallet.exception.NegativeCreditValueException;
import com.wallet.exception.NegativeDebitValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
    
    @Test
    void shouldReturnEquivalentRupeesForOneDollar() {

        Dollar dollar = new Dollar(1);

        double actualValue = dollar.convertToRupees();

        assertEquals(74.85, actualValue);
        
    }

    @Test
    void shouldReturnTrueIfRupeesCanBeCreditedToWallet() throws NegativeCreditValueException {

        Wallet wallet = new Wallet(Currency.CurrencyType.RUPEE);
        Rupee rupee = new Rupee(50);

        boolean isRupeesCredited = wallet.credit(rupee);

        assertTrue(isRupeesCredited);

    }

    @Test
    void shouldReturnTrueIfDollarCanBeCreditedToWallet() throws NegativeCreditValueException {

        Wallet wallet = new Wallet(Currency.CurrencyType.RUPEE);
        Dollar dollar = new Dollar(1);

        boolean isDollarsCredited = wallet.credit(dollar);

        assertTrue(isDollarsCredited);

    }

    @Test
    void shouldReturnTotalWalletAmountInRupeesIfPreferredCurrencyTypeIsRupee() throws NegativeCreditValueException {

        Wallet wallet = new Wallet(Currency.CurrencyType.RUPEE);
        Rupee rupee = new Rupee(50);
        Dollar dollar = new Dollar(1);

        wallet.credit(rupee);
        wallet.credit(dollar);
        double availableAmountInWallet = wallet.getAvailableAmount();

        assertEquals(124.85, availableAmountInWallet);

    }

    @Test
    void shouldReturnTotalWalletAmountInDollarsIfPreferredCurrencyTypeIsDollar() throws NegativeCreditValueException {

        Wallet wallet = new Wallet(Currency.CurrencyType.DOLLAR);
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
    void shouldReturnTrueIfDollarCanBeDebited() throws NegativeCreditValueException, NegativeDebitValueException, InsufficientAmountInWalletException {

        Wallet wallet = new Wallet(Currency.CurrencyType.DOLLAR);
        Dollar dollar1 = new Dollar(5);
        Dollar dollar2 = new Dollar(3);

        wallet.credit(dollar1);
        boolean isDollarDebited = wallet.debit(dollar2);

        assertAll(() -> assertTrue(isDollarDebited), () -> assertEquals(2, wallet.getAvailableAmount()));

    }

    @Test
    void shouldGiveReasonIfCurrencyCannotBeCredit() {

        Wallet wallet = new Wallet(Currency.CurrencyType.RUPEE);
        Rupee rupee = new Rupee(-23);

        NegativeCreditValueException negativeCreditValueException = assertThrows(NegativeCreditValueException.class, () -> wallet.credit(rupee));

        assertEquals("Credit value should not be negative", negativeCreditValueException.getMessage());

    }

    @Test
    void shouldGiveReasonIfCurrencyCannotBeDebit() {

        Wallet wallet = new Wallet(Currency.CurrencyType.RUPEE);
        Rupee rupee = new Rupee(-45);

        NegativeDebitValueException negativeDebitValueException = assertThrows(NegativeDebitValueException.class, () -> wallet.debit(rupee));

        assertEquals("Debit value should not be Negative", negativeDebitValueException.getMessage());

    }

    @Test
    void shouldGiveReasonIfWalletNotHaveSufficientAmount() throws NegativeCreditValueException {

        Wallet wallet = new Wallet(Currency.CurrencyType.RUPEE);
        Rupee rupee1 = new Rupee(10);
        Rupee rupee2 = new Rupee(50);

        wallet.credit(rupee1);
        InsufficientAmountInWalletException insufficientAmountInWalletException = assertThrows(InsufficientAmountInWalletException.class, () -> wallet.debit(rupee2));

        assertEquals("Insufficient Amount in Wallet", insufficientAmountInWalletException.getMessage());

    }

    @Test
    void shouldReturnEquivalentRupeesForOnePound() {

        Pound pound = new Pound(1);

        double equivalentValue = pound.convertToRupees();

        assertEquals(103.45, equivalentValue);

    }

    @Test
    void shouldReturnTrueIfPoundCanBeCredit() throws NegativeCreditValueException {

        Wallet wallet = new Wallet(Currency.CurrencyType.POUND);
        Pound pound = new Pound(10);

        boolean isPoundCredited = wallet.credit(pound);

        assertTrue(isPoundCredited);

    }

    @Test
    void shouldReturnTrueIfPoundCanBeDebited() throws NegativeCreditValueException, NegativeDebitValueException, InsufficientAmountInWalletException {

        Wallet wallet = new Wallet(Currency.CurrencyType.POUND);
        Pound pound1 = new Pound(5);
        Pound pound2 = new Pound(3);

        wallet.credit(pound1);
        boolean isPoundDebited = wallet.debit(pound2);

        assertAll(() -> assertTrue(isPoundDebited), () -> assertEquals(2, wallet.getAvailableAmount()));

    }

    private static class Rupee implements Currency {

        private final double rupeeValue;
        
        public Rupee(double rupeeValue) {
            this.rupeeValue = rupeeValue;
        }

        @Override
        public double convertToRupees() {
            return rupeeValue;
        }

        @Override
        public double covertToDollars() {
            return rupeeValue/RUPEE_VALUE_FOR_ONE_DOLLAR;
        }

        @Override
        public double covertToPounds() {
            return rupeeValue/RUPEE_VALUE_FOR_ONE_DOLLAR;
        }
    }

    private static class Dollar implements Currency {

        private final double dollarValue;
        
        public Dollar(double dollarValue) {
            this.dollarValue = dollarValue;
        }

        @Override
        public double convertToRupees() {
            return dollarValue * RUPEE_VALUE_FOR_ONE_DOLLAR;
        }

        @Override
        public double covertToDollars() {
            return dollarValue;
        }

        @Override
        public double covertToPounds() {
            return convertToRupees()/RUPEE_VALUE_FOR_ONE_POUND;
        }
    }

    private static class Pound implements Currency {

        double poundValue;

        public Pound(double poundValue) {
            this.poundValue = poundValue;
        }

        @Override
        public double convertToRupees() {
            return poundValue*RUPEE_VALUE_FOR_ONE_POUND;
        }

        @Override
        public double covertToDollars() {
            return convertToRupees()/RUPEE_VALUE_FOR_ONE_DOLLAR;
        }

        @Override
        public double covertToPounds() {
            return poundValue;
        }
    }



}

