package com.example.convertcurrency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Currency[] currencies;
    Float input,inRate;
    Float output,outRate;
    private Spinner spFrom;
    private Spinner spTo;
    private EditText et_input;
    private EditText et_output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initCurrencies();
        this.spFrom=(Spinner) findViewById(R.id.from);
        this.spTo=(Spinner) findViewById(R.id.to) ;
        this.et_input=(EditText) findViewById(R.id.input);
        this.et_output=(EditText)findViewById(R.id.output);
        this.et_input.setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_FLAG_DECIMAL);
        this.et_output.setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_FLAG_DECIMAL);
        //spinner
        ArrayAdapter<Currency> adapter=new ArrayAdapter<Currency>(
                this,
                android.R.layout.simple_spinner_item,
                this.currencies);
        this.spFrom.setAdapter(adapter);
        this.spTo.setAdapter(adapter);
        this.spFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                inRate=currencies[position].getRate();
                convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                inRate=1f;
            }
        });
        this.spTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                outRate=currencies[position].getRate();
                convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                outRate=1f;
            }
        });
        //edittext
        this.et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String in=et_input.getText().toString();
                if (in.length()==0)
                    input=0f;
                else
                    input=Float.parseFloat(in);
                convert();
            }
        });
    /*    this.et_output.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String out=et_output.getText().toString();
                if (out.length()==0)
                    output=0f;
                else
                    output=Float.parseFloat(out);
                input=output*(inRate/outRate);
                et_input.setText(String.format("%.2f",input));
            }
        });*/
    }
    private void initCurrencies(){
        currencies=new Currency[5];
        currencies[0]=new Currency("VND",1f/23180.34f);
        currencies[1]=new Currency("USD",1.0f);
        currencies[2]=new Currency("JPY",1f/104.5202f);
        currencies[3]=new Currency("RUB",1f/77.12f);
        currencies[4]=new Currency("EUR",1f/0.85f);
        this.input=0f;
        this.output=0f;
        this.inRate=1f;
        this.outRate=1f;
    }
    private void convert(){
        this.output=this.input*(inRate/outRate);
        this.et_output.setText(String.format("%.5f",this.output));
    }
}