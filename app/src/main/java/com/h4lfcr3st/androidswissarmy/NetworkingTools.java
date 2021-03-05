package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NetworkingTools extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking_tools);

    }

    public void BtnPingUtility(View view){
        Intent PingUtility = new Intent(this, com.h4lfcr3st.androidswissarmy.PingUtility.class);
        startActivity(PingUtility);
    }
    public void BtnArpDevices(View view){
        Intent ARPDevices = new Intent(this, com.h4lfcr3st.androidswissarmy.ARPDevices.class);
        startActivity(ARPDevices);

    }
    public void BtnMacVendors(View view){
        Intent MacVendorUtility = new Intent(this, MacVendorFinder.class);
        startActivity(MacVendorUtility);

    }

}
