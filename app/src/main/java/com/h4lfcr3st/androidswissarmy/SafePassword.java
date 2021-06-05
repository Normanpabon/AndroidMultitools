package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;

public class SafePassword extends AppCompatActivity {

    private EditText editTextIngreseContraseña, txtOutput;
    private TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_password);

        editTextIngreseContraseña = (EditText) findViewById(R.id.editTextIngreseContraseña);
        txtOutput = (EditText) findViewById(R.id.txtOutput);
        tv1 = (TextView) findViewById(R.id.txtTituloSecurePassword);
        tv2 = (TextView) findViewById(R.id.txtNota);
    }

    public void principal(View view) {

        Scanner sc = new Scanner(System.in); // Se crea el Scanner correspondiente

        String password = editTextIngreseContraseña.getText().toString(); // Variable en donde se almacena la contraseña que ingrese el usuario
        password = sc.nextLine(); // Se verifica el password ingresado por medio del Scanner
        PassCheck(password); // Se usa el metodo PassCheck que ofrece JAVA.

    }

    public void PassCheck(String password) { // Metodo para validar la contraseña

        boolean valid = true; // Variable para validar

        if (password.length() < 8) { // Condicional para verificar la cantidad de caracteres
            txtOutput.setText("La contraseña debe contener más de 8 caracteres");
            valid = false;
        }
        String minusculas = "(.*[a-z].*)";
        if (!password.matches(minusculas)) { // Condicional para verificar minusculas
            txtOutput.setText("La contraseña debe contener almenos una minuscula");
            valid = false;
        }
        String mayusculas = "(.*[A-Z].*)";
        if (!password.matches(mayusculas)) { // Condicional para verificar mayusculas
            txtOutput.setText("La contraseña debe contener almenos una mayuscula");
            valid = false;
        }
        String numeros = "(.*[0-9].*)";
        if (!password.matches(numeros)) { // Condicional para verificar numeros
            txtOutput.setText("La contraseña debe contener almenos un número.");
            valid = false;
        }
        String caracterEspecial = "(.*[ ! # @ $ % ^ & * ( ) - _ = + [ ] ; : ' \" , < . > / ?].*)";
        if (!password.matches(caracterEspecial)) { // Condicional para verificar si contiene un caracter especial
            txtOutput.setText("La contraseña debe contener almenos un caracter especial.");
            valid = false;
        }
        if (valid) { // Impresion si la contraseña cumple con los requerimientos
            txtOutput.setText("La contraseña es segura.");
        }


    }
    public void botonLimpiar(View view){
        txtOutput.setText("");
    }


}