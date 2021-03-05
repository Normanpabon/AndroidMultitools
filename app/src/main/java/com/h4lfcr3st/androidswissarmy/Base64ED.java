package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;

public class Base64ED extends AppCompatActivity {
    private EditText TxtToBase64;
    private EditText Base64ToTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base64_ed);

        TxtToBase64 = findViewById(R.id.ETtxtToBinary);
        Base64ToTxt = findViewById(R.id.ETAsciiToTxt);

    }

    public void BtnDecode(View view){

        TxtToBase64.setText(DecodificarBase64(Base64ToTxt.getText().toString()));

    }

    public void BtnEncode(View view){

        Base64ToTxt.setText(CodificarBase64(TxtToBase64.getText().toString()));
    }


    public String CodificarBase64(String toEncode) {
        byte[] encodedBytes = Base64.encode(toEncode.getBytes(), Base64.DEFAULT);
        return new String(encodedBytes);    //devuelve string codificado
    }

    //Decodifica Base64 a String
    public String DecodificarBase64(String toDecode) {
        byte[] decodeValue = Base64.decode(toDecode, Base64.DEFAULT);
        return new String(decodeValue);
    }
}