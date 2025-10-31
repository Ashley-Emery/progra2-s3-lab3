/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2.s3.lab3;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author esteb
 */
public class Frame extends JFrame {

    private final Generador generador = new Generador();
    private Panel panel;
    private Tablero tableroActual;

    public Frame() {
        setTitle("Sudoku el Queso");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTop.add(new JLabel("Dificultades"));

        String[] opciones = {"Facil", "Media", "Dificil"};
        JComboBox<String> cmbOpciones = new JComboBox<>(opciones);
        cmbOpciones.setSelectedItem("Media");
        panelTop.add(cmbOpciones);

        JButton btnGenerar = new JButton("Generar");
        JButton btnResolver = new JButton("Resolver");
        panelTop.add(btnGenerar);
        panelTop.add(btnResolver);

        add(panelTop, BorderLayout.NORTH);

        tableroActual = generador.generadorTablero("Media");
        panel = new Panel(tableroActual);
        add(panel, BorderLayout.CENTER);

        btnGenerar.addActionListener(e -> {
            String seleccion = (String) cmbOpciones.getSelectedItem();
            tableroActual = generador.generadorTablero(seleccion);
            if (panel != null) {
                panel.setTablero(tableroActual); 
                panel.refresh();
            } else {
                panel = new Panel(tableroActual);
                add(panel, BorderLayout.CENTER);
            }
            revalidate();
            repaint();
            pack();
        });

        btnResolver.addActionListener(e -> {
            tableroActual.aplicarSolucion();
            panel.refresh();
            JOptionPane.showMessageDialog(null, "Tablero Resuelto, un noob que ocupo ayuda");
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
