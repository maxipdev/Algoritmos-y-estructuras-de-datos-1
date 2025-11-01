package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo puntero;
    private int tamaño;
    private Nodo minimo;
    private Nodo maximo;

    private class Nodo {
        private T valor;
        private Nodo padre;
        private Nodo izquierda;
        private Nodo derecha;

        // Crear Constructor del nodo

        private Nodo(T v, Nodo padre) {
            this.valor = v;
            this.izquierda = null;
            this.derecha = null;
            this.padre = padre;
        }
    }

    public ABB() {
        puntero = null; // digo que el arbol esta vacío
        tamaño = 0;
    }

    public int cardinal() {
        return this.tamaño;
    }

    public T minimo() {
        System.out.println(this.minimo.valor);

        return this.minimo.valor;
    }

    public T maximo() {
        System.out.println(this.maximo.valor);
        return this.maximo.valor;
    }

    private Nodo buscarUltimoHijo(Nodo nodoActual, T elemento, Nodo nodoAnterior) {
        if (nodoActual == null) {
            return nodoAnterior;
        } else if (nodoActual.valor.compareTo(elemento) > 0) {
            return buscarUltimoHijo(nodoActual.izquierda, elemento, nodoActual);
        } else if (nodoActual.valor.compareTo(elemento) < 0) {
            return buscarUltimoHijo(nodoActual.derecha, elemento, nodoActual);
        } else { // en caso de que sean iguales
            return nodoAnterior;
        }
    }

    public void insertar(T elem) {
        if (pertenece(elem)) {
            return;
        }

        if (puntero == null) {
            puntero = new Nodo(elem, null);
            minimo = puntero;
            maximo = puntero;
            tamaño += 1;
        } else {
            Nodo nodoPadre = buscarUltimoHijo(puntero, elem, null);
            Nodo nuevoNodo = new Nodo(elem, nodoPadre);
            if (nodoPadre.valor.compareTo(elem) > 0) { // significa que el padre es más grande que el elemento
                nodoPadre.izquierda = nuevoNodo; // al hijo le agrego el padre
                tamaño += 1;
            } else if (nodoPadre.valor.compareTo(elem) < 0) {
                nodoPadre.derecha = nuevoNodo; // al hijo le agrego el padre
                tamaño += 1;
            } else {
                return; // sigfinica que el elemento ya estaba
            }

            if (maximo.valor.compareTo(elem) < 0) { // en caso de que el nuevo elemento sea más grande
                maximo = nuevoNodo;
            }
            if (minimo.valor.compareTo(elem) > 0) {
                minimo = nuevoNodo;
            }
        }
    }

    private boolean buscador(Nodo nodo, T elemento) {
        if (nodo == null)
            return false; // ya no existe pq llego al final y no lo encontro

        if (nodo.valor.compareTo(elemento) == 0) {
            return true;
        } else if (nodo.valor.compareTo(elemento) > 0) {
            return buscador(nodo.izquierda, elemento);
        } else {
            return buscador(nodo.derecha, elemento);
        }
    }

    public boolean pertenece(T elem) {
        return buscador(puntero, elem);
    }

    private Nodo buscarNodo(Nodo nodoActual, T elemento) {
        if (nodoActual.valor.compareTo(elemento) == 0) {
            return nodoActual;
        } else if (nodoActual.valor.compareTo(elemento) > 0) {
            return buscarNodo(nodoActual.izquierda, elemento);
        } else {
            return buscarNodo(nodoActual.derecha, elemento);
        }
    }

    // buscamos el más grande de la rama izquierda:
    private Nodo buscarPredecesor(Nodo nodo, Nodo nodoAnterior) {
        if (nodo == null) {
            return nodoAnterior;
        } else {
            return buscarPredecesor(nodo.derecha, nodo);
        }
    }

    private void actualizarMaximos(Nodo nodo, Nodo anterior) {
        if (nodo == null) {
            this.maximo = anterior;
        } else {
            actualizarMaximos(nodo.derecha, nodo);
        }
    }

    private void actualizarMinimos(Nodo nodo, Nodo anterior) {
        if (nodo == null) {
            this.minimo = anterior;
        } else {
            actualizarMinimos(nodo.izquierda, nodo);
        }
    }

    public void eliminar(T elem) {
        if (pertenece(elem) == false)
            return; // en caso de que no pertenezca

        Nodo nodoToBorrar = buscarNodo(puntero, elem);
        Nodo padre = nodoToBorrar.padre;

        // modifico el tamaño:
        tamaño -= 1;

        // caso 1: -- > es una hoja
        if (nodoToBorrar.derecha == null && nodoToBorrar.izquierda == null) {
            if (padre == null) { // significa que estamos queriendo borrar la raiz
                puntero = null; // pq estamso en el caso que no tiene ningun hijo
            } else {
                if (padre.valor.compareTo(elem) > 0) {
                    padre.izquierda = null; // le borro el hijo
                } else if (padre.valor.compareTo(elem) < 0) {
                    padre.derecha = null;
                }
            }
        }

        // caso 2 --> tiene un hijo derecho
        else if (nodoToBorrar.derecha != null && nodoToBorrar.izquierda == null) {
            if (padre == null) { // significa que la raiz tiene un solo hijo y es el derecho
                puntero = nodoToBorrar.derecha;
            } else {
                if (padre.valor.compareTo(elem) > 0) {
                    padre.izquierda = nodoToBorrar.derecha;
                } else if (padre.valor.compareTo(elem) < 0) {
                    padre.derecha = nodoToBorrar.derecha;
                }
            }
        }

        // caso 3 --> tiene un hijo izquierdo
        else if (nodoToBorrar.derecha == null && nodoToBorrar.izquierda != null) {
            if (padre == null) { // tiene un solo hijo y el de la izquierda
                puntero = nodoToBorrar.izquierda;
            } else {
                if (padre.valor.compareTo(elem) > 0) {
                    padre.izquierda = nodoToBorrar.izquierda;
                } else if (padre.valor.compareTo(elem) < 0) {
                    padre.derecha = nodoToBorrar.izquierda;
                }
            }
        }

        // caso 4 --> tiene los 2 hijos
        else {
            Nodo predecesor = buscarPredecesor(nodoToBorrar.izquierda, null);
            Nodo padrePredecesor = predecesor.padre;

            nodoToBorrar.valor = predecesor.valor;

            if (padrePredecesor == nodoToBorrar) {
                if (predecesor.izquierda == null) {
                    nodoToBorrar.izquierda = null;
                } else {
                    nodoToBorrar.izquierda = predecesor.izquierda;
                    predecesor.izquierda.padre = nodoToBorrar;
                }
            } else {
                if (predecesor.izquierda == null) {
                    padrePredecesor.derecha = null;
                } else {
                    padrePredecesor.derecha = predecesor.izquierda;
                    predecesor.izquierda.padre = padrePredecesor;
                }
            }

        }

        // ver los casos en los que tenemos maximos y minimos:
        if (maximo.valor.compareTo(elem) == 0) {
            actualizarMaximos(puntero, null);
        }
        if (minimo.valor.compareTo(elem) == 0) {
            actualizarMinimos(puntero, null);
        }
    }

    @Override
    public String toString() {
        ABB_Iterador it = iterador();
        String texto = "{";
        boolean esPrimero = true;
        while (it.haySiguiente()) {
            T valor = it.siguiente(); // SOLO una llamada
            if (!esPrimero) {
                texto += ",";
            }
            texto += valor.toString();
            esPrimero = false;
        }
        texto += "}";

        return texto;
    }

    public class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public ABB_Iterador() {
            this._actual = minimo;
        }

        public boolean haySiguiente() {
            if (_actual != null) {
                return true;
            } else {
                return false;
            }
        }

        public T siguiente() {
            T valor = _actual.valor;

            if (_actual.derecha != null) {
                _actual = _actual.derecha;

                while (_actual.izquierda != null) {
                    _actual = _actual.izquierda;
                }
            } else {
                Nodo padre = _actual.padre;

                while (padre != null && _actual == padre.derecha) {
                    _actual = padre;
                    padre = padre.padre;
                }
                _actual = padre;
            }

            return valor;
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }

}
