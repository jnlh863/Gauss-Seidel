package com.gausssidel;

import java.util.Arrays;

public class Calculos {
    
    public static final int MAX_ITERACIONES = 100;
    
    public boolean esDominante(double [][] matriz){
        boolean[] Filasvisitadas = new boolean[matriz.length];
        int[] pivotes = new int[matriz.length];
        Arrays.fill(Filasvisitadas, false);
        return hacerDominante(0, Filasvisitadas, pivotes, matriz,  matriz.length);
    }
    
     public boolean hacerDominante(int indice, boolean[] filasvisitadas, int[] pivote, double [][] matriz, int len){
        int n = len - 1;
        if (indice == len) {
            double[][] T = new double[n][n + 1];
            for (int i = 0; i < pivote.length; i++) {
                for (int j = 0; j < n + 1; j++)
                    T[i][j] = matriz[pivote[i]][j];
            }
            matriz = T;
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
                if (hacerDominante(indice + 1, filasvisitadas, pivote, matriz, len))
                    return true;
                filasvisitadas[i] = false;
            }
        }
        return false;
    }
     
     
    public void resolverSistemadeEcuaciones(double [][] matriz, double tolerancia){
        int iterations = 0;
        int n = matriz.length;
        double[] aproximaciones = new double[n]; 
        double[] valoresPrevios = new double[n];
        Arrays.fill(aproximaciones, 0);
        while (true) {
            for (int i = 0; i < n-1; i++) {
                double sum = matriz[i][n]; 
                for (int j = 0; j < n; j++)
                    if (j != i)
                        sum -= matriz[i][j] * aproximaciones[j];
                aproximaciones[i] = 1 / matriz[i][i] * sum;
            }
            System.out.print("Iteración" + iterations + " = [");
            for (int i = 0; i < n-1; i++)
                System.out.print("x" + i + " : " +  aproximaciones[i] + " , ");
            System.out.println("]");
            iterations++;
            boolean stop = true;
            for (int i = 0; i < n && stop; i++)
                if (Math.abs(aproximaciones[i] - valoresPrevios[i]) > tolerancia)
                    stop = false;
            if (stop || iterations == MAX_ITERACIONES)
                break;
            valoresPrevios = (double[])aproximaciones.clone();
        }
    }
    
}
