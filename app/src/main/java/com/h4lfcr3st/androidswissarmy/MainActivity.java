package com.h4lfcr3st.androidswissarmy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void Encoders(View view){
        Intent enterEncoder = new Intent(this, Encoders.class);
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        startActivity(enterEncoder); //ingresa al activity encoders

    }
    public void Hashes(View view){
        Intent enterHashes = new Intent(this, HashesMenu.class);
        startActivity(enterHashes); //ingresa al activity de hashes

    }
    public void NetworkTools(View view){
        Intent enterNetworking = new Intent(this, NetworkingTools.class);
        startActivity(enterNetworking);
    }
    public void CryptoTools(View view){
        Intent enterCrypto = new Intent(this, CryptoMain.class);
        startActivity(enterCrypto);

    }
    public void SecurePassword(View view){
        Intent enterSecure = new Intent(this, SafePassword.class);
        startActivity(enterSecure);
    }

}