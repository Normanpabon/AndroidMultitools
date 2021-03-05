package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;

public class HEXED extends AppCompatActivity {
    private EditText TxtToHex;
    private EditText HexToTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hexed);
        TxtToHex = findViewById(R.id.EtTxtToAscii);
        HexToTxt = findViewById(R.id.ETAsciiToTxt);


    }

    public void BtnDecode(View view) {  //todo crear el decodificador (quizas pasar a binario y luego a string)
        Toast.makeText(this, "Still working", Toast.LENGTH_SHORT).show();

    }

    public void btnEncode(View view) {

        String text = TxtToHex.getText().toString();
        HexToTxt.setText(toHex(text));

    }


    private String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));
    }


}
