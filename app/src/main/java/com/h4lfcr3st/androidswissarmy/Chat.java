package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.h4lfcr3st.androidswissarmy.ChatServer;
import com.h4lfcr3st.androidswissarmy.SocketComms;

import java.io.IOException;

public class Chat extends AppCompatActivity {

    private EditText serverIp, serverPort, clientMessage, cliename, hostPort;
    private Button btnConnect, btnSend, btnHost;
    private TextView history;

    private SocketComms socket;
    private ChatServer chatServer;

    private String ip;
    private int port;
    private String clientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        serverIp = findViewById(R.id.txtServerIp);
        serverPort = findViewById(R.id.txtServerPort);
        clientMessage = findViewById(R.id.txtSendMessage);
        cliename = findViewById(R.id.txtUsername);
        hostPort = findViewById(R.id.txtHostPort);

        btnConnect = findViewById(R.id.btnConnectServer);
        btnSend = findViewById(R.id.btnSendMessage);
        btnHost = findViewById(R.id.btnHost);

        history = findViewById(R.id.tvChatHistory);


    }

    public void ConnectServer(){
        this.socket = new SocketComms(this.clientName, this.ip, this.port);

    }

    public void ConnectButton(View view){

        //todo control de errores
        this.ip = serverIp.getText().toString();
        this.port = Integer.parseInt((serverPort.getText().toString()));
        this.clientName = cliename.getText().toString();

        ConnectServer();


    }

    public void SendMessage(View view){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            String tmpMsg = clientMessage.getText().toString();
            String tmp;
            tmp = this.socket.SendMessage(tmpMsg);
            this.history.clearComposingText();
            this.history.setText(tmp);
            Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(this, "Error al enviar mensaje", Toast.LENGTH_SHORT).show();
        }catch (ClassNotFoundException e){
            Toast.makeText(this, "Error al enviar mensaje", Toast.LENGTH_SHORT).show();

        }

    }

    public void HostServer(View view) {
        try{
            this.chatServer = new ChatServer(Integer.parseInt(hostPort.getText().toString()));
            new Thread(this.chatServer).start();
            Toast.makeText(this, "Servidor creado", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(this, "Error creando servidor", Toast.LENGTH_SHORT).show();
        }


    }
}