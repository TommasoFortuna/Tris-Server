package com.meucci;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");






    }

    public static char chiHaVinto(char[] array){
        if(areEqual(array, 0,1,2)){
            return array[0];
        }

        return ' ';
    }

    public static char areEqual(char[] array, int a, int b, int c){
        if(array[a] == array[b] && array[b] == array[c]){
            return array[a];
        }
        return 'x';
    }

}