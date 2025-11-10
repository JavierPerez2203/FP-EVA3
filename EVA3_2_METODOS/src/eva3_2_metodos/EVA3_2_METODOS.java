/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eva3_2_metodos;

import java.util.Scanner;


public class EVA3_2_METODOS {

   
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        String mensaje;
        int valor;
        System.out.println("captura mensaje");
        mensaje = input.nextLine();
        System.out.println("captura repeticiones");
        valor = input.nextInt ();
        imprimirMensaje(mensaje,valor);
        }
    public static void imprimirMensaje(String mensaje,int repeticiones){
        for (int i = 0; i < repeticiones; i++){
            System.out.println(mensaje);
    
    }
       
    }
}
