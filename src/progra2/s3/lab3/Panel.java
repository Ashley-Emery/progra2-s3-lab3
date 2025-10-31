/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2.s3.lab3;

import java.awt.*;
import java.awt.event.FocusAdapter;
import javax.swing.*;

/**
 *
 * @author esteb
 */
public class Panel extends JPanel {

    private Tablero tablero;
    private final JTextField[][] espacios = new JTextField[9][9];

    public Panel(Tablero tablero) {
        this.tablero = tablero;
        setLayout(new GridLayout(9, 9));
        Grid();
    }

    public void setTablero(Tablero nuevo) {
        this.tablero = nuevo;
        Grid();
    }

    private void Grid() {
        removeAll();
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                JTextField txtCasilla = new JTextField();
                txtCasilla.setHorizontalAlignment(JTextField.CENTER);
                txtCasilla.setFont(txtCasilla.getFont().deriveFont(18f));

                int valor = tablero.getValor(fila, columna);
                txtCasilla.setText(valor == 0 ? "" : String.valueOf(valor));
                txtCasilla.setEditable(!tablero.isFijo(fila, columna));

                txtCasilla.setBorder(BorderFactory.createMatteBorder(
                        (fila % 3 == 0 ? 3 : 1),
                        (columna % 3 == 0 ? 3 : 1),
                        (fila == 8) ? 3 : 1,
                        (columna == 8) ? 3 : 1, Color.BLACK));

                final int Fila = fila, Columna = columna;

                txtCasilla.addActionListener(e -> procesarEntrada(txtCasilla, Fila, Columna));

                txtCasilla.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusLost(java.awt.event.FocusEvent e) {
                        procesarEntrada(txtCasilla, Fila, Columna);
                    }
                });
                espacios[fila][columna] = txtCasilla;
                add(txtCasilla);
            }
        }
        revalidate();
        repaint();
    }

     private void procesarEntrada(JTextField txtCasilla, int Fila, int Columna) {
        String texto = txtCasilla.getText().trim();
        int intento;

        try {
            intento = texto.isEmpty() ? 0 : Integer.parseInt(texto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Introduce un numero entre 1 y 9");
            txtCasilla.setText("");
            return;
        }

        if (intento == 0) {
            tablero.setValorVacio(Fila, Columna);
            txtCasilla.setText("");
            return;
        }

        if (tablero.isFijo(Fila, Columna)) {
            JOptionPane.showMessageDialog(null, "Esta Casilla es fija");
            txtCasilla.setText(String.valueOf(tablero.getValor(Fila, Columna)));
            return;
        }

        if (!tablero.isMovimientoValido(Fila, Columna, intento)) {
            JOptionPane.showMessageDialog(null, "Movimiento invalido segun reglas");
        }

        boolean correcto = tablero.isValorCorrecto(Fila, Columna, intento);
        if (correcto) {
            tablero.setValor(Fila, Columna, intento);

        } else {
            tablero.setValorVacio(Fila, Columna);
            txtCasilla.setText("");
            JOptionPane.showMessageDialog(null, "Valor incorrecto");
        }
    }
    public void refresh() {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                int v = tablero.getValor(fila, columna);
                espacios[fila][columna].setText(v == 0 ? "" : String.valueOf(v));
            }
        }
    }
}
