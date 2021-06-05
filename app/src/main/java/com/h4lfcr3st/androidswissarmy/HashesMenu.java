package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HashesMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashes_menu);
    }

    public void BtnHashMd5(View view){
        Intent HashMd5 = new Intent(this, MD5Calculator.class);
        startActivity(HashMd5);
    }
    public void BtnHashSha1(View view){
        Intent HashSha1 = new Intent(this, SHA1Calculator.class);
        startActivity(HashSha1);
    }
    public void BtnHashSha256(View view){
        Intent HashSha256 = new Intent(this, SHA256Calculator.class);
        startActivity(HashSha256);
    }

    public void BtnSafePassword(View view){
        Intent safePassword = new Intent(this, SafePassword.class);
        startActivity(safePassword);
    }
}
