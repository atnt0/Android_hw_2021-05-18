package com.example.hw_1_calc.Models;

public class Operand {

    private Integer valueInteger;
    private Double valueDouble;

    private Boolean isDouble = false;

    public Operand(Integer valueInteger) {
        this.valueInteger = valueInteger;
        isDouble = false;
    }

    public Operand(Double valueDouble) {
        this.valueDouble = valueDouble;
        isDouble = true;
    }

    public Boolean isDouble() {
        return isDouble;
    }

    public void setToDouble() {
        if( !isDouble ) {
            isDouble = true;
            valueDouble = valueInteger.doubleValue();
        }
    }

    public Integer getValueInteger() {
        if( ! isDouble ) {
            return valueInteger;
        }
        else {
            return valueDouble.intValue();
        }
    }

    public Double getValueDouble() {
        if( isDouble ) {
            return valueDouble;
        }
        else{
            return valueInteger.doubleValue();
        }
    }

    public String getString() {
        if( ! isDouble ) {

            return valueInteger.toString();
        }
        else {
            return valueDouble.toString();
        }
    }


}
