package com.example.currency;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final static ArrayData arrayData = new ArrayData();
    private final static double[] rates = arrayData.getArrayRate();
    private final static String[] texts = arrayData.getArrayText();
    private final static String[] icons = arrayData.getArrayIcon();
    private final static TextView[] iconViews = new TextView[2];
    private final static TextView[] textViews = new TextView[2];
    private final static Element[] elements = new Element[]{
            new Element(9, rates[9]/rates[10], icons[9]),
            new Element(10, rates[10]/rates[9], icons[10])
    };
    private final static int BLACK = 0xFF000000, LIGHT_BLACK = 0x80000000;

    private static int idFocus = 0, idNonFocus = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViews[0] = findViewById(R.id.text_0);
        textViews[1] = findViewById(R.id.text_1);
        iconViews[0] = findViewById(R.id.icon_0);
        iconViews[1] = findViewById(R.id.icon_1);
        textViews[0].setOnClickListener(v -> onClickTextView(0));
        textViews[1].setOnClickListener(v -> onClickTextView(1));

        Spinner spinner0 = findViewById(R.id.spinner_0);
        Spinner spinner1 = findViewById(R.id.spinner_1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, texts);
        spinner0.setAdapter(adapter);
        spinner1.setAdapter(adapter);
        spinner0.setSelection(elements[0].getIndex());
        spinner1.setSelection(elements[1].getIndex());
        spinner0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onClickSpinner(0, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onClickSpinner(1, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.button_0).setOnClickListener(v-> onClickZero());
        findViewById(R.id.button_1).setOnClickListener(v-> onClickNumber(1));
        findViewById(R.id.button_2).setOnClickListener(v-> onClickNumber(2));
        findViewById(R.id.button_3).setOnClickListener(v-> onClickNumber(3));
        findViewById(R.id.button_4).setOnClickListener(v-> onClickNumber(4));
        findViewById(R.id.button_5).setOnClickListener(v-> onClickNumber(5));
        findViewById(R.id.button_6).setOnClickListener(v-> onClickNumber(6));
        findViewById(R.id.button_7).setOnClickListener(v-> onClickNumber(7));
        findViewById(R.id.button_8).setOnClickListener(v-> onClickNumber(8));
        findViewById(R.id.button_9).setOnClickListener(v-> onClickNumber(9));
        findViewById(R.id.button_ce).setOnClickListener(v-> onClickCE());
        findViewById(R.id.button_bs).setOnClickListener(v -> onClickBS());
        findViewById(R.id.button_dot).setOnClickListener(v-> onClickDot());
    }

    private void onClickZero() {
        if (!elements[idFocus].getText().equals("0")) {
            elements[idFocus].appendValue(0);
            elements[idNonFocus].setValue(elements[idFocus].getRate() * elements[idFocus].getValue());
            setText();
        }
    }

    private void onClickTextView(int id) {
        if (id != idFocus) {
            idFocus = id;
            idNonFocus = 1 - id;
            textViews[idFocus].setTextColor(BLACK);
            textViews[idNonFocus].setTextColor(LIGHT_BLACK);
        }
    }

    private void onClickNumber(int number) {
        elements[idFocus].appendValue(number);
        elements[idNonFocus].setValue(elements[idFocus].getRate() * elements[idFocus].getValue());
        setText();
    }

    private void onClickBS() {
        if (!elements[idFocus].getText().equals("0")) {
            elements[idFocus].clearAChar();
            elements[idNonFocus].setValue(elements[idFocus].getRate() * elements[idFocus].getValue());
            setText();
        }
    }

    private void onClickCE() {
        elements[0].clear();
        elements[1].clear();
        setText();
    }

    private void onClickDot() {
        if (!elements[idFocus].getHasDot()) {
            elements[idFocus].appendDot();
            setText();
        }
    }

    private void onClickSpinner(int id, int index) {
        if (index != elements[id].getIndex()) {
            elements[id].setIndex(index);
            elements[id].setIcon(icons[index]);
            elements[id].setRate(rates[index] / rates[elements[1 - id].getIndex()]);
            elements[1 - id].setRate(rates[elements[1 - id].getIndex()] / rates[index]);
            elements[1 - id].setValue(elements[id].getRate() * elements[id].getValue());
            iconViews[id].setText(icons[index]);
            setText();
        }
    }

    private void setText() {
        textViews[idFocus].setText(elements[idFocus].getText());
        textViews[idNonFocus].setText(elements[idNonFocus].getText());
    }

    @Override
    protected void onResume() {
        super.onResume();
        textViews[idFocus].setText(elements[idFocus].getText());
        textViews[idNonFocus].setText(elements[idNonFocus].getText());
        iconViews[idFocus].setText(elements[idFocus].getIcon());
        iconViews[idNonFocus].setText(elements[idNonFocus].getIcon());
        textViews[idFocus].setTextColor(BLACK);
        textViews[idNonFocus].setTextColor(LIGHT_BLACK);
    }
}