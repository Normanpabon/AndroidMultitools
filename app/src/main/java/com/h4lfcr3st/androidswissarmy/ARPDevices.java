package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ARPDevices extends AppCompatActivity {

    private EditText EDARP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arpdevices);

        EDARP = findViewById(R.id.EdARPDevices);

    }

    public void ShowDevicesARP(View view){
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("/proc/net/arp")));
            String total = "";
            String line;
            while((line = br.readLine()) != null) {
                total += line + "\n";
            }
            EDARP.setText(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
