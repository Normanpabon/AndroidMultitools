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
    private TextView matriz, matrizT, resultado;
    private Button btnAgregar,btnCantNodos;
    private int[][] numerosMatriz;
    private int[][] numerosMatrizT;
    private boolean dimInicializada=false;

    String acumulador="",acumuladorT="",acumResultados="";
    int acumRep=1, countMatriz=0;
    int sumaFilas = 0, sumaColumnas = 0, noDirigido = 0, acumColumna = 0,numNodos=0,i=0,j=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafoss);

        valorMatriz = findViewById(R.id.txtnum_valor);
        cantNodos = findViewById(R.id.txt_numNodos);
        matriz = findViewById(R.id.tv_matriz);
        resultado = findViewById(R.id.tv_resultMatriz);
        matrizT = findViewById(R.id.tvMatrizT);
        valorMatriz.setEnabled(false);
        btnAgregar = findViewById(R.id.btn_agregarValor);
        btnAgregar.setEnabled(false);
        btnCantNodos = findViewById(R.id.btn_enviar_numNodos);

    }

    public void dimensionMatriz(View view) {
        String dimMatriz = String.valueOf(cantNodos.getText());
        if (dimMatriz.equals("")){
            cantNodos.setError("Requerido");
        }else{
            valorMatriz.setEnabled(true);
            btnAgregar.setEnabled(true);
            matriz.setText("");
            matrizT.setText("");
            acumulador="";
            acumuladorT="";
            acumRep=0;
            countMatriz=0;
            resultado.setText("");
            sumaFilas = 0;
            sumaColumnas = 0;
            i=0;
            j=0;
            btnCantNodos.setEnabled(false);
            cantNodos.setEnabled(false);
            numNodos = Integer.parseInt(String.valueOf(cantNodos.getText()));
            inicializarMatriz(numNodos);
            if (!this.dimInicializada){
                inicializarMatriz(numNodos);
            }
        }
    }
    public void inicializarMatriz (int numNodos){
        this.numerosMatriz= new int[numNodos][numNodos];
        this.numerosMatrizT= new int[numNodos][numNodos];
        this.dimInicializada=true;
    }
    public void datosGrafo (View view){

        //int numerosT[][] = new int[numNodos][numNodos];
        String verificadorValor = String.valueOf(valorMatriz.getText());


        if (verificadorValor.equals("")){
            valorMatriz.setError("Requerido");
        }else{
            int valor = Integer.parseInt(String.valueOf(valorMatriz.getText()));

            if (i<numNodos){
                if (j<numNodos){
                    this.numerosMatriz[i][j]=valor;
                }else{
                    i++;
                    j=0;
                    this.numerosMatriz[i][j]=valor;
                }
                j++;
            }
            acumRep++;
            if (acumRep==numNodos*numNodos){
                for (int i = 0; i < numNodos; i++) {
                    for (int j = 0; j < numNodos; j++) {

                        this.numerosMatrizT[j][i] = this.numerosMatriz[i][j];
                    }
                }
                for (int i = 0; i < numNodos; i++) {
                    for (int j = 0; j < numNodos; j++) {
                        acumulador = acumulador + "[" + this.numerosMatriz[i][j] + "]";
                        acumuladorT = acumuladorT + "[" + this.numerosMatrizT[i][j] + "]";
                        if (i == 0) {
                            sumaFilas = sumaFilas + this.numerosMatriz[0][j];
                        }

                        if (j == 0) {
                            sumaColumnas = sumaColumnas + this.numerosMatriz[i][0];
                        }

                        if (i == j) {
                            if (this.numerosMatriz[i][j]!= 0) {
                                acumResultados = acumResultados + "Hay un loop en " + "[" + i + "]" + "[" + j + "]\n";
                            }
                        }
                        if (this.numerosMatrizT[i][j] == this.numerosMatriz[i][j]) {
                            noDirigido++;
                        }
                    }
                    acumulador = acumulador + "\n";
                    acumuladorT = acumuladorT + "\n";
                }
                acumResultados = acumResultados + "El grado de salida del nodo 1 es: " + sumaFilas + "\n";
                acumResultados = acumResultados + "El grado de entrada del nodo 1 es: " + sumaColumnas + "\n";
                matriz.setText(acumulador);
                matrizT.setText(acumuladorT);
                cantNodos.setEnabled(true);
                btnCantNodos.setEnabled(true);
                valorMatriz.setEnabled(false);
                btnAgregar.setEnabled(false);
                if (noDirigido == numNodos * numNodos) {
                    acumResultados= acumResultados + "El grafo es no dirigido\n";
                } else {
                    acumResultados= acumResultados + "El grafo es dirigido\n";
                }

                resultado.setText(acumResultados);
            }
            valorMatriz.setText("");
            Toast.makeText(this, "Has agregado un valor", Toast.LENGTH_SHORT).show();

            /*if (acumRep==numNodos){
                acumRep=1;
                numeros[countMatriz][acumRep]=valor;
                acumulador = acumulador + "[" + numeros[countMatriz][acumRep] + "]"+ "\n";
                countMatriz++;
                matriz.setText(acumulador);
                valorMatriz.setText("");
            }else{
                numeros[countMatriz][acumRep]=valor;
                acumulador = acumulador + "[" + numeros[countMatriz][acumRep] + "]";
                acumRep++;
                matriz.setText(acumulador);
                valorMatriz.setText("");
            }
            if (countMatriz==acumRep){
                if (valor!=0){
                    loop = loop + "Hay un loop en " + "[" + countMatriz + "]" + "[" + acumRep + "]\n";
                }
            }
            if (countMatriz==numNodos){
                valorMatriz.setEnabled(false);
                btnAgregar.setEnabled(false);
                Toast.makeText(this, "Esa es su matriz", Toast.LENGTH_LONG).show();
                resultado.setText("Resultados: " + loop);
            }*/

        }

    }

}