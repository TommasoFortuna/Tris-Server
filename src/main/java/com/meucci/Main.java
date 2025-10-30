package com.meucci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        char[] tris = {'0', '0', '0', '0', '0', '0', '0', '0', '0'};
        boolean turnoPrimoGiocatore = true;

        ServerSocket mioServerSocket = new ServerSocket(3000);
        Socket G1 = mioServerSocket.accept();
        BufferedReader G1_in = new BufferedReader(new InputStreamReader(G1.getInputStream()));
        PrintWriter G1_out = new PrintWriter(G1.getOutputStream(), true);
        System.out.println("Giocatore 1 collegato");
        G1_out.println("WAIT");
        
        Socket G2 = mioServerSocket.accept();
        BufferedReader G2_in = new BufferedReader(new InputStreamReader(G2.getInputStream()));
        PrintWriter G2_out = new PrintWriter(G2.getOutputStream(), true);
        System.out.println("Giocatore 2 collegato");
        G1_out.println("READY");
        G2_out.println("READY");


        while (chiHaVinto(tris) == '0') {
            if (turnoPrimoGiocatore) {
                char mossaScelta = G1_in.readLine().charAt(0);
                if(aggiungi(tris, '1', mossaScelta)){
                    //TODO OK
                } else {
                    //TODO KO
                }

                //TODO 

            }else{
                //TODO same as p1

            }
            turnoPrimoGiocatore = !turnoPrimoGiocatore;
        }
        switch (chiHaVinto(tris)) {
            case '1':
            //G1 ha vinto
                //TODO
                break;
            case '2':
            //G2 ha vinto
                //TODO
                break;
            case 'P':
            //Pareggio
                //TODO
                break;
            default:
                break;
        }       

        mioServerSocket.close();
    }

    private static boolean aggiungi(char[] array, char valore, int pos){
        if(array[pos] == '0'){
            array[pos] = valore;
            return true;
        }
        return false;
    }

    private static char chiHaVinto(char[] array){
        /*
         * '0' Nessuno ha vinto
         * '1' G1 ha vinto
         * '2' G2 ha vinto
         * 'P' Gioco finito in pareggio
         */
        char r = '0';

        //orizzontali
        areEqual(array, r, 0,1,2);
        areEqual(array, r, 3,4,5);
        areEqual(array, r, 6,7,8);

        //verticali
        areEqual(array, r, 0,3,6);
        areEqual(array, r, 1,4,7);
        areEqual(array, r, 2,5,8);

        //diagonali
        areEqual(array, r, 0,4,8);
        areEqual(array, r, 2,4,6);

        if(r == '0'){
            for (char c : array) {
                if(c == '0'){
                    return r;
                }
            }
            return 'P';
        }
        return r;
    }

    private static void areEqual(char[] array, char $r,  int a, int b, int c){
        if(array[a] == array[b] && array[b] == array[c]){
            if (array[a] != '0'){
                $r = array[a];
            }
        }
    }

}