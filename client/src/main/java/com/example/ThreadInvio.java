package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ThreadInvio extends Thread{
    Socket socket;

    ThreadInvio(Socket socket){
        this.socket = socket;
    }

    public void run(){
        String line = ""; 

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            String nota;
            String fraseServer;
            boolean presente = false;
            Scanner sc = new Scanner(System.in);

            do {
                System.out.println("inserire il proprio username:");
                String username = sc.nextLine();
                out.writeBytes(username + "\n");
                String apprUsername = in.readLine();

                if(apprUsername.equals("n")){
                    System.out.println("username non disponibile");
                    presente = true;
                } else{
                    System.out.println("username disponibile");
                    presente = false;
                }
            } while (presente);
            
            do {
                
                if(!presente){
                    System.out.println("Scrivere la nota (scrivere -exit- per uscire, -visualizza- per vedere tutte le note, -condivise- per quelle condivise con tutti (con & davanti per aggiungerne una condivisibile),-cancella- per eliminare una nota)");

                    nota = sc.nextLine();

                    if(nota.equals("exit")){
                        out.writeBytes("!" + "\n");
                    }
                    else if(nota.equals("visualizza")){
                        out.writeBytes("?" + "\n");

                        do {
                            fraseServer =  in.readLine();
                            System.out.println(fraseServer);
                            
                        } while (!fraseServer.equals("@"));
                    }
                    else if(nota.equals("condivise")){
                        out.writeBytes("&" + "\n");

                        do {
                            fraseServer =  in.readLine();
                            System.out.println(fraseServer);
                            
                        } while (!fraseServer.equals("@"));
                    }
                    else if(nota.equals("cancella")){
                        out.writeBytes("$" + "\n");
                        System.out.println("Quale nota vuoi cancellare fra queste? (inserire la stringa da cancellare)");
                        do {
                            fraseServer =  in.readLine();
                            System.out.println(fraseServer);
                            
                        } while (!fraseServer.equals("@"));

                        String strCancella = sc.nextLine();
                        out.writeBytes(strCancella + "\n");
                        String trovatoCanc = in.readLine();
                        System.out.println(trovatoCanc);
                        if(trovatoCanc.equals("s")){
                            System.out.println("stringa eliminata");
                        }
                        else{
                            System.out.println("stringa non trovata");
                        }
                    }
                    else{
                        out.writeBytes(nota + "\n");

                        fraseServer =  in.readLine();
                        System.out.println(fraseServer);
                    }
                }
                else{
                    nota = "exit";
                    out.writeBytes("!" + "\n");
                }
            } while (!nota.equals("exit"));
            



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}