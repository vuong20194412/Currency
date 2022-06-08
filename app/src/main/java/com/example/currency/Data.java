package com.example.currency;

public class Data {

    private final double rate;
    private final String text;
    private final String icon;

    public Data(double rate, String text, String icon) {
        this.rate = rate;
        this.text = text;
        this.icon = icon;
    }

    public double getRate() {
        return rate;
    }

    public String getText() {
        return text;
    }

    public String getIcon() {
        return icon;
    }
}
