/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eva3_3_menu;

import java.util.Scanner;


public class EVA3_3_MENU {

   
    public static void main(String[] args) {
        Scanner captu = new Scanner(System.in);
        int opcion;
        do{
            imprimirMenu();
            opcion = captu.nextInt();
            if(opcion ==1){
                calcularPotencia();
            }else if(opcion ==2){
                
            }
        }while(opcion != 3);
        
        
        
    }
    public static void imprimirMenu(){
        System.out.println("bienvenido al sistema");
        System.out.println("selecciona una opcion");
        System.out.println("1.calcular una potencia");
        System.out.println("2. sumar dos numeros");
        System.out.println("3. salir del sistema");
        System.out.println("selecciona una opcion");
        
    }
     public static void calcularPotencia(){
     Scanner captu = new Scanner(System.in);
     int base, exp;
         System.out.println("*****calculo de una potencia*****");
         System.out.println("captura la base");
         base = captu.nextInt();
         System.out.println("captura el exponente");
         exp = captu.nextInt();
         //pendiente el calculo
         System.out.println(base + "elevado a " + exp + "=" + calcularPot(base,exp));
     }  
     public static int calcularPot(int base,int expo){
         int resu = 1;
         for(int i = 1; i < expo; i++){
             resu = resu * base;     
     }
         return resu;
     }
}
