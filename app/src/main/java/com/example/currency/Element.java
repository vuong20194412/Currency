package com.example.currency;

public class Element {

    private boolean hasDot = false;
    private double value = 0.0;
    private String text = "0";
    private int index;
    private double rate;
    private String icon;

    public Element(int index, double rate, String icon) {
        this.index = index;
        this.rate = rate;
        this.icon = icon;
    }

    public boolean getHasDot() {
        return hasDot;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        text = String.valueOf(value);
        if (text.endsWith(".0")) {
            text = text.substring(0, text.length() - 2);
            hasDot = false;
        }
        else {
            hasDot = true;
        }
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void appendValue(int value) {
        if (text.equals("0")) {
            text = String.valueOf(value);
        }
        else {
            text += String.valueOf(value);
        }
        this.value = Double.parseDouble(text);
    }

    public void appendDot() {
        if (!hasDot) {
            text += ".";
            hasDot = true;
        }
    }

    public void clear() {
        value = 0.0;
        text = "0";
        hasDot = false;
    }

    public void clearAChar() {
        if (text.length() == 1) {
            text = "0";
            value = 0.0;
            hasDot = false;
        }
        else if (hasDot) {
            if (text.endsWith(".")) {
                text = text.substring(0, text.length() - 1);
                hasDot = false;
            }
            else {
                text = text.substring(0, text.length() - 1);
                if (text.endsWith("E")) {
                    text = text.substring(0, text.length() - 1);
                }
                else if (text.endsWith("E-")) {
                    text = text.substring(0, text.length() - 2);
                }
                value = Double.parseDouble(text);
            }
        }
        else {
            text = text.substring(0, text.length() - 1);
            value = Double.parseDouble(text);
        }
    }
}
