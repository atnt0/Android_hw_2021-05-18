package com.example.hw_1_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Double operandFirst;
    private Double operandSecond;

    private Operator operand;

    private Boolean isReadyToCalculate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    enum Operator {
        PLUS,
        MINUS,
        DIVISION,
        MULTIPLY
    }




}