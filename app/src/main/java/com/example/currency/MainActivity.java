package com.example.currency;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final static ArrayData dataArray = new ArrayData();
    private final static Element[] elements = new Element[]{
            new Element(9, dataArray.calRate(9, 10), dataArray.getIcon(9)),
            new Element(10, dataArray.calRate(10, 9), dataArray.getIcon(10))
    };
    private static ArrayAdapter<String> adapter;
    private static int idFocus = 0, idNonFocus = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item, dataArray.getTexts());
        }
        setTextView(findViewById(R.id.text_0), findViewById(R.id.icon_0), 0);
        setTextView(findViewById(R.id.text_1), findViewById(R.id.icon_1), 1);
        setSpinner(findViewById(R.id.spinner_0), 0);
        setSpinner(findViewById(R.id.spinner_1), 1);
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

    private void setTextView(TextView textView, TextView iconView, int id) {
        elements[id].setView(textView, iconView);
        elements[id].getTextView().setOnClickListener(v -> {
            if (id != idFocus) {
                idFocus = id;
                idNonFocus = 1 - id;
                setBoldColor();
            }
        });
    }

    private void setSpinner(@NonNull Spinner spinner, int id) {
        spinner.setAdapter(adapter);
        spinner.setSelection(elements[id].getIndex());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> av, View v, int index, long i) {
                if (index != elements[id].getIndex()) {
                    elements[id].replaceIcon(index, dataArray.getIcon(index));
                    elements[id].setRate(dataArray.calRate(index, elements[1 - id].getIndex()));
                    elements[1 - id].setRate(dataArray.calRate(elements[1 - id].getIndex(), index));
                    elements[1 - id].setValue(elements[id].calValue());
                    setText();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> av) {

            }
        });
    }

    private void onClickZero() {
        if (elements[idFocus].appendZero()) {
            elements[idNonFocus].setValue(elements[idFocus].calValue());
            setText();
        }
    }

    private void onClickNumber(int number) {
        elements[idFocus].appendValue(number);
        elements[idNonFocus].setValue(elements[idFocus].calValue());
        setText();
    }

    private void onClickBS() {
        if (elements[idFocus].clearAChar()) {
            elements[idNonFocus].setValue(elements[idFocus].calValue());
            setText();
        }
    }

    private void onClickCE() {
        elements[idFocus].clear();
        elements[1].clear();
        setText();
    }

    private void onClickDot() {
        if (elements[idFocus].appendDot()) {
            setText();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setText();
        elements[idFocus].displayIcon();
        elements[idNonFocus].displayIcon();
        setBoldColor();
    }

    private void setBoldColor() {
        elements[idFocus].setBoldColor(true);
        elements[idNonFocus].setBoldColor(false);
    }

    private void setText() {
        elements[idFocus].displayText();
        elements[idNonFocus].displayText();
    }
}