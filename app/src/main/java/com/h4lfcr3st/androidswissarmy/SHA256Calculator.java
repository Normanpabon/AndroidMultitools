package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SHA256Calculator extends AppCompatActivity {
    private EditText EDTxtToSha256;
    private EditText Sha256OutPut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sha256_calculator);
        EDTxtToSha256 = findViewById(R.id.EDtextToSHA256);
        Sha256OutPut = findViewById(R.id.EDSha256Output);
    }

    public void BtnCalculateSha256(View view){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String text = EDTxtToSha256.getText().toString();

            // Change this to UTF-16 if needed
            md.update(text.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();

            String hex = String.format("%064x", new BigInteger(1, digest));
            Sha256OutPut.setText(hex);
        }catch (Exception e){
            Toast.makeText(this, "A error has ocurred", Toast.LENGTH_SHORT).show();
        }


    }

}
