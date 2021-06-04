package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class grafoss extends AppCompatActivity {

    private EditText valorMatriz, cantNodos;
    private TextView matriz, infoValorMatriz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafoss);

        valorMatriz = findViewById(R.id.txtnum_valor);
        cantNodos = findViewById(R.id.txt_numNodos);
        matriz = findViewById(R.id.tv_matriz);
        infoValorMatriz = findViewById(R.id.txt_infoValorMatriz);
    }

    public void dimensionMatriz (View view) {

        int dimMatriz = Integer.parseInt(String.valueOf(cantNodos.getText()));
    }
    public void armarMatriz (View view){

        int numNodos = Integer.parseInt(String.valueOf(cantNodos.getText()));
        int sumaFilas = 0, sumaColumnas = 0, noDirigido = 0;

        String acumulador = "", loop = "", acumuladorT = "";

        int numeros[][] = new int[numNodos][numNodos];// primero filas y luego columnas
        int numerosT[][] = new int[numNodos][numNodos];

        while (numNodos==0){
            cantNodos.getError();
            valorMatriz.setEnabled(false);
        }
        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                infoValorMatriz.setText("¿Que valor desea agregar en la posición [" + i + "]" + "[" + j + "] ?");
                int valor = Integer.parseInt(String.valueOf(valorMatriz.getText()));
                while (valor<0) {
                    infoValorMatriz.setText("Debe de ingresar un valor positivo en [" + i + "]" + "[" + j + "] ?");
                    valorMatriz.setText("");
                    valorMatriz.getError();
                    valor = Integer.parseInt(String.valueOf(valorMatriz.getText()));
                }

                numeros[i][j] = valor;

                if (i == 0) {
                    sumaFilas = sumaFilas + valor;
                }

                if (j == 0) {
                    sumaColumnas = sumaColumnas + valor;
                }

                if (i == j) {
                    if (valor != 0) {
                        loop = loop + "Hay un loop en " + "[" + i + "]" + "[" + j + "]\n";
                    }
                }

                acumulador = acumulador + "[" + numeros[i][j] + "]";

            }
            acumulador = acumulador + "\n";

        }

        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {

                numerosT[j][i] = numeros[i][j];
            }
        }

        // Para imprimir la matriz transpuesta
        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {

                acumuladorT = acumuladorT + "[" + numerosT[i][j] + "]";
                if (numerosT[i][j] == numeros[i][j]) {
                    noDirigido++;
                }
            }
            acumuladorT = acumuladorT + "\n";
        }
        matriz.setText(acumulador);
    }
}