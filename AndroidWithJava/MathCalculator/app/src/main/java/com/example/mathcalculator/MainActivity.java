package com.example.mathcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result,solution;
    Button buttonC, buttonOpen, buttonClose;
    Button buttonDivide, buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    Button button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    Button buttonAC,buttonDot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);
        assignId(buttonC, R.id.buttonC);
        assignId(buttonOpen, R.id.buttonOpen);
        assignId(buttonClose, R.id.buttonClose);
        assignId(buttonDivide, R.id.buttonDivide);
        assignId(buttonMultiply, R.id.buttonMultiply);
        assignId(buttonPlus, R.id.buttonPlus);
        assignId(buttonMinus, R.id.buttonMinus);
        assignId(buttonEquals, R.id.buttonEquals);
        assignId(button0, R.id.button0);
        assignId(button1, R.id.button1);
        assignId(button2, R.id.button2);
        assignId(button3, R.id.button3);
        assignId(button4, R.id.button4);
        assignId(button5, R.id.button5);
        assignId(button6, R.id.button6);
        assignId(button7, R.id.button7);
        assignId(button8, R.id.button8);
        assignId(button9, R.id.button9);
        assignId(buttonAC, R.id.buttonAC);
        assignId(buttonDot, R.id.buttonDot);

    }
    void assignId(Button button, int id){
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Button button = (Button)view;
        String buttonText = button.getText().toString();
        //solution.setText(buttonText);
        String calculate = solution.getText().toString();
        if(buttonText.equals("AC")){
            solution.setText("");
            result.setText("");
            return;
        }
        if(buttonText.equals("C")){
            calculate = calculate.substring(0, calculate.length() - 1);
        }else{
            calculate += buttonText;
        }
        solution.setText(calculate);
        String finalResult = getResult(calculate);

        if(!finalResult.equals("Err")){
            if(buttonText.equals("=")){
                solution.setText("");
            }
            result.setText(finalResult);
        }


    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch(Exception e){
            return "Err";
        }
    }


}