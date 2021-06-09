package com.example.hw_1_calc.Models;

import android.util.Log;

public class Operand {

    private Integer valueInteger;
    private Double valueDouble;

    private Boolean isDouble = false;
    private Boolean isDotPreviousInsert = true; // if was dot is setted

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

    public Boolean getAfterDot() {
        return isDouble ? isDotPreviousInsert : false;
    }

    public void setAfterDot(Boolean isDotPrevious) {
        if( isDouble )
            isDotPreviousInsert = isDotPrevious;
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
        if( isDouble ) {
            Log.d("DEBUG", "0: "+ getAfterDot());

            String tmpStr1 = getValueDouble().toString();
            Integer tmpLen = tmpStr1.length();
            String tmpStr2 = tmpStr1.substring(0, tmpLen - 1);

            if( isDotPreviousInsert ) {
//                Log.d("DEBUG", "--- *1: "+ getValueDouble());
//                Log.d("DEBUG", "--- *2: "+ getValueDouble().toString());

//                Log.d("DEBUG", "--- *3: "+ getValueDouble().toString().length());
//                Log.d("DEBUG", "--- *4: "+ (getValueDouble().toString().length() - 1));

                Log.d("DEBUG", "5-1: "+ tmpStr2);

                return tmpStr2;
            }
            else {
                Log.d("DEBUG", "5-2: "+ tmpStr2);

                return valueDouble.toString();
            }
        }
        else {
            return valueInteger.toString();
        }
    }


    public static Operand getNewOperand(Operand source, Integer addDigit) {

        String leftPart = source.getString();
        String tmp1 = leftPart + addDigit;
        Double tmp2 = Double.parseDouble(tmp1);

//        Log.d("DEBUG", "leftPart: "+ leftPart);
//        Log.d("DEBUG", "digit: "+ addDigit);
//        Log.d("DEBUG", "leftPart+digit: "+ tmp1);
//        Log.d("DEBUG", "leftPart+digit_double: "+ tmp2);

        return source.isDouble() ? new Operand( Double.parseDouble(tmp1) ) : new Operand( Integer.parseInt(tmp1) );
    }



}
