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
            
            String username;
            String serverUsername;
            String[] scelta;
            String[] scelta2;
            String stringaScelta;
            String stringaScelta2;
            String serverRisposta;
            String listaStringa;
            String[] lista; 
            String stringM;
            String tipoM;
            String testoM;
            String nomeM;
            Scanner scanner = new Scanner(System.in);

            do {
                System.out.println("inserire il proprio username:");
                username = scanner.nextLine();
                out.writeBytes(username + "\n");

                serverUsername = in.readLine();

                if(serverUsername.equals("KOS")){
                    System.out.println("username non disponibile");
                    
                } else{
                    System.out.println("username disponibile");
                }  

                do{
                    System.out.println("listaC - lista contatti");
                    System.out.println("listaG - linsta gruppi");
                    System.out.println("CREA:NOME - crea un gruppo");
                    System.out.println("PART:NOME - entra in un gruppo");
                    System.out.println("nuova - avvia una chat");
                    System.out.println("EXIT - esci");

                    stringaScelta = scanner.nextLine();
                    scelta=stringaScelta.split(":");

                    //invio
                    if(scelta[0].equals("listaC")){
                        out.writeBytes("C"+ "\n");
                    }
                    if(scelta[0].equals("listaG")){
                        out.writeBytes("G"+ "\n");
                    }
                    if(scelta[0].equals("CREA")){
                        out.writeBytes(stringaScelta + "\n");
                    }
                    if(scelta[0].equals("PART")){
                        out.writeBytes(stringaScelta + "\n");
                    }
                    if(scelta[0].equals("nuova")){
                        out.writeBytes("M"+ "\n");
                    }

                    //ricezione
                    serverRisposta= in.readLine();

                    if(scelta.equals("listaC")){
                        out.writeBytes("C"+ "\n");
                        System.out.println("ecco la lista dei contatti:");
                        listaStringa=in.readLine();
                        lista =listaStringa.split(",");

                        for(int i=0; i < lista.length; i++){
                            System.out.println(lista[i]);
                        }
                    }
                    if(scelta.equals("listaG")){
                        out.writeBytes("G"+ "\n");
                        System.out.println("ecco la lista dei gruppi:");
                        listaStringa=in.readLine();
                        lista =listaStringa.split(",");

                        for(int i=0; i < lista.length; i++){
                            System.out.println(lista[i]);
                        }
                    }


                    if(serverRisposta.equals("OKG")){
                        System.out.println("il gruppo è stato creato");
                    }
                    if(serverRisposta.equals("KOG")){
                        System.out.println("il gruppo è già esistente");
                    }

                    if(serverRisposta.equals("OKP")){
                        System.out.println("ora partecipi al gruppo");
                    }
                    if(serverRisposta.equals("KOP")){
                        System.out.println("non puoi partecipare a questo gruppo");
                    }

                    if(serverRisposta.equals("OKK")){
                        System.out.println("cosa vuoi fare?");
                        System.out.println("PRIV - invia un messaggio ad un solo utente");
                        System.out.println("GRP - invia un messaggio ad un gruppo");
                        System.out.println("ALL - invia un messaggio a tutti gli utenti");
                        tipoM = scanner.nextLine();
                        stringM = tipoM + ":";

                        if(!tipoM.equals("ALL")) {
                            System.out.println("a chi lo vuoi inviare?");
                            nomeM = scanner.nextLine();
                            stringM = stringM + ":" + nomeM + ":";
                        }                        

                        System.out.println("Inserisci il messaggio da inviare");
                        testoM = scanner.nextLine();

                        stringM = stringM + ":" + testoM;
                        out.writeBytes(stringM + "/n");
                    }





                }while(!scelta.equals("EXIT"));
            } while (!serverUsername.equals("KOS"));
            




































































            /*String nota;
            String fraseServer;
            boolean presente = false;
           
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
            } while (!nota.equals("exit"));*/
            



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}