package com.h4lfcr3st.androidswissarmy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketComms {
    //client vars
    private String clientName;
    private String host;
    private Socket socket = null;
    private int port;

    //server vars

    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;



    SocketComms(String clientName, String host, int port) { //constructor for client
        this.clientName = clientName +": ";
        this.host = host;
        this.port = port;
    }



    public String SendMessage(String msg) throws IOException, ClassNotFoundException {
        String serverFeedback = "w";

        this.socket = new Socket(this.host, this.port);
        this.oos = new ObjectOutputStream(this.socket.getOutputStream());
        if(msg == null){
            this.oos.writeObject(msg);
        }else{
            this.oos.writeObject(this.clientName+msg);
        }



        this.ois = new ObjectInputStream(this.socket.getInputStream());
        serverFeedback = (String) this.ois.readObject();

        this.oos.close();
        this.ois.close();
        return serverFeedback;

    }





}
