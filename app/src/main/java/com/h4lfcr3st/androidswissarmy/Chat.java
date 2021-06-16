package com.h4lfcr3st.androidswissarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.net.IpSecManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.h4lfcr3st.androidswissarmy.ChatServer;
import com.h4lfcr3st.androidswissarmy.SocketComms;

import java.io.IOException;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class Chat extends AppCompatActivity {

    private EditText serverIp, serverPort, clientMessage, cliename, hostPort;
    private Button btnConnect, btnSend, btnHost;
    private TextView history;

    private SocketComms socket;
    private ChatServer chatServer;

    private String ip;
    private int port;
    private String clientName;

    private volatile boolean stopThread = false;



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
        InitThread(10, this.socket, " ");
        Toast.makeText(this, "Fetching messages", Toast.LENGTH_SHORT).show();

    }

    public void ConnectButton(View view){

        //todo control de errores
        this.ip = serverIp.getText().toString();
        this.port = Integer.parseInt((serverPort.getText().toString()));
        this.clientName = cliename.getText().toString();

        ConnectServer();


    }

    public void InitThread(int seconds, SocketComms socket, String history ){
        stopThread = false;
        FetchMessage fetchMessage = new FetchMessage(seconds, socket, history);
        new Thread(fetchMessage).start();
    }

    public void StopThread(){
        stopThread = true;
    }

    public void SendMessage(View view){

        stopThread = true;
        String tmpMsg = clientMessage.getText().toString();

        BackgroundSendMessage backgroundSendMessage = new BackgroundSendMessage(tmpMsg, this.socket);
        new Thread(backgroundSendMessage).start();


        clientMessage.setText("");
        InitThread(10, this.socket, " ");


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

    class BackgroundSendMessage implements Runnable{
        String msgToSend;
        SocketComms socketSend;
        String tmp = "";

        BackgroundSendMessage(String msgToSend, SocketComms socketComms){
            this.msgToSend = msgToSend;
            this.socketSend = socketComms;
        }

        @Override
        public void run() {
            try {
                tmp = socketSend.SendMessage(msgToSend);
            }catch (IOException e){
                e.printStackTrace();
            }catch (NoClassDefFoundError e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    class FetchMessage implements Runnable{
        int seconds; // time between refresh
        SocketComms socketFetcher; //socket to connect
        String lastHistory; // to compare new fetched history
        String newMessage;

        FetchMessage(int seconds, SocketComms socketFetcher, String lastHistory){
            this.seconds = seconds;
            this.socketFetcher = socketFetcher;
            this.lastHistory = lastHistory;
            this.newMessage = lastHistory;
        }

        @Override
        public void run() {
            int i = 0;
            while (!stopThread){
                Log.e("AvisoEnvio", "Fetching messages try n°: " + i);
                i++;
                String tmp;
                try {
                    Thread.sleep((1000*seconds));

                    tmp = this.socketFetcher.SendMessage(null);  //todo revisar en el servidor, se esta haciendo append a los mensajes nulos
                    if(tmp != null){
                        newMessage = tmp;
                        if(compareHistory()){
                            // if changes are detected post message to handler

                            //handler

                            history.post(new Runnable() {
                                @Override
                                public void run() {
                                    history.setText(lastHistory);
                                }
                            });
                            /*
                            Handler mainHandler = new Handler(Looper.getMainLooper()); //asociation with main thread

                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    history.clearComposingText();
                                    history.setText(lastHistory); //Change history data
                                }
                            });

                             */




                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            }

        }

        private boolean compareHistory(){  // compare new fetched messages from server to detect a change
            if(!lastHistory.equals(newMessage) && newMessage != null){
                lastHistory = newMessage;
                return true;
            }
            return false;
        }


    }

}