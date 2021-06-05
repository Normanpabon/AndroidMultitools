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
    private TextView matriz, infoValorMatriz, resultado;
    private Button btnAgregar;

    String acumulador="",acumuladorT="",loop="";
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
        infoValorMatriz = findViewById(R.id.txt_infoValorMatriz);
        valorMatriz.setEnabled(false);
        btnAgregar = findViewById(R.id.btn_agregarValor);
        btnAgregar.setEnabled(false);

    }

    public void dimensionMatriz(View view) {
        String dimMatriz = String.valueOf(cantNodos.getText());
        if (dimMatriz.equals("")){
            cantNodos.setError("Requerido");
        }else{
            valorMatriz.setEnabled(true);
            btnAgregar.setEnabled(true);
            matriz.setText("");
            acumulador="";
            acumuladorT="";
            acumRep=0;
            countMatriz=0;
            resultado.setText("");
            sumaFilas = 0;
            sumaColumnas = 0;
            loop="";
            i=0;
            j=0;
        }
    }
    public void datosGrafo (View view){

        numNodos = Integer.parseInt(String.valueOf(cantNodos.getText()));
        int numeros[][] = new int[numNodos][numNodos];// primero filas y luego columnas
        //int numerosT[][] = new int[numNodos][numNodos];
        String verificadorValor = String.valueOf(valorMatriz.getText());

        if (verificadorValor.equals("")){
            valorMatriz.setError("Requerido");
        }else{
            int valor = Integer.parseInt(String.valueOf(valorMatriz.getText()));

            if (i<numNodos){
                if (j<numNodos){
                    numeros[i][j]=valor;
                }else{
                    i++;
                    j=0;
                    numeros[i][j]=valor;
                }
                j++;
            }
            acumRep++;
            if (acumRep==numNodos*numNodos){
                for (int i = 0; i < numNodos; i++) {
                    for (int j = 0; j < numNodos; j++) {
                        acumulador = acumulador + "[" + numeros[i][j] + "]";
                    }
                    acumulador = acumulador + "\n";
                }
                resultado.setText(acumulador);
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