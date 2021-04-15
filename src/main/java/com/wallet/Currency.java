package com.wallet;

public interface Currency {

    double RUPEE_VALUE_FOR_ONE_DOLLAR = 74.85;
    double RUPEE_VALUE_FOR_ONE_POUND = 103.45;

    double convertToRupees();
    double covertToDollars();
    double covertToPounds();

    enum CurrencyType {
        RUPEE,
        DOLLAR,
        POUND
    }

}
