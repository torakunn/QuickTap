package com.example.test;

import android.os.Handler;
import android.widget.TextView;



public class MyTimer{
    private long start;
    private long end;
    private boolean finish;
    private TextView v;
    private Handler handler;
    public MyTimer(TextView v){
        this.start = System.currentTimeMillis();
        this.finish = false;
        this.v = v;
        this.handler = new Handler();
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            end = System.currentTimeMillis();
            String text = String.valueOf((end-start)/1000.0)+"秒";
            v.setText(text);
            if(!finish) handler.postDelayed(runnable,10);
        }
    };
    public void run(){

            handler.post(runnable);
    }

    public long finish(){
        this.finish = true;
        return end;
    }
}
