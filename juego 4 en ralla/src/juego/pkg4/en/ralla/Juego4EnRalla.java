/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juego.pkg4.en.ralla;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends JFrame {

    private final int FILAS = 6;
    private final int COLUMNAS = 7;
    private JButton[][] botones = new JButton[FILAS][COLUMNAS];

    private int turno = 1;  // 1 = Humano (rojo), 2 = IA (amarillo)
    private boolean modoIA = true;

    private final Color colorHumano = Color.RED;
    private final Color colorIA = Color.YELLOW;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    public Main() {
        setTitle("4 en Raya con IA");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(FILAS, COLUMNAS));

        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {

                JButton btn = new JButton();
                btn.setBackground(Color.WHITE);
                btn.setOpaque(true);
                btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                final int columnaFinal = col;

                btn.addActionListener(e -> {
                    if (turno == 1) {
                        colocarFichaHumano(columnaFinal);
                    }
                });

                botones[fila][col] = btn;
                panel.add(btn);
            }
        }

        add(panel);
    }

    //----------------------------------
    // MOVIMIENTO DEL HUMANO
    //----------------------------------
    private void colocarFichaHumano(int col) {
        if (colocarFicha(col, colorHumano)) {
            if (turno == 2 && modoIA) {
                movimientoIA();
            }
        }
    }

    //----------------------------------
    // COLOCAR FICHA EN UNA COLUMNA
    //----------------------------------
    private boolean colocarFicha(int col, Color color) {
        for (int fila = FILAS - 1; fila >= 0; fila--) {
            if (botones[fila][col].getBackground() == Color.WHITE) {

                botones[fila][col].setBackground(color);

                if (ganar(fila, col, color)) {
                    anunciarGanador(color == colorHumano ? "HUMANO" : "IA");
                    return false;
                }

                turno = (turno == 1) ? 2 : 1;
                return true;
            }
        }
        return false;
    }

    //----------------------------------
    // MOVIMIENTO DE LA IA
    //----------------------------------
    private void movimientoIA() {
        new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                int mejorCol = obtenerMovimientoIA();
                colocarFicha(mejorCol, colorIA);

                ((Timer) evt.getSource()).stop();
            }
        }).start();
    }

    private int obtenerMovimientoIA() {

        // 1. Si la IA puede ganar → ganar
        for (int col = 0; col < COLUMNAS; col++) {
            if (simularJugada(col, colorIA)) {
                return col;
            }
        }

        // 2. Si el humano puede ganar → bloquear
        for (int col = 0; col < COLUMNAS; col++) {
            if (simularJugada(col, colorHumano)) {
                return col;
            }
        }

        // 3. Si no, columna aleatoria válida
        List<Integer> validas = new ArrayList<>();
        for (int col = 0; col < COLUMNAS; col++) {
            if (columnaDisponible(col)) {
                validas.add(col);
            }
        }

        Random rand = new Random();
        return validas.get(rand.nextInt(validas.size()));
    }

    //----------------------------------
    // SIMULAR UNA JUGADA
    //----------------------------------
    private boolean simularJugada(int col, Color color) {
        for (int fila = FILAS - 1; fila >= 0; fila--) {
            if (botones[fila][col].getBackground() == Color.WHITE) {

                botones[fila][col].setBackground(color);
                boolean gana = ganar(fila, col, color);
                botones[fila][col].setBackground(Color.WHITE);

                return gana;
            }
        }
        return false;
    }

    private boolean columnaDisponible(int col) {
        return botones[0][col].getBackground() == Color.WHITE;
    }

    //----------------------------------
    // VERIFICAR SI ALGUIEN GANA
    //----------------------------------
    private boolean ganar(int fila, int col, Color color) {
        return (contar(fila, col, 1, 0, color) + contar(fila, col, -1, 0, color) >= 3 || // vertical
                contar(fila, col, 0, 1, color) + contar(fila, col, 0, -1, color) >= 3 || // horizontal
                contar(fila, col, 1, 1, color) + contar(fila, col, -1, -1, color) >= 3 || // diagonal \
                contar(fila, col, 1, -1, color) + contar(fila, col, -1, 1, color) >= 3);  // diagonal /
    }

    private int contar(int fila, int col, int df, int dc, Color color) {
        int cont = 0;
        int f = fila + df;
        int c = col + dc;

        while (f >= 0 && f < FILAS && c >= 0 && c < COLUMNAS &&
                botones[f][c].getBackground() == color) {
            cont++;
            f += df;
            c += dc;
        }

        return cont;
    }

    //----------------------------------
    // GANADOR Y REINICIO
    //----------------------------------
    private void anunciarGanador(String ganador) {
        JOptionPane.showMessageDialog(this, "¡Ganó " + ganador + "!");
        reiniciarTablero();
    }

    private void reiniciarTablero() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                botones[fila][col].setBackground(Color.WHITE);
            }
        }


