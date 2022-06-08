package com.example.currency;

import java.util.concurrent.atomic.AtomicInteger;

public class ArrayData {

    private final Data[] dataList = new Data[] {
            new Data(3474, "China - Yuan","¥"),
            new Data(24784, "Europe - Euro","€"),
            new Data(174, "Japan - Yen","¥"),
            new Data(18, "Korea (South) - Won","₩"),
            new Data(2, "Laos - Kip", "₭"),
            new Data(438, "Philippines - Peso","₱"),
            new Data(381, "Russia - Ruble","₽"),
            new Data(672, "Thailand - Baht","฿"),
            new Data(29143, "United Kingdom - Pound","£"),
            new Data(23191,"United States - Dollar","$"),
            new Data(1,"Vietnam - Dong","₫")
    };

    private final int len = dataList.length;

    public ArrayData() {

    }

    public double[] getArrayRate() {

        double[] rates = new double[len];

        AtomicInteger i = new AtomicInteger();

        for (i.set(0); i.get() < len; i.getAndIncrement()) {

            rates[i.get()] = dataList[i.get()].getRate();
        }

        return rates;
    }

    public String[] getArrayText() {

        String[] texts = new String[len];

        AtomicInteger i = new AtomicInteger();

        for (i.set(0); i.get() < len; i.getAndIncrement()) {

            texts[i.get()] = dataList[i.get()].getText();
        }

        return texts;
    }

    public String[] getArrayIcon() {

        String[] icons = new String[len];

        AtomicInteger i = new AtomicInteger();

        for (i.set(0); i.get() < len; i.getAndIncrement()) {

            icons[i.get()] = dataList[i.get()].getIcon();
        }

        return icons;
    }
}
