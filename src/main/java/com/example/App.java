package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) {
        int risposta = 0;
        int conta = 0;
        int client = 0;
        try {
            double casuale = (int) (Math.random() * 100);
            System.out.println("server avviato");
            ServerSocket server = new ServerSocket(3000);

            Socket s = server.accept();
            System.out.println("un cliente si è connesso");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            out.writeBytes("indovina un numero da 1 a 100" + '\n');
            do {
                client = Integer.parseInt(in.readLine());
                conta++;
                if (casuale == client) {
                    risposta = 3;
                    conta--;
                } else if (casuale > client) {
                    risposta = 2;
                } else if (casuale < client) {
                    risposta = 1;
                }
                out.writeBytes(Integer.toString(risposta) + '\n');
                out.writeBytes(Integer.toString(conta) + '\n');
            } while (risposta != 3);
            System.exit(0);

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Errore durante l'istanza del server del server");
        }
    }
}
