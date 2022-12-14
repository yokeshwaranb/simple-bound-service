package com.training.simpleboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyBoundService extends Service {

    // Binder given to clients
    private final IBinder binder = new MyLocalBinder();

    // Random number generator
    private final Random mGenerator = new Random();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    @Override
    public IBinder onBind(Intent intent) {
        // Return this instance of LocalService so clients can call public methods
        return binder;
    }

    public class MyLocalBinder extends Binder {
        MyBoundService getService(){
            return MyBoundService.this;
        }
    }

    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}