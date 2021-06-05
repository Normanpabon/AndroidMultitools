package com.h4lfcr3st.androidswissarmy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer extends Thread {

    //server vars
    private ServerSocket serverSocket;
    private String history;
    private int port;
    private Socket socket = null;

    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;

    ChatServer(int port) throws IOException {  //constructor for Server
        this.port = port;
        this.serverSocket = new ServerSocket(port);

    }

    public void HostServer() throws IOException, ClassNotFoundException {
        while(true){
            String tmp;
            this.socket = serverSocket.accept();
            this.ois = new ObjectInputStream(this.socket.getInputStream());
            tmp = (String) this.ois.readObject(); //msg to show
            if(tmp != null){
                this.history += "\n" + tmp;
            }
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.oos.writeObject(this.history);

            this.ois.close();
            this.oos.close();
            this.socket.close();


        }
    }

    public void run(){
        try {
            HostServer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}