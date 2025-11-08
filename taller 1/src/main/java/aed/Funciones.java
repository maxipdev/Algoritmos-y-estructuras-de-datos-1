package aed;

class Funciones {

/***  Primera parte: Funciones en java ***/

    int cuadrado(int x) {
        int multiplicacion = x * x;
        return multiplicacion;
    }

    double distancia(double x, double y) {
        double dobleX = x * x;
        double dobleY = y * y;

        double raiz = Math.sqrt((dobleX + dobleY));

        return raiz;
    }

    boolean divideA(int a, int b) {
        // quiero ver si a divide a b
        return b % a == 0;
    }

    boolean esPar(int n) {
        return divideA(2, n);
    }

    boolean esBisiesto(int n) {
        // COMPLETAR
        return (divideA(4, n) && !(divideA(100, n))) || divideA(400, n);
    }

    int factorialIterativo(int n) {
        int total = 1; // Aca se pueden ver los factoriales y cubre el caso base del 1
        if (n != 0) {
            for (int i = 1; i <= n; i++) {
                total = total* i;
            }   
        }

        return total;
    }

    int factorialRecursivo(int n) {
        int total = 1;
        if (n == 0) {
            return total;
        } else {
            return n * factorialRecursivo((n-1));
        }
    }

    int cantDivisores(int n) {
        int count = 0;
        for (int i= 1; i <= n; i++) {
            if (divideA(i, n)) {
                count += 1;
            }
        }
        return count;
    }

    boolean esPrimo(int n) {
        return (cantDivisores(n) == 2);
    }

    int sumatoria(int[] numeros) {
        int total = 0;
        for (int i = 0; i < numeros.length; i++) {
            total += numeros[i];
        }
        return total;
    }

    int busqueda(int[] numeros, int buscado) {
        for (int i = 0; i < numeros.length; i++) {
            int num = numeros[i];
            if (num == buscado) {
                return i;
            }
        }

        return -1;
    }

    boolean tienePrimo(int[] numeros) {
        for (int i = 0; i < numeros.length; i++) {
            if (esPrimo(numeros[i])) {
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        for (int i = 0; i < numeros.length; i++) {
            if (! esPar(numeros[i])) {
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false; // se da asi pq no podria funcionar de la otra manera
        }

        // como se supone que s1 esta dentro de s2, estonces evaluo ya suponiendo eso
        for (int i = 0; i < s1.length(); i++) {
            if (!(s1.charAt(i) == s2.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    boolean esSufijo(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false; // se da asi pq no podria funcionar de la otra manera
        }

        for (int i = 0; i < s1.length(); i++) {
            // comparar desde el final
            if (s1.charAt(s1.length() - 1 - i) != s2.charAt(s2.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

/***  Segunda parte: Debugging ***/

    boolean xor(boolean a, boolean b) {
        return (a || b) && !(a && b);
    }

    boolean iguales(int[] xs, int[] ys) {
        boolean res = true;

        if (xs.length != ys.length) {
            return false;
        }

        for (int i = 0; i < xs.length; i++) {
            if (xs[i] != ys[i]) {
                res = false;
            }
        }
        return res;
    }

    boolean ordenado(int[] xs) {
        boolean res = true;
        for (int i = 0; i < xs.length -1; i++) {
            if (xs[i] > xs [i+1]) {
                return false;
            }
        }
        return res;
    }

    int maximo(int[] xs) {
        int res = xs[0];
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] > res) {
                res = xs[i];
            }
        }
        return res;
    }

    boolean todosPositivos(int[] xs) {
        boolean res = true;
        for (int x : xs) {
            if (x > 0) {
                res = true;
            } else {
                return false;
            }
        }
        return res;
    }

}
