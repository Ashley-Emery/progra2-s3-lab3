/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2.s3.lab3;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
/**
 *
 * @author ferna
 */
public class Generador 
{
    private Random random=new Random();
    
    public Tablero generadorTablero(String dificultad) 
    {
        int completo[][]=new int[9][9];
        randomizador(completo); 

        int solucion[][]=new int[9][9];
        for (int f=0;f<9;f++) 
        {
            for (int c=0;c<9;c++) 
            {
                solucion[f][c]=completo[f][c];
            }
        }

        String niveles;
        if (dificultad==null) 
        {
            niveles="";
        } 
        else 
        {
            niveles=dificultad.trim().toUpperCase();
        }
        
        int vacios;
        switch (niveles) {
            case "EASY":
                vacios = 35;
                break;
            case "HARD":
                vacios = 55;
                break;
            default:
                vacios = 45;
        }

        int[][] tableroJuego=new int[9][9];
        for (int f=0;f<9;f++) 
        {
            for (int c=0;c<9;c++) 
            {
                tableroJuego[f][c] = solucion[f][c];
            }
        }

        int indices[]=new int[81];
        for (int i=0; i<81; i++) {
            indices[i]=i;
        }

        for (int i=indices.length-1;i>0;i--) 
        {
            int j=random.nextInt(i + 1);
            int temporal=indices[i];
            indices[i]=indices[j];
            indices[j]=temporal;
        }

        for (int k=0;k<vacios;k++) 
        {
            int pos=indices[k];
            int fila=pos/9;
            int col=pos%9;
            tableroJuego[fila][col]=0;
        }

        Tablero tablero = new Tablero();
        tablero.setSolutionMatrix(solucion);
        tablero.setPuzzleMatrix(tableroJuego);
        return tablero;
    }

    
    public boolean numValido(int tablero[][], int fil,int col, int valor)
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
    
    public boolean randomizador(int[][] casilla) 
    {
        int fila=-1;
        int columna=-1;
        boolean vacio= false;

        for (int i=0; i<9 && !vacio;i++) 
        {
            for (int c=0;c<9 && !vacio;c++) 
            {
                if (casilla[i][c]==0) 
                {
                    fila=i;
                    columna=c;
                    vacio=true;
                }
            }
        }
        
        if (!vacio) 
            return true;

        ArrayList<Integer> valsValidos=new ArrayList<>();
        for(int n=1; n<=9; n++)
        {
            valsValidos.add(n);
        }
        Collections.shuffle(valsValidos, random);

        for (int nums=0; nums<valsValidos.size();nums++) 
        {
            int valor=valsValidos.get(nums);
            if (numValido(casilla,fila,columna,valor)) 
            {
                casilla[fila][columna]=valor;

                if (randomizador(casilla)) 
                    return true; 
                casilla[fila][columna]=0; 
            }
        }
        return false;
    }
}
