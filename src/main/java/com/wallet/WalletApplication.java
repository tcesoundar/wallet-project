package com.wallet;

import com.wallet.currencies.Dollar;
import com.wallet.currencies.Pound;
import com.wallet.currencies.Rupee;
import com.wallet.exceptions.CurrencyTypeNotFoundException;
import com.wallet.exceptions.NegativeCreditValueException;
import com.wallet.exceptions.NegativeDebitValueException;

public class WalletApplication {

    public static void main(String[] args) throws NegativeCreditValueException, NegativeDebitValueException, CurrencyTypeNotFoundException {

        Wallet wallet1 = new Wallet();
        Rupee rupee = new Rupee(74.85);
        Dollar dollar = new Dollar(5);
        Pound pound = new Pound(1);

        System.out.println();
        System.out.println("Rupee Credit Status : " + wallet1.credit(rupee));
        System.out.println("Dollar Credit Status : " + wallet1.credit(dollar));
        System.out.println("Pound Credit Status : " + wallet1.credit(pound));

        System.out.println();
        System.out.println("Total Amount When Preferred Currency is Rupee : " + wallet1.getTotalAmount(new Rupee()) + " : " + wallet1.getTotalAmount(new Rupee()).getValue());
        System.out.println("Total Amount When Preferred Currency is Dollar : " + wallet1.getTotalAmount(new Dollar()) + " : " + wallet1.getTotalAmount(new Dollar()).getValue());
        System.out.println("Total Amount When Preferred Currency is Pound : " + wallet1.getTotalAmount(new Pound()) + " : " + wallet1.getTotalAmount(new Pound()).getValue());

        System.out.println();
        System.out.println("Rupee Debit Status : " + wallet1.debit(rupee));

        System.out.println();
        System.out.println("Total Amount When Preferred Currency is Rupee : " + wallet1.getTotalAmount(new Rupee()) + " : " + wallet1.getTotalAmount(new Rupee()).getValue());
        System.out.println("Total Amount When Preferred Currency is Dollar : " + wallet1.getTotalAmount(new Dollar()) + " : " + wallet1.getTotalAmount(new Dollar()).getValue());
        System.out.println("Total Amount When Preferred Currency is Pound : " + wallet1.getTotalAmount(new Pound()) + " : " + wallet1.getTotalAmount(new Pound()).getValue());

        System.out.println();
        System.out.println("Dollar Debit Status : " + wallet1.debit(dollar));

        System.out.println();
        System.out.println("Total Amount When Preferred Currency is Rupee : " + wallet1.getTotalAmount(new Rupee()) + " : " + wallet1.getTotalAmount(new Rupee()).getValue());
        System.out.println("Total Amount When Preferred Currency is Dollar : " + wallet1.getTotalAmount(new Dollar()) + " : " + wallet1.getTotalAmount(new Dollar()).getValue());
        System.out.println("Total Amount When Preferred Currency is Pound : " + wallet1.getTotalAmount(new Pound()) + " : " + wallet1.getTotalAmount(new Pound()).getValue());

        System.out.println();
        System.out.println("Pound Debit Status : " + wallet1.debit(pound));

        System.out.println();
        System.out.println("Total Amount When Preferred Currency is Rupee : " + wallet1.getTotalAmount(new Rupee()) + " : " + wallet1.getTotalAmount(new Rupee()).getValue());
        System.out.println("Total Amount When Preferred Currency is Dollar : " + wallet1.getTotalAmount(new Dollar()) + " : " + wallet1.getTotalAmount(new Dollar()).getValue());
        System.out.println("Total Amount When Preferred Currency is Pound : " + wallet1.getTotalAmount(new Pound()) + " : " + wallet1.getTotalAmount(new Pound()).getValue());

        Currency currency1 = new Rupee(12);
        Currency currency2 = new Dollar(10);
        Currency currency3 = new Pound(15);

        System.out.println();
        System.out.println("Currency 1 : " + currency1.getCurrencyType());
        System.out.println("Currency 2 : " + currency2.getCurrencyType());
        System.out.println("Currency 3 : " + currency3.getCurrencyType());

    }

}
