package com.wallet;

import com.wallet.exceptions.CurrencyTypeNotFoundException;

import java.util.HashMap;

public class CurrencyConverter {

    private final HashMap<FromToPair,Double> exchangeRate;

    public CurrencyConverter() {
        exchangeRate = new HashMap<>();
        constructExchangeRate();
    }

    private void constructExchangeRate() {
        exchangeRate.put(new FromToPair("Rupee", "Dollar"), 74.85);
        exchangeRate.put(new FromToPair("Rupee", "Pound"), 103.4);
        exchangeRate.put(new FromToPair("Dollar", "Rupee"), 0.013);
        exchangeRate.put(new FromToPair("Dollar", "Pound"), 1.40);
        exchangeRate.put(new FromToPair("Pound", "Rupee"), 0.0096);
        exchangeRate.put(new FromToPair("Pound", "Dollar"), 0.72);
    }

    public Currency convert(Currency from, Currency to) throws CurrencyTypeNotFoundException {

        String fromType = from.getType(from);
        String toType = to.getType(to);

        if(fromType.equals(toType))
            return from;

        for(FromToPair key: exchangeRate.keySet()) {
            if(key.getFrom().equals(fromType) && key.getTo().equals(toType)) {
                Double exchangeRate = this.exchangeRate.get(key);
                to.setValue(from.getValue()/exchangeRate);
                return to;
            }
        }

        throw new CurrencyTypeNotFoundException("Currency Type Not Found Exception");
    }

    private static class FromToPair {
        private final String from;
        private final String to;

        public FromToPair(String from, String to) {
            this.from = from;
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }
    }

}
