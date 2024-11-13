package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadRicevitore extends Thread{
    Socket socket;
    ThreadRicevitore(Socket socket){
        this.socket = socket;
    }
    public void run(){
        String line = ""; 
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            do{
                line = in.readLine();
                System.out.println(line);
            }while(!line.equals("!"));
        
        
        
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            
    }
}
