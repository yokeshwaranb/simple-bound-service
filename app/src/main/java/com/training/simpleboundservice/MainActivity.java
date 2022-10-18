package com.training.simpleboundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Yoke.MainActivity";

    MyBoundService myBoundService;
    boolean mBound = false;

    Button btnGenRandNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGenRandNum = findViewById(R.id.btnGenRandNum);
        btnGenRandNum.setOnClickListener(v -> getRandomNumber());
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to Service
        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    public void getRandomNumber() {
        if(mBound) {
            int randomNumber = myBoundService.getRandomNumber();
            Log.e(TAG, "Generated random number is " + randomNumber);
        }
        else {
            Log.e(TAG, "Not bound to service");
        }
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Because we have bound to an explicit
            // service that is running in our own process, we can
            // cast its IBinder to a concrete class and directly access it.
            MyBoundService.MyLocalBinder binder = (MyBoundService.MyLocalBinder) service;
            myBoundService = binder.getService();
            mBound = true;
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            Log.e(TAG, "onServiceDisconnected");
            mBound = false;
        }
    };
}