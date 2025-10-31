/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2.s3.lab3;

/**
 *
 * @author ferna
 */
public class Solucionador 
{
    public boolean solucionador(int[][] tablero) 
    {
        for (int i=0;i<81;i++) 
        {
            int fil=i/9,col=i%9;
            if (tablero[fil][col]==0) 
            {
                for (int valor=1;valor<=9;valor++) 
                {
                    if (numValido(tablero,fil,col,valor)) 
                    {
                        tablero[fil][col]=valor;
                        if (solucionador(tablero)) 
                            return true;
                        tablero[fil][col]=0;
                    }
                }
                return false;
            }
        }
        return true;
    }
    public boolean numValido(int tablero[][],int fil,int col,int valor)
    {
        for(int i=0;i<9;i++)
        {
            if(tablero[i][col]==valor)
                return false;
        }
        for(int j=0;j<9;j++)
        {
            if(tablero[fil][j]==valor)
                return false;
        }
        int regionFil=(fil/3)*3;
        int regionCol=(col/3)*3;
        
        for(int i=regionFil;i<regionFil+3;i++)
        {
            for(int j=regionCol;j<regionCol+3;j++)
            {
                if(tablero[i][j]==valor)
                    return false;
            }
        }
        return true;
    }
}
