package com.example.gestorcompeticionsuf5uf6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioCompeticions extends JFrame {
    private static final int CODIGO = 0;
    private JComboBox<String> tipusCompeticio;
    private JTextField numEquips;
    private JComboBox<String> categoria;
    private JComboBox<String> genere;
    private JTextArea output;

    public GestioCompeticions() {
        setTitle("Gestió de Competicions");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        tipusCompeticio = new JComboBox<>(new String[]{"Lliga", "Eliminatoria"});
        numEquips = new JTextField(5);
        categoria = new JComboBox<>(new String[]{"Benjamí", "Infantil", "Junior", "sub-20", "sub-25", "Senior"});
        genere = new JComboBox<>(new String[]{"Masculí", "Femení"});
        JButton crearCompeticio = new JButton("Crear Competició");
        output = new JTextArea(10, 30);
        output.setEditable(false);

        crearCompeticio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCompeticio();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.add(new JLabel("Tipus Competició:"));
        panel.add(tipusCompeticio);
        panel.add(new JLabel("Número Equips:"));
        panel.add(numEquips);
        panel.add(new JLabel("Categoria:"));
        panel.add(categoria);
        panel.add(new JLabel("Gènere:"));
        panel.add(genere);
        panel.add(crearCompeticio);

        Container container = getContentPane();
        container.add(panel, BorderLayout.NORTH);
        container.add(new JScrollPane(output), BorderLayout.CENTER);
    }

    private void crearCompeticio() {
        String tipus = (String) tipusCompeticio.getSelectedItem();
        String numEquipsStr = numEquips.getText();
        String categoriaValue = (String) categoria.getSelectedItem();
        String genereValue = (String) genere.getSelectedItem();

        try {
            int numEquipsValue = Integer.parseInt(numEquipsStr);
            if (numEquipsValue <= 0) {
                mostrarMensajeDeError("El número de equipos debe ser un entero positivo.");
                return;
            }
            if ("Lliga".equals(tipus)) {
                try {
                    Lliga lliga = new Lliga(CODIGO, tipus, numEquipsValue, categoriaValue, genereValue);
                    output.append("Competició Creada de tipus " + tipus + " amb " + numEquipsValue + " equips, categoria " + categoriaValue + " i gènere " + genereValue + ".\n");
                } catch (Lliga.MaxTeamsExceededException ex) {
                    mostrarMensajeDeError(ex.getMessage());
                }
            } else {
                Eliminatoria eliminatoria = new Eliminatoria(CODIGO, tipus, numEquipsValue, categoriaValue, genereValue);
                output.append("Competició Creada de tipus " + tipus + " amb " + numEquipsValue + " equips, categoria " + categoriaValue + " i gènere " + genereValue + ".\n");
            }
        } catch (NumberFormatException ex) {
            mostrarMensajeDeError("Número de equips inválido");
        }
    }


    private void mostrarMensajeDeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestioCompeticions().setVisible(true);
            }
        });
    }
}

