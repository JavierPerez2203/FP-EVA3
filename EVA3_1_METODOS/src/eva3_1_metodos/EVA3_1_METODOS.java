/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eva3_1_metodos;


public class EVA3_1_METODOS {

    
    public static void main(String[] args) {
        int val1 = 5, val2 =10;
        //1. si necesito guardar el valor de retorno
        int resu= sumarEnteros(val1, val2);//invocar valor
        System.out.println("suma de 5 + 10 = " + resu);
        //2. no necesito guardar el valor, un solo uso
        System.out.println("suma de 5 + 10 = " + sumarEnteros (val1, val2));
        //3.solo necesito ejecutar elmetodo, no interesa el valor
        sumarEnteros(val1, val2);
    }
  //metodo para sumar dos enteros
  //llamar sus metodos usando verbos y escritura camelCase
  //1. modificadores de acceso: public,protected,private,
  //2. static-->  para usar los metodos dentro del main
  //3. valor de retorno: cualquier tipo de dato
  // void--> si no quieren regresar un valor
  //4. nombre de metodo
  //5. lista de parametros:pueden ser cero o N cantidad  
  //  1       2    3       4               5
    public static int sumarEnteros(int num1, int num2){
        int suma = num1 + num2;
        return suma;
        
    }
            
    
    
}
