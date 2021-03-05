package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA1Calculator extends AppCompatActivity {
    private EditText EDtxtToSha1;
    private EditText EDSha1Output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sha1_calculator);
        EDtxtToSha1 = findViewById(R.id.EDtextToSHA256);
        EDSha1Output = findViewById(R.id.EDSha256Output);
    }

    public void btnCalulateSha1(View view){

        String value = EDtxtToSha1.getText().toString();

        String sha1 = "";

        // With the java libraries
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(value.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e){
            EDSha1Output.setText("A error has ocurred");
            }

        EDSha1Output.setText(sha1);

    }
}
