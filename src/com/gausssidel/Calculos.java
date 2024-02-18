package com.gausssidel;

import java.util.Arrays;

public class Calculos {
    
    public static final int MAX_ITERACIONES = 100;
    
    public boolean esDominante(double [][] matriz, int filas, int columnas){
        boolean[] Filasvisitadas = new boolean[matriz.length];
        int[] pivotes = new int[matriz.length];
        Arrays.fill(Filasvisitadas, false);
        return hacerDominante(0, Filasvisitadas, pivotes, matriz, filas, columnas);
    }
    
     public boolean hacerDominante(int indice, boolean[] filasvisitadas, int[] pivote, double [][] matriz,  int filas, int columnas){
        int n = filas;
        
        double [][] original = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {                   
                original[i][j] = matriz[i][j];
            }
        }

        if (indice == filas) {
            double[][] T = new double[n][n + 1];
            for (int i = 0; i < pivote.length; i++) {
                for (int j = 0; j < n + 1; j++)
                    T[i][j] = matriz[pivote[i]][j];
            }
            matriz = T;
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {                   
                    original[i][j] = matriz[i][j];
                }
            }
            return true;
        }
        
        for (int i = 0; i < n; i++) {
            if (filasvisitadas[i])
                continue;
            double sum = 0;
            for (int j = 0; j < n; j++)
                sum += Math.abs(matriz[i][j]);
            if (2 * Math.abs(matriz[i][indice]) > sum) {
                filasvisitadas[i] = true;
                pivote[indice] = i;
                if (hacerDominante(indice + 1, filasvisitadas, pivote, original, filas,  columnas))
                    return true;
                filasvisitadas[i] = false;
            }
        }
        return false;
    }
     
     
    public StringBuilder resolverSistemadeEcuaciones(double [][] matriz, double tolerancia){
        int iteraciones = 0;
        int n = matriz.length;
        double[] aproximaciones = new double[n]; 
        double[] valoresPrevios = new double[n];
        Arrays.fill(aproximaciones, 0);
        StringBuilder aprox = new StringBuilder();
        String texto;
        while (true) {
            for (int i = 0; i < n-1; i++) {
                double sum = matriz[i][n]; 
                for (int j = 0; j < n; j++){
                    if (j != i){
                        sum -= matriz[i][j] * aproximaciones[j];
                    }
                }
                aproximaciones[i] = 1 / matriz[i][i] * sum;
            }

            for (int i = 0; i < n-1; i++){
                texto = "Iteracion "+ iteraciones + "= x" + i + " : " +  aproximaciones[i] + " , ";
                aprox.append(texto);
            }
            
            
            iteraciones++;
            boolean stop = true;
            for (int i = 0; i < n && stop; i++)
                if (Math.abs(aproximaciones[i] - valoresPrevios[i]) > tolerancia)
                    stop = false;
            if (stop || iteraciones == MAX_ITERACIONES)
                break;
            valoresPrevios = (double[])aproximaciones.clone();
        }
        return aprox;
    }
    
}
