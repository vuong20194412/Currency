package com.example.currency;

import android.widget.TextView;

public class Element {

    private TextView iconView, textView;
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

    public void setView(TextView textView, TextView iconView) {
        this.textView = textView;
        this.iconView = iconView;
    }

    public void setRate(double rate) {
        this.rate = rate;
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

    public TextView getTextView() {
        return textView;
    }

    public int getIndex() {
        return index;
    }

    public boolean appendZero() {
        if (text.equals("0")) {
            return false;
        }
        else {
            text += "0";
            this.value = Double.parseDouble(text);
            return true;
        }
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

    public boolean appendDot() {
        if (hasDot) {
            return false;
        }
        else {
            text += ".";
            hasDot = true;
            return true;
        }
    }

    public void clear() {
        value = 0.0;
        text = "0";
        hasDot = false;
    }

    public boolean clearAChar() {
        if (text.equals("0")) {
            return false;
        }
        else {
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
            return true;
        }
    }

    public double calValue() {
        return rate * value;
    }

    public void replaceIcon(int index, String icon) {
        this.index = index;
        this.icon = icon;
        displayIcon();
    }

    public void displayIcon() {
        this.iconView.setText(this.icon);
    }

    public void displayText() {
        this.textView.setText(text);
    }

    public void setBoldColor(boolean isBold) {
        int BLACK = 0xFF000000;
        int LIGHT_BLACK = 0x80000000;
        this.textView.setTextColor(isBold ? BLACK : LIGHT_BLACK);
    }
}
