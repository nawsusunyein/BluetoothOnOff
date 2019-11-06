package com.example.bluetoothonoff;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BluetoothAdapter bAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void onBluetoothSetting(View view){
        if(bAdapter == null){
            Toast.makeText(this,"Bluetooth is not supported",Toast.LENGTH_LONG).show();
        }else{
            if(!bAdapter.isEnabled()){
                startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                Toast.makeText(this,"Bluetooth is turned on",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void offBluetoothSetting(View view){
        bAdapter.disable();
        Toast.makeText(this,"Bluetooth is turned off",Toast.LENGTH_LONG).show();

    }

    public void onBluetoothDiscoverableSetting(View view){
        if(!bAdapter.isDiscovering()){
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE),1);
            Toast.makeText(this,"Making discoverable",Toast.LENGTH_LONG).show();
        }
    }

    public void goToPairDeviceScreen(View view){
        Intent pairDeviceIntent = new Intent(MainActivity.this, PairListActivity.class);
        startActivity(pairDeviceIntent);
    }
}
