package com.wallet.currencies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DollarTest {

    @Test
    void shouldReturnDollarValue() {
        Dollar dollar = new Dollar(10);

        double actualValue = dollar.getValue();

        assertEquals(10, actualValue);
    }

}
