package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

public class Encoders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encoders);
    }
    //ED == Encoder & Decoder
    public void Base64ED(View view){
        Intent EnterBase64ED = new Intent(this, Base64ED.class);
        startActivity(EnterBase64ED);
    }
    public void BinaryED(View view){
        Intent EnterBinaryED = new Intent(this, BinaryED.class);
        startActivity(EnterBinaryED);
    }
    public void HexED(View view){
        Intent EnterHexED = new Intent(this, HEXED.class);
        startActivity(EnterHexED);
    }
    public void ASCIIED(View view){
        Intent EnterASCIIED = new Intent(this, ASCIIED.class);
        startActivity(EnterASCIIED);
    }
    public void OctED(View view){
        Intent EnterOctEd = new Intent(this, OctED.class);
        startActivity(EnterOctEd);
    }

    public void GrafosActivity(View view){
        Intent EnterGrafos = new Intent(this, grafoss.class);
        startActivity(EnterGrafos);
    }


}
