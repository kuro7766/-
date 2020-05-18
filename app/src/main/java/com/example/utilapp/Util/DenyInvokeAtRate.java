package com.example.utilapp.Util;

public class DenyInvokeAtRate {
    private long millicons;
    private int gap;

    public DenyInvokeAtRate(int gap) {
        this.gap = gap;
        millicons=System.currentTimeMillis();
    }

    public void invoke(Runnable runnable){
        if(System.currentTimeMillis()-millicons>gap){
            runnable.run();
            millicons=System.currentTimeMillis();
        }
    }
}
