package com.example.myfirebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvToken;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvToken = findViewById(R.id.tvToken);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction() != null && intent.getAction().equals(MyFirebaseMessagingService.MSG_BROADCAST)) {
                    tvToken.setText("New Message: " + intent.getStringExtra(MyFirebaseMessagingService.MSG_READING));
                } else {
                    tvToken.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(MyFirebaseMessagingService.TOKEN_BROADCAST);
        filter.addAction(MyFirebaseMessagingService.MSG_BROADCAST);
        registerReceiver(broadcastReceiver, filter);





        if (SharedPrefManager.getInstance(this).getToken() != null) {
            tvToken.setText(SharedPrefManager.getInstance(this).getToken());
            Log.d("myToken", SharedPrefManager.getInstance(this).getToken());
        }
    }
}
