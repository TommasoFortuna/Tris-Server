package com.meucci;

public class Main {
    public static void main(String[] args) {






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