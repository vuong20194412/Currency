package com.example.currency;

import java.util.ArrayList;
import java.util.List;

public class ArrayData {

    private final Data[] dataArray = new Data[] {
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

    public double calRate(int i, int j) {
        return dataArray[i].getRate()/dataArray[j].getRate();
    }

    public List<String> getTexts() {
        List<String> texts = new ArrayList<>();
        for (Data data: dataArray) {
            texts.add(data.getText());
        }
        return texts;
    }

    public String getIcon(int i) {
        return dataArray[i].getIcon();
    }
}
