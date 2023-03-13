package com.antutu.ABenchMark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button startButton;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startBtn);
        startButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //start 16 threads and do high loading task
        int arraySize = 100000;
        long runTimes = 100000;
        for (int i = 0; i <= 15; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < runTimes; j++){
                        runTask(arraySize);
                    }
                }
            }).start();
        }
    }

    private void runTask(int size) {
        Log.i(TAG,"runTask called!");
        int[] array = new int[size];
        Random random=new Random();
        for (int i=0;i < size;++i){
            int num = random.nextInt();
            array[i] = num;
        }

//        Log.i(TAG,"排序前：");
//        for (int i = 0;i<array.length;i++){
//            Log.i(TAG,"array["+i+"] = "+array[i]);
//        }

        int temp = 0;
        for (int i = 0; i<array.length-1;i++){
            for (int j = 0; j<array.length-1-i;j++){
                if (array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

//        Log.i(TAG,"排序后：");
//        for (int i = 0;i<array.length;i++){
//            Log.i(TAG,"array["+i+"] = "+array[i]);
//        }
    }
}