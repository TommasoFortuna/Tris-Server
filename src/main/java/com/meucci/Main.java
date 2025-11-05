package com.meucci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        char[] tris = { '0', '0', '0', '0', '0', '0', '0', '0', '0' };
        System.out.println(printChar(tris));
        boolean turnoPrimoGiocatore = true;
        boolean primoTurno = true;

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
                gioca(G1_in, G1_out, G2_out, tris, primoTurno, '1');
            } else {
                gioca(G2_in, G2_out, G1_out, tris, primoTurno, '2');
            }
            primoTurno = false;
            turnoPrimoGiocatore = !turnoPrimoGiocatore;
        }
        switch (chiHaVinto(tris)) {
            case '1':
                G1_out.println("W");
                G2_out.println(printChar(tris) + "L");
                break;
            case '2':
                G2_out.println("W");
                G1_out.println(printChar(tris) + "L");
                break;
            case 'P':
                if (turnoPrimoGiocatore) {
                    G1_out.println(printChar(tris) + "P");
                    G2_out.println("P");
                } else {
                    G2_out.println(printChar(tris) + "P");
                    G1_out.println("P");
                }
                break;
            default:
                break;
        }

        mioServerSocket.close();
    }

    private static void gioca(BufferedReader in, PrintWriter out, PrintWriter outD, char[] tris, boolean primoTurno, char player)
            throws IOException {
        if (!primoTurno) {
            out.println(printChar(tris) + " ");
        }

        while (true) {
            String mossaScelta = in.readLine();
            if (mossaScelta == null) {
                outD.println("DISCONNECTED");
                return;
            }
            if (aggiungi(tris, player, mossaScelta)) {
                if (chiHaVinto(tris) == '0') {
                    out.println("OK");
                }
                break;
            }
            out.println("KO");
        }
    }

    private static boolean aggiungi(char[] array, char valore, String posS) {
        if (posS.length() > 1) {
            return false;
        }
        int pos = posS.charAt(0) - '0';
        if (pos > 8 || pos < 0) {
            return false;
        }
        if (array[pos] == '0') {
            array[pos] = valore;
            return true;
        }
        return false;
    }

    private static char chiHaVinto(char[] array) {
        /*
         * '0' Nessuno ha vinto
         * '1' G1 ha vinto
         * '2' G2 ha vinto
         * 'P' Gioco finito in pareggio
         */
        char r = '0';

        // orizzontali
        r = areEqual(array, r, 0, 1, 2);
        r = areEqual(array, r, 3, 4, 5);
        r = areEqual(array, r, 6, 7, 8);

        // verticali
        r = areEqual(array, r, 0, 3, 6);
        r = areEqual(array, r, 1, 4, 7);
        r = areEqual(array, r, 2, 5, 8);

        // diagonali
        r = areEqual(array, r, 0, 4, 8);
        r = areEqual(array, r, 2, 4, 6);

        if (r == '0') {
            for (char c : array) {
                if (c == '0') {
                    return r;
                }
            }
            return 'P';
        }
        return r;
    }

    private static char areEqual(char[] array, char r, int a, int b, int c) {
        if (array[a] == array[b] && array[b] == array[c]) {
            if (array[a] != '0') {
                r = array[a];
            }
        }
        return r;
    }

    private static String printChar(char[] a) {
        String s = "";
        for (char c : a) {
            s = s + c + ",";
        }
        return s;
    }

}