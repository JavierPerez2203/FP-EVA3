/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juego.pkg4.en.ralla;

import java.util.Random;
import java.util.Scanner;

public class Juego4EnRalla {

    static final int FILAS = 6;
    static final int COLUMNAS = 7;
    static char[][] tablero = new char[FILAS][COLUMNAS];
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    // ==== COLORES ANSI ====
    static final String RESET = "\u001B[0m";
    static final String ROJO = "\u001B[31m";
    static final String AZUL = "\u001B[34m";
    static final String AMARILLO = "\u001B[33m";
    static final String VERDE = "\u001B[32m";

    static final String COLOR_X = ROJO;
    static final String COLOR_O = AZUL;

    // ==== COLORES DE FONDO ====
    static final String BG_NEGRO = "\u001B[40m";
    static final String BG_ROJO = "\u001B[41m";
    static final String BG_VERDE = "\u001B[42m";
    static final String BG_AMARILLO = "\u001B[43m";
    static final String BG_AZUL = "\u001B[44m";
    static final String BG_MAGENTA = "\u001B[45m";
    static final String BG_CYAN = "\u001B[46m";
    static final String BG_BLANCO = "\u001B[47m";

    // Fondo actual del tablero
    static String FONDO_TABLERO = BG_ROJO;

    // ==== PUNTUACIÓN ====
    static int puntuacionJugador = 0;
    static int puntuacionIA = 0;
    static int puntuacionJugador2 = 0;

    public static void main(String[] args) {

        boolean jugarOtraVez = true;

        while (jugarOtraVez) {

            // ===== MENÚ DE MODOS =====
            System.out.println(AMARILLO + "=== MODOS DE JUEGO ===" + RESET);
            System.out.println("1. Jugador vs Jugador");
            System.out.println("2. Jugador vs IA (fácil)");
            System.out.println("3. Jugador vs IA (difícil)");
            System.out.println("4. Modo rápido (3 en línea)");
            System.out.println("5. Modo infinito");
            System.out.print("Elige un modo: ");
            int modo = scanner.nextInt();

            int objetivo = (modo == 4) ? 3 : 4;
            boolean contraIA = (modo == 2 || modo == 3);
            boolean modoInfinito = (modo == 5);

            inicializarTablero();

            // ===== VARIABLES DE JUEGO =====
            boolean juegoTerminado = false;
            char jugadorHumano = 'X';
            char jugadorIA = 'O';
            char jugadorActual = jugadorHumano;

            while (!juegoTerminado) {

                imprimirTablero();
                int columna;

                // ---- TURNO IA ----
                if (contraIA && jugadorActual == jugadorIA) {

                    if (modo == 2)
                        columna = elegirColumnaIA(jugadorIA, jugadorHumano);       // IA fácil
                    else
                        columna = elegirColumnaIADificil(jugadorIA, jugadorHumano); // IA difícil

                    System.out.println("La IA elige columna " + (columna + 1));

                } else {
                    // ---- TURNO HUMANO ----
                    System.out.println("Turno del jugador " + jugadorActual + " (0 para cambiar fondo)");
                    System.out.print("Elige una columna (1-7): ");
                    try {
                        columna = scanner.nextInt() - 1;

                        if (columna == -1) { // Si ingresa 0
                            cambiarFondo();
                            imprimirTablero();
                            continue;
                        }

                    } catch (Exception e) {
                        System.out.println("Entrada inválida.");
                        scanner.next();
                        continue;
                    }
                }

                if (columna < 0 || columna >= COLUMNAS) {
                    System.out.println("Columna fuera de rango.");
                    continue;
                }

                if (!colocarFicha(columna, jugadorActual)) {
                    System.out.println("Columna llena.");
                    continue;
                }

                // ===== CHEQUEO GANADOR =====
                if (verificarGanador(jugadorActual, objetivo)) {
                    cambiarFondo(); // Cambiar fondo solo al finalizar la partida
                    imprimirTablero();

                    if (contraIA && jugadorActual == jugadorIA) {
                        System.out.println(VERDE + "¡La IA gana!" + RESET);
                        puntuacionIA++;
                    } else {
                        System.out.println(VERDE + "¡El jugador " + jugadorActual + " gana!" + RESET);
                        if (jugadorActual == 'X') puntuacionJugador++;
                        else puntuacionJugador2++;
                    }

                    // Mostrar marcador
                    System.out.println(AMARILLO + "\n=== MARCADOR ===");
                    System.out.println("Jugador X: " + puntuacionJugador);
                    System.out.println("Jugador O: " + (contraIA ? puntuacionIA : puntuacionJugador2) + RESET);

                    juegoTerminado = true;

                } else if (tableroLleno()) {
                    cambiarFondo(); // Cambiar fondo al finalizar la partida (empate)
                    imprimirTablero();
                    System.out.println(AMARILLO + "¡Empate!" + RESET);
                    juegoTerminado = true;
                } else {
                    jugadorActual = (jugadorActual == jugadorHumano) ? jugadorIA : jugadorHumano;
                }
            }

            // ===== PREGUNTAR SI QUIERE JUGAR OTRA VEZ =====
            System.out.print(AMARILLO + "¿Quieres jugar otra vez? (s/n): " + RESET);
            String respuesta = scanner.next();
            if (!respuesta.equalsIgnoreCase("s")) {
                jugarOtraVez = false;
                System.out.println(VERDE + "¡Gracias por jugar!" + RESET);
            } else {
                inicializarTablero();
            }
        }

        scanner.close();
    }

    

