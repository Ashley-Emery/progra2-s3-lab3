/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2.s3.lab3;

/**
 *
 * @author ashley
 */
public class Tablero {
    
    public final int tamano = 9;
    public final int[][] tablero = new int[tamano][tamano];
    public final boolean[][] fijos = new boolean[tamano][tamano];
    public final int[][] solucion = new int[tamano][tamano];
    
    public Tablero (){}
    
    public Tablero(int[][] inicial, int[][] matrizSolucion){
        
        for(int fila = 0; fila < tamano; fila++){
            for (int col = 0; col < tamano; col++) {
                
                tablero[fila][col] = inicial[fila][col];
                fijos[fila][col] = inicial[fila][col] != 0;
                solucion[fila][col] = matrizSolucion[fila][col];
                
            }
        }
    }
    
    
    
}
