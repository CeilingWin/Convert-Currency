package com.example.convertcurrency;

import androidx.annotation.NonNull;

public class Currency {
    private String name;
    private float rate;
    Currency(String name,float rate){
        this.name=name;
        this.rate=rate;
    }
    public String getName(){
        return this.name;
    }
    public float getRate(){
        return this.rate;
    }
    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
