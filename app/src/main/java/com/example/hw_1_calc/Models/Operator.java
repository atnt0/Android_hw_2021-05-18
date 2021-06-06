package com.example.hw_1_calc.Models;

public enum Operator {
    PLUS("+"),
    MINUS("-"),
    DIVISION("/"),
    MULTIPLY("x");

    private String str;

    Operator(String _str) {
        str = _str;
    }

    public String getString() {
        return str;
    }
}
