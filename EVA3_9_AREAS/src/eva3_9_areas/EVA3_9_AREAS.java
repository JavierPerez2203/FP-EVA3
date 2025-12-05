/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eva3_9_areas;

import java.util.Scanner;


public class EVA3_9_AREAS {

   
    public static void main(String[] args) {
        Scanner captu = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("===== MENU DE AREAS =====");
            System.out.println("1. Calcular area del circulo");
            System.out.println("2. Calcular area del rectangulo");
            System.out.println("3. Calcular area del trapecio");
            System.out.println("4. Salir");
            System.out.print("Elija una opcion: ");
            opcion = captu.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el radio del circulo: ");
                    double radio = captu.nextDouble();
                    System.out.println("El area del círculo es: " + calcularArea(radio));
                    break;

                case 2:
                    System.out.print("Ingrese la base del rectángulo: ");
                    double base = captu.nextDouble();
                    System.out.print("Ingrese la altura del rectángulo: ");
                    double altura = captu.nextDouble();
                    System.out.println("El área del rectángulo es: " + calcularArea(base, altura));
                    break;

                case 3:
                    System.out.print("Ingrese la base mayor del trapecio: ");
                    double baseMayor = captu.nextDouble();
                    System.out.print("Ingrese la base menor del trapecio: ");
                    double baseMenor = captu.nextDouble();
                    System.out.print("Ingrese la altura del trapecio: ");
                    double h = captu.nextDouble();
                    System.out.println("El area del trapecio es: " + calcularArea(baseMayor, baseMenor, h));
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
            }

        } while (opcion != 4);

        
    }
    //Area del circulo
     public static double calcularArea(double radio) {
        return Math.PI * radio * radio;
    }

    // Area del rectángulo
    public static double calcularArea(double base, double altura) {
        return base * altura;
    }

    // Area del trapecio
    public static double calcularArea(double baseMayor, double baseMenor, double altura) {
        return ((baseMayor + baseMenor) * altura) / 2;
    }
}