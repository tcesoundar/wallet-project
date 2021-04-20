package com.wallet.currencies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RupeeTest {

    @Test
    void shouldReturnRupeeValue() {
        Rupee rupee = new Rupee(6);

        double actualValue = rupee.getValue();

        assertEquals(6, actualValue);
    }

}
