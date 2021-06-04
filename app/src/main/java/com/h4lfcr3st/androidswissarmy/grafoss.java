package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class grafoss extends AppCompatActivity {
    private EditText valorMatriz, cantNodos;
    private TextView matriz, infoValorMatriz, matrizT;
    private Button btnAgregar;

    String acumulador="",acumuladorT="";
    int acumRep=1, countMatriz=0;
    int sumaFilas = 0, sumaColumnas = 0, noDirigido = 0, acumColumna = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorMatriz = findViewById(R.id.txtnum_valor);
        cantNodos = findViewById(R.id.txt_numNodos);
        matriz = findViewById(R.id.tv_matriz);
        matrizT = findViewById(R.id.tv_matrizT);
        infoValorMatriz = findViewById(R.id.txt_infoValorMatriz);
        valorMatriz.setEnabled(false);
        btnAgregar = findViewById(R.id.btn_agregarValor);
        btnAgregar.setEnabled(false);

    }

    public void dimensionMatriz(View view) {
        String dimMatriz = String.valueOf(cantNodos.getText());
        while (dimMatriz.equals("")){
            cantNodos.getError();
        }
        valorMatriz.setEnabled(true);
        btnAgregar.setEnabled(true);
        matriz.setText("");
        acumulador="";
        acumuladorT="";
        acumRep=1;
        countMatriz=0;
        matrizT.setText("");
        sumaFilas = 0;
        sumaColumnas = 0;
    }


    public void agregarValor (View view) {

        int numNodos = Integer.parseInt(String.valueOf(cantNodos.getText()));

        String loop = "";

        int numeros[][] = new int[numNodos][numNodos];// primero filas y luego columnas
        int numerosT[][] = new int[numNodos][numNodos];
        String verificador = String.valueOf(valorMatriz.getText());
        if (verificador.equals("")) {
            valorMatriz.getError();
        } else {
            //infoValorMatriz.setText("Ingrese valor para la posición");
            int valor = Integer.parseInt(String.valueOf(valorMatriz.getText()));
            while (valor < 0) {
                //infoValorMatriz.setText("Debe de ingresar un valor positivo");
                valorMatriz.setText("");
                valorMatriz.getError();
                valor = Integer.parseInt(String.valueOf(valorMatriz.getText()));
            }

            if (acumRep==numNodos){
                acumRep=1;
                numeros[countMatriz][acumRep]=valor;
                //acumulador = acumulador + "[" + valor + "]"+ "\n";
                acumulador = acumulador + "[" + numeros[countMatriz][acumRep] + "]"+ "\n";
                countMatriz++;
                matriz.setText(acumulador);
                valorMatriz.setText("");
            }else{
                numeros[countMatriz][acumRep]=valor;
                //acumulador = acumulador + "[" + valor + "]";
                acumulador = acumulador + "[" + numeros[countMatriz][acumRep] + "]";
                acumRep++;
                matriz.setText(acumulador);
                valorMatriz.setText("");
            }
            if (countMatriz==numNodos){
                valorMatriz.setEnabled(false);
                btnAgregar.setEnabled(false);
                Toast.makeText(this, "Esa es su matriz", Toast.LENGTH_LONG).show();
            }
            //falta por corregir esta parte
            if (acumRep<numNodos){
                sumaFilas=sumaFilas+valor;
            }else{
                sumaColumnas=sumaColumnas+valor;
            }
            matrizT.setText("Grado de entrada: "+ sumaColumnas + "\nGrado de salida: " + sumaFilas);
        }
    }
}