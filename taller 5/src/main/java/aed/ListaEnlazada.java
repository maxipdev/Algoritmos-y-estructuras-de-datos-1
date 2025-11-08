package aed;

public class ListaEnlazada<T> {
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

    public ListaEnlazada() {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
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
            this.ultimo = nuevoNodo;

            // le agrego una longitud:
            this.size++;
        } else {
            Nodo actual = this.primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente; // podemos hacer esto ya que actual es un nodo y lo mismo com el siuginwete
                                           // pq tabienn es un nodo, es decir le estoy diciendo que mire ese nodo ahora
            }

            // le digo cual es su anterior
            nuevoNodo.anterior = actual;

            // agrego el ultimo nodo
            actual.siguiente = nuevoNodo;

            // cambio el ultimo elemento:
            this.ultimo = nuevoNodo;

            // le aumento la longitud:
            this.size++;
        }
    }

    private Nodo getNodo(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + i);
        }

        Nodo actual = primero;
        Nodo prev = null;

        for (int j = 0; j < i; j++) {
            prev = actual;
            actual = actual.siguiente;
        }

        return actual;
    }

    public T obtener(int i) {
        return getNodo(i).valor;
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

    public void modificarPosicion(int indice, T elem) {
        Nodo nodoObtenido = getNodo(indice);
        nodoObtenido.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo actual = lista.primero;
        while (actual != null) {
            agregarAtras(actual.valor);
            actual = actual.siguiente;
        }
    }

    @Override
    public String toString() {
        String texto = "[";

        // itero mis elementos:
        boolean primerElemento = true;
        ListaIterador it = iterador();
        while (it.haySiguiente()) {
            if (!primerElemento) {
                texto += " ";
            } else {
                primerElemento = false;
            }
            texto += it.siguiente().toString();

            if (it.haySiguiente()) {
                texto += ",";
            }
        }

        texto += "]";

        return texto;
    }

    public class ListaIterador {
        // si avanza con el siguiente da el elemento actual y cuando hace un volver para
        // atras te da el actual
        private Nodo actual; // va a ser el elemento en el cual vamso a crear las cosas
        private Nodo ultimoDevuelto;

        public ListaIterador() {
            this.actual = primero; // arranca al principio de la lista
            this.ultimoDevuelto = null; // aún no devolvió nada
        }

        public boolean haySiguiente() {
            return (actual != null);
        }

        public boolean hayAnterior() {
            return ultimoDevuelto != null;
        }

        public T siguiente() {
            ultimoDevuelto = actual; // cargo el ultimo elemento
            actual = actual.siguiente; // digo que pase al siguente nodo
            return ultimoDevuelto.valor; // devuelvo el nodo que se busco
        }

        public T anterior() {
            T valor = ultimoDevuelto.valor;
            actual = ultimoDevuelto; // el cursor vuelve a ese nodo
            ultimoDevuelto = ultimoDevuelto.anterior; // el “último devuelto” retrocede uno
            return valor;
        }
    }

    public ListaIterador iterador() {
        return new ListaIterador();
    }

}
