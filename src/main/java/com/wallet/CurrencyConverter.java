package com.wallet;

import com.wallet.exception.CurrencyTypeNotFoundException;

import java.util.HashMap;

public class CurrencyConverter {

    private final HashMap<FromToPair,Double> exchangeRateMap;

    public CurrencyConverter() {
        exchangeRateMap = new HashMap<>();
        addExchangeRate();
    }

    private void addExchangeRate() {
        exchangeRateMap.put(new FromToPair("Rupee", "Dollar"), 74.85);
        exchangeRateMap.put(new FromToPair("Rupee", "Pound"), 103.4);
        exchangeRateMap.put(new FromToPair("Dollar", "Rupee"), 0.013);
        exchangeRateMap.put(new FromToPair("Dollar", "Pound"), 1.40);
        exchangeRateMap.put(new FromToPair("Pound", "Rupee"), 0.0096);
        exchangeRateMap.put(new FromToPair("Pound", "Dollar"), 0.72);
    }

    public double convert(Currency from, Currency to) throws CurrencyTypeNotFoundException {

        String fromType = from.getType(from);
        String toType = to.getType(to);

        if(fromType.equals(toType))
            return from.getValue();

        for(FromToPair key: exchangeRateMap.keySet()) {
            if(key.getFrom().equals(fromType) && key.getTo().equals(toType)) {
                Double exchangeRate = exchangeRateMap.get(key);
                return from.getValue()/exchangeRate;
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
