package aed;

public class Main {
    public static void main(String[] args) {
        Funciones f = new Funciones();
         System.out.println(f.cuadrado(5));

         System.out.println(f.distancia(2, 2));
         System.out.println(f.esPar(13));
         System.out.println(f.esBisiesto(1900));

         System.out.println(f.factorialIterativo(5));
         System.out.println(f.factorialRecursivo(5));

         System.out.println(f.esPrimo(17));

         System.out.println(f.sumatoria(new int[] {3, 2, 3}));

         System.out.println(f.esPrefijo("abc", "abcdefg"));

         System.out.println(f.todosPares(new int[]{6, 2, 14, 20}));

         System.out.println(f.iguales(new int[]{6, 2, 14, 20}, new int[]{6, 2, 14, 20}));

        System.out.println(f.ordenado(new int[]{-3,-2,-1}));
    }
}
