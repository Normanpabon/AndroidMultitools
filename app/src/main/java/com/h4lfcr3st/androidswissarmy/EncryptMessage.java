package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public class EncryptMessage extends AppCompatActivity {

    private EditText edPass, edTxtToEnc, edEnc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_message);

        edPass = findViewById(R.id.edEncryptPassword);
        edTxtToEnc = findViewById(R.id.txtToDecrypt);
        edEnc = findViewById(R.id.DecryptedEd);
    }

    public void btnEncrypt(View view){
        String pass = edPass.getText().toString();
        String toEncode = edTxtToEnc.getText().toString();
        if(pass.isEmpty()){
            Toast.makeText(this, "Error, enter a password to encrypt", Toast.LENGTH_SHORT).show();
        }else {
            try {
                AesCbcWithIntegrity.SecretKeys keys = AesCbcWithIntegrity.generateKey();
                keys = AesCbcWithIntegrity.generateKeyFromPassword(pass, pass);

                AesCbcWithIntegrity.CipherTextIvMac cipherTextIvMac = AesCbcWithIntegrity.encrypt(toEncode, keys);

                edEnc.setText(cipherTextIvMac.toString());
                edPass.setText("");

            } catch (GeneralSecurityException e) {
                e.getStackTrace();
                Toast.makeText(this, "A security Exception has ocurred", Toast.LENGTH_SHORT).show();
            } catch (UnsupportedEncodingException e) {
                e.getCause();
                Toast.makeText(this, "UnsupportedEncodingException has ocurred", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
