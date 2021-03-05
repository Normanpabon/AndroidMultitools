package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class ASCIIED extends AppCompatActivity {
    private EditText TxtToAscii, AsciiToTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asciied);
        TxtToAscii = findViewById(R.id.EtTxtToAscii);
        AsciiToTxt = findViewById(R.id.ETAsciiToTxt);
    }

    public void btnEncodeAscii(View view){
        try {
            String text = TxtToAscii.getText().toString();

            // translating text String to 7 bit ASCII encoding
            byte[] bytes = text.getBytes("US-ASCII");
            String decoded = "";
            for(int i = 0; bytes.length > i; i++){
                decoded +=  bytes[i] + " ";
            }
            AsciiToTxt.setText(decoded);
           // AsciiToTxt.setText(Arrays.toString(bytes));

        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    public void btnDecodeAscii(View view){  //TODO Ponerlo a funcionar
        Toast.makeText(this, "Working on it", Toast.LENGTH_SHORT).show();

    }
}
