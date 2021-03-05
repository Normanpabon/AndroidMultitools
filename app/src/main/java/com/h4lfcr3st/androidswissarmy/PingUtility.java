package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.*;

public class PingUtility extends AppCompatActivity {

    private EditText HostToPing, pingResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_utility);

        HostToPing = findViewById(R.id.ETMac);
        pingResults = findViewById(R.id.ETMacVendor);
        
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {   //todo no tocar D:, solo funciona y ya
        boolean recheable = false;
        private String ipToPing = HostToPing.getText().toString();


        @Override
        protected String doInBackground(String... params) {
            try{

                InetAddress geek = InetAddress.getByName(ipToPing);

                System.out.println("Sending Ping Request to " + ipToPing);
                recheable = geek.isReachable(10000);

            }catch (Exception e){

                e.printStackTrace();

            }

            return ipToPing;
        }


        @Override
        protected void onPostExecute(String result) { //imprime el resultado una vez finalizado el proceso
            pingResults.setText("The host: " + ipToPing +" its recheable = " + recheable);

        }

    }

    public void BtnMakePing(View view){ //todo corregir
        if(HostToPing.getText().toString().isEmpty()){
            Toast.makeText(this, "Type a host", Toast.LENGTH_SHORT).show();
        }else{
            AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute();

        }

    }
}
