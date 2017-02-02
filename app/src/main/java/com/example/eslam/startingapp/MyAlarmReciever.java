package com.example.eslam.startingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by islam on 01/02/17.
 */

public class MyAlarmReciever extends BroadcastReceiver {
    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "com.example.servicesdemo.alarm";

    // Triggered by the Alarm periodically (starts the service to run task)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MyTestService.class);
        context.startService(i);
    }
}
