package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 3000);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
        
                    //Sender
                    ThreadInvio invio = new ThreadInvio(socket);
        
                    //Receiver
                    ThreadRicevitore ricevitore = new ThreadRicevitore(socket);
        
                    invio.start();
                    ricevitore.start();
                    
                    /*
                    try {
                        //Wait until sender & receiver thread is done
                        invio.join();
                        ricevitore.join();
                        socket.close();
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }*/

                
            }
            catch (Exception e) {}
        }
}