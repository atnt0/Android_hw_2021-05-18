package com.example.hw_1_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hw_1_calc.Models.Operand;
import com.example.hw_1_calc.Models.Operator;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private Operand operandFirst;
    private Operand operandSecond;
    private Operator operator;

    /**
     * 0: not set   - first operand
     *    not set   - operator
     *    not set   - second operand
     * 1:     set   - first operand
     *    not set   - operator
     *    not set   - second operand
     * 2:     set   - first operand
     *        set   - operator
     *    not set   - second operand
     * 3:     set   - first operand
     *        set   - operator
     *        set   - second operand
     */
    private Integer stageCalculate = 0;


    private Boolean isReadyToCalculate = false;

    private TextView tv_expression;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_expression = findViewById(R.id.tv_expression);
        tv_result = findViewById(R.id.tv_result);

    }


    public void btnClearOnClick(View view) {
        Integer btnId = view.getId();
        String digits;

        // здесь проверим нажатие только на указанные кнопки
        switch (btnId) {
            case R.id.btn_clear:
            {
                operandFirst = null;
                operandSecond = null;
                operator = null;
                stageCalculate = 0;

                tv_expression.setText( "" );
                tv_result.setText( "" );
            }
        }
    }

    public void btnNumberOnClick(View view) {
        Integer btnId = view.getId();
        Integer digit;

        // check pressing only the specified buttons
        switch (btnId) {
            case R.id.btn_number_0:
            case R.id.btn_number_1:
            case R.id.btn_number_2:
            case R.id.btn_number_3:
            case R.id.btn_number_4:
            case R.id.btn_number_5:
            case R.id.btn_number_6:
            case R.id.btn_number_7:
            case R.id.btn_number_8:
            case R.id.btn_number_9:
                // set the value itself so as not to mess with a separate method :)
                switch (btnId) {
                    case R.id.btn_number_0:
                        digit = 0;
                        break;
                    case R.id.btn_number_1:
                        digit = 1;
                        break;
                    case R.id.btn_number_2:
                        digit = 2;
                        break;
                    case R.id.btn_number_3:
                        digit = 3;
                        break;
                    case R.id.btn_number_4:
                        digit = 4;
                        break;
                    case R.id.btn_number_5:
                        digit = 5;
                        break;
                    case R.id.btn_number_6:
                        digit = 6;
                        break;
                    case R.id.btn_number_7:
                        digit = 7;
                        break;
                    case R.id.btn_number_8:
                        digit = 8;
                        break;
                    case R.id.btn_number_9:
                        digit = 9;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + btnId);
                }


                if(stageCalculate == 0 || stageCalculate == 1 ) {
                    if (operandFirst == null) operandFirst = new Operand( 0 );

                    String leftPart = operandFirst.getString();

                    operandFirst = operandFirst.isDouble() ? new Operand( Double.parseDouble(leftPart + digit) ) : new Operand( Integer.parseInt(leftPart + digit) );

                    if( stageCalculate == 0 ) stageCalculate++;

                    showCurrentExpression();
                }
                else
                if(stageCalculate == 2 || stageCalculate == 3) {
                    if (operandSecond == null) operandSecond = new Operand( 0 );

                    String leftPart = operandSecond.getString();

                    operandSecond = operandSecond.isDouble() ? new Operand( Double.parseDouble(leftPart + digit) ) : new Operand( Integer.parseInt(leftPart + digit) );

                    if( stageCalculate == 2 ) stageCalculate++;

                    showCurrentExpression();
                }

                break;
            default:
        }
    }

    // stage 0 - nothing, 1 - only first, 2 - first and second was setted

    public void btnDotOnClick(View view) {
        Integer btnId = view.getId();

        switch (btnId) {
            case R.id.btn_dot: {
                if(stageCalculate == 0 || stageCalculate == 1 ) {
                    if (operandFirst == null) operandFirst = new Operand( 0 );
                    //operandFirst = operandFirst.isDouble() ? operandFirst.getValueDouble() : operandFirst.getValueInteger();
                    operandFirst.setToDouble();

                    showCurrentExpression();

                    //if(stageCalculate <= 2) stageCalculate++;
                }
                else
                if(stageCalculate == 2 || stageCalculate == 3) {
                    if (operandFirst == null) operandFirst = new Operand( 0 );
                    //operandSecond = operandSecond.doubleValue();
                    operandSecond.setToDouble();

                    showCurrentExpression();

                    //if(stageCalculate <= 2) stageCalculate++;
                }
            }
        }
    }


    private void showCurrentExpression() {
        StringBuilder sb = new StringBuilder();

        switch (stageCalculate) {
            case 0:
//                Log.d("DEBUG", "stageCalculate = "+ stageCalculate +"; case 0");
                sb.append(""+ operandFirst.getString() +" ");

                tv_expression.setText( sb );
                break;
            case 1:
                sb.append(""+ operandFirst.getString() +" ");

                tv_expression.setText( sb );
                break;
            case 2:
                sb.append(""+ operandFirst.getString() +" ");
                sb.append(""+ operator.getString() +" ");

                tv_expression.setText( sb.toString() );
                break;
            case 3:
                sb.append(""+ operandFirst.getString() +" ");
                sb.append(""+ operator.getString() +" ");
                sb.append(""+ operandSecond.getString() +" ");

                tv_expression.setText( sb.toString() );
                break;
        }

    }




    public void btnOperatorOnClick(View view) {
        Integer btnId = view.getId();

        switch (btnId) {
            case R.id.btn_operand_plus:
            case R.id.btn_operand_minus:
            case R.id.btn_operand_division:
            case R.id.btn_operand_multiplication:
            {
                //if ( stageCalculate <= 2) {
                    // stageCalculate == 0 ||
                    if(stageCalculate == 1) { // only after set first operand
                        // set the value itself so as not to mess with a separate method :)
                        switch (btnId) {
                            case R.id.btn_operand_plus:
                                operator = Operator.PLUS;
                                break;
                            case R.id.btn_operand_minus:
                                operator = Operator.MINUS;
                                break;
                            case R.id.btn_operand_division:
                                operator = Operator.DIVISION;
                                break;
                            case R.id.btn_operand_multiplication:
                                operator = Operator.MULTIPLY;
                                break;
                            default:
                                operator = Operator.PLUS;
                        }
                        //Log.d("DEBUG", "now stageCalculate is "+ stageCalculate);
                        stageCalculate += 1;

                        showCurrentExpression();
                    }
                //}
            }
        }
    }


    private void showCurrentResult() throws Exception {
        StringBuilder sb = new StringBuilder();

        switch (stageCalculate) {
            case 3:
            {

                if( operandFirst.isDouble() || operandSecond.isDouble() ) {
                    Double tmpResult = operandFirst.getValueDouble() + operandSecond.getValueDouble();

                    switch (operator) {
                        case MINUS:
                            tmpResult = operandFirst.getValueDouble() - operandSecond.getValueDouble();
                            break;
                        case DIVISION:
                            if( operandSecond.getValueDouble() == 0 || operandSecond.getValueInteger() == 0 )
                                throw new Exception("Error: Divide by zero");

                            tmpResult = operandFirst.getValueDouble() / operandSecond.getValueDouble();

                            break;
                        case MULTIPLY:
                            tmpResult = operandFirst.getValueDouble() * operandSecond.getValueDouble();
                            break;
                        case PLUS:
                        default:
                            tmpResult = operandFirst.getValueDouble() + operandSecond.getValueDouble();
                            break;
                    }

                    tv_result.setText( "= "+ tmpResult +"" );
                }
                else {
                    Integer tmpResult;

                    switch (operator) {
                        case MINUS:
                            tmpResult = operandFirst.getValueInteger() - operandSecond.getValueInteger();
                            break;
                        case DIVISION:
                            if( operandSecond.getValueInteger() == 0 )
                                throw new Exception("Error: Divide by zero");

                            tmpResult = operandFirst.getValueInteger() / operandSecond.getValueInteger();
                            break;
                        case MULTIPLY:
                            tmpResult = operandFirst.getValueInteger() * operandSecond.getValueInteger();
                            break;
                        case PLUS:
                        default:
                            tmpResult = operandFirst.getValueInteger() + operandSecond.getValueInteger();
                            break;
                    }

                    tv_result.setText( "= "+ tmpResult +"" );
                }


            }
        }

    }


    public void btnResultOnClick(View view) throws Exception {
        Integer btnId = view.getId();

        switch (btnId) {
            case R.id.btn_result:
            {
                if(stageCalculate >= 3) {
                    showCurrentResult();


                }
            }
        }
    }






}