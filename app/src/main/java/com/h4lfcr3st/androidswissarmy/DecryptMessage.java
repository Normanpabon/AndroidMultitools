package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;

public class DecryptMessage extends AppCompatActivity {

    private EditText EdPasswd, txtToDecrypt, DecryptedTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt_message);

        EdPasswd = findViewById(R.id.edEncryptPassword);
        txtToDecrypt = findViewById(R.id.txtToDecrypt);
        DecryptedTxt = findViewById(R.id.DecryptedEd);

    }

    public void btnDecrypt(View view){
        String userPasswd = EdPasswd.getText().toString();
        String EncryptedTx = txtToDecrypt.getText().toString();
        if(userPasswd.isEmpty() || EncryptedTx.isEmpty()){
            Toast.makeText(this, "Check passwd and InputText", Toast.LENGTH_SHORT).show();
        }else {

            try {
                AesCbcWithIntegrity.SecretKeys keys = AesCbcWithIntegrity.generateKey();
                keys = AesCbcWithIntegrity.generateKeyFromPassword(userPasswd, userPasswd);
                AesCbcWithIntegrity.CipherTextIvMac Decrypt = new AesCbcWithIntegrity.CipherTextIvMac(EncryptedTx);
                DecryptedTxt.setText(AesCbcWithIntegrity.decryptString(Decrypt, keys));
            } catch (GeneralSecurityException e) {
                e.getStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.getCause();
            } catch (IllegalArgumentException e){
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
