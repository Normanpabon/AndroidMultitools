package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MacVendorFinder extends AppCompatActivity {
    private EditText ETMac, ETVendor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_vendor_finder);
        ETMac = findViewById(R.id.ETMac);
        ETVendor = findViewById(R.id.ETMacVendor);


    }

    public void BtnBuscar(View view){
        String MacABuscar = ETMac.getText().toString();
        String MacTratada = BuscarVendedor(MacABuscar);
        String vendor = "";
        if(MacTratada.equals("!")){
            ETVendor.setText("Error in MACAdrres, check it and try again");
        }else{
            vendor = BuscarMac(MacTratada, "");
            ETVendor.setText("Vendor = "+vendor);
        }

        BuscarVendedor(MacABuscar);

    }

    private static String BuscarVendedor(String MacIngresada) {
        String MacSanitizada = "";
        String Salida = "";
        for (int i = 0; MacIngresada.length() > i; i++) {
            char actual = MacIngresada.charAt(i);
            if (actual == '-' || actual == ':') {
                System.out.println("Saltando posicion  " + i);
            } else {
                MacSanitizada += String.valueOf(actual);
            }
        }
        //Corrige por si hay mas caracteres que 6
        if (MacSanitizada.length() > 5) {
            for(int x = 0; 6>x; x++){
                Salida += MacSanitizada.charAt(x);
            }
        }else{
            Salida = "!";
        }
        return Salida;
    }

    private String BuscarMac(String MacABuscar, String direccion){

        //String csvFile = "assets/macvendor.txt";
        String line = "";
        String cvsSplitBy = "\t";
        String vendedor = "";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("macvendor.txt")))) {
            boolean encontrado = false;
            String MacActual = "";


            while ((line = br.readLine()) != null && !encontrado) {
                // use comma as separator
                String[] MACLISTED = line.split(cvsSplitBy);
                MacActual = MACLISTED[0];

                if(MacActual.replaceAll("\\s+","").equalsIgnoreCase(MacABuscar)){
                    System.out.println("MAC [code= " + MACLISTED[0] + " , Vendor=" + MACLISTED[1] + "]");
                    encontrado = true;
                    vendedor = MACLISTED[1];
                }
            }
            if(vendedor.isEmpty()){
                vendedor = "Not Found";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vendedor;
    }
}
