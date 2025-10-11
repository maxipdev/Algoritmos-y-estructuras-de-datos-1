
public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int size;

    private class Nodo {
        private T valor;
        private Nodo siguiente;
        private Nodo anterior;

        // que esto sea publico o privado no cambia en nada, ayq ue la clase de por si
        // es privada => no permite que se use afuera de ListaEnlazada
        private Nodo(T v) {
            this.valor = v;
            this.siguiente = null;
            this.anterior = null;
        }

    }

    // creo una lista vacia
    public ListaEnlazada() {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo actual = lista.primero;
        this.size = lista.size; // le cargo la longitud de la lista
        while (actual != null) {
            agregarAtras(actual.valor);
            actual = actual.siguiente;
        }
    }

    public int longitud() {
        return this.size;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevoNodo = new Nodo(elem);
        // tengo que configurar el nodo manualmente, ya que el contructor no lo hace
        if (primero != null) {
            this.primero.anterior = nuevoNodo; // le cargo quen va a ser su anterior
            nuevoNodo.siguiente = this.primero; // le cargo quen va a ser su siguiente
        } else {
            nuevoNodo.siguiente = null; // le digo que no hay ningun otro elemento, ya que si no hay primero no hay nada
        }

        // esto siempre tiene que pasar
        this.primero = nuevoNodo;

        // cubro el caso de que no existiera el ultimo
        if (ultimo == null) {
            this.ultimo = nuevoNodo; // esto es una excepcion ya que si no lo tenemos lo agrego
        }

        // le aumento la longitud:
        this.size++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevoNodo = new Nodo(elem); // creamos el nodo correspondiente
        // en caso de que no haya un primer nodo o el ultimo
        if (primero == null || ultimo == null) {
            this.primero = nuevoNodo;

            // le agrego una longitud:
            this.size++;
        } else {
            Nodo actual = this.primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente; // podemos hacer esto ya que actual es un nodo y lo mismo com el siuginwete
                                           // pq tabienn es un nodo, es decir le estoy diciendo que mire ese nodo ahora
            }
            // agrego el ultimo nodo
            actual.siguiente = nuevoNodo;

            // cambio el ultimo elemento:
            this.ultimo = nuevoNodo;

            // le aumento la longitud:
            this.size++;
        }
    }

    public void eliminar(int i) {
        // verifico que la i pasada este dentro del rango de los disponibles, en caso
        // contrario no hago nada:
        if (!(0 <= i && i < longitud())) {
            return; // corto la ejecucion de la función
        }

        Nodo actual = primero;
        Nodo prev = null;
        boolean estado = false;

        // lo que hace es llegar hasdta la posicion pedida
        for (int j = 0; j < i; j++) {
            prev = actual;
            actual = actual.siguiente;
        }

        // hay que seprar los casos en los que i == 0 ya que quermos borrar el primer
        // nodo
        if (i == 0 && longitud() > 1) {
            this.primero = actual.siguiente; // cambia de lugar al primero
            this.primero.anterior = null; // ahora le digo que el primer elemento va a ser null en su anteriror
            estado = true;
        }

        // caso en el que la lista tiene un solo elemento, al borralo ya no hay nada
        else if (i == 0 && longitud() == 1) {
            this.primero = null;
            this.ultimo = null; // tambien borra el ultimo
            estado = true;
        }

        else { // yo quiero borra el que dice ACTUAL, que ese es mi elemento solicitdado
            if (actual.siguiente != null) { // es decir que tiene siguiente
                prev.siguiente = actual.siguiente; // hago que se salteen el elemento que quiero eliminar
                (actual.siguiente).anterior = prev; // lo que hago es mofificar que el siguiente elemento del que
                                                    // queremos borrar se conecte con el elemento anterior del que
                                                    // queriamos borrar
            } else { // coso que sea el ultimo elemento => no tiene un siguiente
                prev.siguiente = null;
                // despues no hay que hacer más cambios ya que si esta en la ultima posion no
                // hay que modificar el anterior ya que no existe el sigueinte que seria el que
                // tenemos que modificarlo
            }

            // siempre ejecutamso esto ya que pase lo que pase siempre se va a terminar
            // borrando en este caso
            estado = true;
        }

        // le disminuyo la longitud:
        if (estado) {
            this.size--;
        }
    }

    public Iterador<T> iterador() {
        return new Iterador();
    }

    private class Iterador implements Iterador<T> {
        Nodo actual; // va a ser el elemento en el cual vamso a crear las cosas
        Nodo alReves; // creo el nodo que va al reves

        private Iterador() {
            this.actual = this.primero;
            this.alReves = this.ultimo;
        }

        public boolean haySiguiente() {
            if (actual.siguiente != null) {
                return true;
            } else {
                return false;
            }
        }

        public boolean hayAnterior() {
            if (alReves.anterior != null) {
                return true;
            } else {
                return false;
            }
        }

        public T siguiente() {
            Nodo elemento = actual;
            actual = actual.siguiente; // digo que pase al siguente nodo
            return elemento.valor; // devuelvo el nodo que se busco
        }

        public T anterior() {
            Nodo elemento = alReves;
            alReves = alReves.anterior; // digo que pase al anterior nodo
            return elemento.valor; // devuelvo el nodo que se busco
        }
    }
}