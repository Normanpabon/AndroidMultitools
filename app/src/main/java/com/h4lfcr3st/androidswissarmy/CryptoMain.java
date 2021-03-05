package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public class CryptoMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_main);
    }

    public void ToEncryption(View view) {
        Intent goToEncryption = new Intent(this, EncryptMessage.class);
        startActivity(goToEncryption);

    }
    public void ToDecryption(View view){
        Intent GoToDecryption = new Intent(this, DecryptMessage.class);
        startActivity(GoToDecryption);
    }
}
