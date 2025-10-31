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
    
    public int getTamano(){
        return tamano;
    }
    
    public int getValor(int fila, int col){
        return tablero[fila][col];
    }
    
    public boolean isFijo(int fila, int col){
        return fijos[fila][col];
    }
    
    public boolean setValor(int fila, int col, int valor){
        
        if( fijos[fila][col] )
            return false;
        
        if( valor < 0 || valor > 9 )
            return false;
        
        tablero[fila][col] = valor;
        
        return true;
    }
    
    public void setValorVacio(int fila, int col){
        
        if( !fijos[fila][col] )
            tablero[fila][col] = 0;
        
    }
    
    public void setMatrizSolucion(int[][] matriz){
        
        for (int fila = 0; fila < tamano; fila++) {
            System.arraycopy(matriz[fila], 0, solucion[fila], 0, tamano);
        }
    }
    
    public void setMatrizTablero(int[][] matriz){
        
        for (int fila = 0; fila < tamano; fila++) {
            System.arraycopy(matriz[fila], 0, tablero[fila], 0, tamano);
            
            for (int col = 0; col < tamano; col++) {
                fijos[fila][col] = matriz[fila][col] != 0;
            }
        }
    }
    
    
    public boolean isMovimientoValido(int fila, int col, int valor){
        
        if (valor < 1 || valor > 9)
            return false;
        
        for (int i = 0; i < tamano; i++) {
            
            if (tablero[fila][i] == valor){
                return false;
            }
            
            if (tablero[i][col] == valor){
                return false;
            }
        }
        
        int baseFila = (fila / 3) * 3;
        int baseCol = (col / 3) * 3;
        
        for (int i = baseFila; i < baseFila + 3; i++) {
            
            for (int j = baseCol; j < baseCol + 3; j++) {
                
                if (tablero[i][j] == valor)
                    return false;
                
            }
        }
        
        return true;
        
    }
    
    public boolean isValorCorrecto(int fila, int col, int valor){
        
        return solucion[fila][col] == valor;
    }
    
    public void aplicarSolucion(){
        
        for (int fila = 0; fila < tamano; fila++) {
            
            for (int col = 0; col < tamano; col++) {
                
                tablero[fila][col] = solucion[fila][col];
                
            }
            
        }
    }
    
}
