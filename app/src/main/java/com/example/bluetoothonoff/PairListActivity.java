package com.example.bluetoothonoff;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class PairListActivity extends AppCompatActivity {

    ListView lstView;
    ArrayAdapter lstAdapter ;
    BluetoothAdapter bAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_list);
        bAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void showPairedDevices(View view){
        if(bAdapter == null){
            Toast.makeText(this,"Bluetooth not supported",Toast.LENGTH_LONG).show();
        }else{
            Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
            ArrayList list = new ArrayList();
            if(pairedDevices.size()>0){
                for(BluetoothDevice device: pairedDevices){
                    String devicename = device.getName();
                    String macAddress = device.getAddress();
                    list.add("Name: "+devicename+"MAC Address: "+macAddress);
                }
                lstView = (ListView) findViewById(R.id.ls_pairedDevices);
                lstAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                lstView.setAdapter(lstAdapter);
            }
        }
    }
}
