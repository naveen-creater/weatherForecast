package com.example.weatherforecast.Reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SystemReciverSample extends BroadcastReceiver {
    public static final String ACTIONS_ON_RECEIVE = "android.intent.action.BOOT_COMPLETED";
    private static final String TAG = "AutoStart";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ACTIONS_ON_RECEIVE.equals(action)) {
            Toast.makeText(context, "Boot complete Reciver...", Toast.LENGTH_SHORT).show();
        }

    }
}
