package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BinaryED extends AppCompatActivity {

    private EditText ETTextToBinary;
    private EditText ETBinaryToTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_ed);

        ETTextToBinary = findViewById(R.id.ETtxtToBinary);
        ETBinaryToTxt = findViewById(R.id.ETAsciiToTxt);
    }

    public void BinaryEncoder(View view){
        ETBinaryToTxt.setText(ToBinary(ETTextToBinary.getText().toString()));


    }

    public void BinaryDecoder(View view){   //todo solucionar este error de puta mierda (string mas largo que el long, usar bigInteger)
        String binary = ETBinaryToTxt.getText().toString().replaceAll("\\s","");

        try{
            binary = ToText(binary);
            ETTextToBinary.setText(binary);
        }catch (NumberFormatException e){
            Toast.makeText(this, "The binary is too long", Toast.LENGTH_SHORT).show();
            ETTextToBinary.setText("Error! ");

        }


    }

    private String ToBinary(String a){
        byte[] bytes = a.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        String StBin = binary.toString();
        return StBin;
    }
    private String ToText(String b){ //todo no funciona el metodo, no decodifica

        //BigInteger binaryS = new BigInteger(b);
        long charCode = Long.parseLong(b, 2);
        String str = new Character((char)charCode).toString();

        return str;
    }

}