    // ==== ANIMACIÓN DE FICHA ====
    static boolean colocarFicha(int columna, char jugador) {
        for (int f = 0; f < FILAS; f++) {
            if (tablero[0][columna] != '.') return false;

            if (tablero[f][columna] != '.') {
                int finalFila = f - 1;
                animarCaida(finalFila, columna, jugador);
                tablero[finalFila][columna] = jugador;
                return true;
            }
        }

        animarCaida(FILAS - 1, columna, jugador);
        tablero[FILAS - 1][columna] = jugador;

        return true;
    }

    static void animarCaida(int filaFinal, int columna, char jugador) {
        for (int f = 0; f <= filaFinal; f++) {
            tablero[f][columna] = jugador;
            imprimirTablero();
           

            try { Thread.sleep(100); } catch (Exception e) {}

            tablero[f][columna] = '.';
        }
    }

    // ==== IA FÁCIL ====
    static int elegirColumnaIA(char ia, char humano) {
        for (int col = 0; col < COLUMNAS; col++) {
            if (columnaDisponible(col)) {
                int fila = filaDisponible(col);
                tablero[fila][col] = ia;
                if (verificarGanador(ia, 4)) {
                    tablero[fila][col] = '.';
                    return col;
                }
                tablero[fila][col] = '.';
            }
        }

        for (int col = 0; col < COLUMNAS; col++) {
            if (columnaDisponible(col)) {
                int fila = filaDisponible(col);
                tablero[fila][col] = humano;
                if (verificarGanador(humano, 4)) {
                    tablero[fila][col] = '.';
                    return col;
                }
                tablero[fila][col] = '.';
            }
        }

        int c;
        do {
            c = random.nextInt(COLUMNAS);
        } while (!columnaDisponible(c));

        return c;
    }

    // ==== IA DIFÍCIL ====
    static int elegirColumnaIADificil(char ia, char humano) {
        int col = elegirColumnaIA(ia, humano);
        if (col != -1) return col;

        if (columnaDisponible(COLUMNAS / 2)) return COLUMNAS / 2;

        for (int c = 0; c < COLUMNAS; c++) {
            if (columnaDisponible(c)) {
                int fila = filaDisponible(c);
                tablero[fila][c] = ia;
                tablero[fila][c] = '.';
                return c;
            }
        }

        return elegirColumnaIA(ia, humano);
    }

    // ==== FUNCIONES NORMALES =====
    static boolean columnaDisponible(int col) {
        return tablero[0][col] == '.';
    }

    static int filaDisponible(int col) {
        for (int f = FILAS - 1; f >= 0; f--)
            if (tablero[f][col] == '.') return f;
        return -1;
    }

    static void inicializarTablero() {
        for (int f = 0; f < FILAS; f++)
            for (int c = 0; c < COLUMNAS; c++)
                tablero[f][c] = '.';
    }

    static void imprimirTablero() {
        System.out.println("\n");

        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                char x = tablero[f][c];
                if (x == 'X') System.out.print(FONDO_TABLERO + COLOR_X + "X " + RESET);
                else if (x == 'O') System.out.print(FONDO_TABLERO + COLOR_O + "O " + RESET);
                else System.out.print(FONDO_TABLERO + ". " + RESET);
            }
            System.out.println();
        }

        System.out.println("1 2 3 4 5 6 7");
    }

    static boolean verificarGanador(char jugador, int objetivo) {

        for (int f = 0; f < FILAS; f++)
            for (int c = 0; c <= COLUMNAS - objetivo; c++) {
                boolean ok = true;
                for (int k = 0; k < objetivo; k++)
                    if (tablero[f][c + k] != jugador) ok = false;
                if (ok) return true;
            }

        for (int c = 0; c < COLUMNAS; c++)
            for (int f = 0; f <= FILAS - objetivo; f++) {
                boolean ok = true;
                for (int k = 0; k < objetivo; k++)
                    if (tablero[f + k][c] != jugador) ok = false;
                if (ok) return true;
            }

        for (int f = 0; f <= FILAS - objetivo; f++)
            for (int c = 0; c <= COLUMNAS - objetivo; c++) {
                boolean ok = true;
                for (int k = 0; k < objetivo; k++)
                    if (tablero[f + k][c + k] != jugador) ok = false;
                if (ok) return true;
            }

        for (int f = objetivo - 1; f < FILAS; f++)
            for (int c = 0; c <= COLUMNAS - objetivo; c++) {
                boolean ok = true;
                for (int k = 0; k < objetivo; k++)
                    if (tablero[f - k][c + k] != jugador) ok = false;
                if (ok) return true;
            }

        return false;
    }

    static boolean tableroLleno() {
        for (int c = 0; c < COLUMNAS; c++)
            if (tablero[0][c] == '.') return false;
        return true;
    }

    // ==== CAMBIO DE FONDO ====
    static void cambiarFondo() {
        String[] fondos = {BG_NEGRO, BG_ROJO, BG_VERDE, BG_AMARILLO, BG_AZUL, BG_MAGENTA, BG_CYAN, BG_BLANCO};
        int indice;
        do {
            indice = random.nextInt(fondos.length);
        } while (fondos[indice].equals(FONDO_TABLERO)); // Evita repetir el mismo color
        FONDO_TABLERO = fondos[indice];
    }

}







