package com.wallet.currencies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PoundTest {

    @Test
    void shouldReturnPoundValue() {
        Pound pound = new Pound(5);

        double actualValue = pound.getValue();

        assertEquals(5, actualValue);
    }

}
