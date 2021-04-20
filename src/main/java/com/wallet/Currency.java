package com.wallet;

public abstract class Currency {

    private double value;

    public Currency() {
        this.value = 0;
    }

    public Currency(double value) {
        this.value = value;
    }

    public double getValue(){
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getType(Currency currency) {
        return currency.getClass().getSimpleName();
    }

}
