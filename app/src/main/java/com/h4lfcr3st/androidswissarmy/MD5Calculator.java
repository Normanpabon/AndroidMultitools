package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5Calculator extends AppCompatActivity {
    private EditText TxtToMd5;
    private EditText Md5Output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md5_calculator);

        TxtToMd5 = findViewById(R.id.EDtextToMD5);
        Md5Output = findViewById(R.id.TVMD5Output);
    }

    public void BtnCalculateMD5(View view){
        //String toCode = TxtToMd5.getText().toString();
        String plaintext = TxtToMd5.getText().toString();

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plaintext.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            Md5Output.setText(hashtext);
        }catch (Exception e){
            Toast.makeText(this, "A error has ocurred", Toast.LENGTH_SHORT).show();
        }

    }
}
