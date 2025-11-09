package aed;

public class ABB<T extends Comparable<T>> {
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

    public class HandleABB {
        private Nodo nodoApuntado;

        private HandleABB(Nodo n) {
            this.nodoApuntado = n;
        }

        public T valor() {
            return nodoApuntado.valor;
        }

        public void eliminar() {
            ABB.this.eliminar(nodoApuntado);
        }

    }

    public ABB() {
        puntero = null; // digo que el arbol esta vacío
        tamaño = 0;
    }

    public int cardinal() {
        return this.tamaño;
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

    private Nodo buscarNodo(Nodo nodoActual, T elemento) {
        if (nodoActual.valor.compareTo(elemento) == 0) {
            return nodoActual;
        } else if (nodoActual.valor.compareTo(elemento) > 0) {
            return buscarNodo(nodoActual.izquierda, elemento);
        } else {
            return buscarNodo(nodoActual.derecha, elemento);
        }
    }

    public T minimo() {
        return this.minimo.valor;
    }

    public T maximo() {
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

    public HandleABB insertar(T elem) {
        // si pertenece no hago nada
        if (pertenece(elem)) {
            return null;
        }

        if (puntero == null) {
            puntero = new Nodo(elem, null);
            minimo = puntero;
            maximo = puntero;
            tamaño += 1;
            return new HandleABB(puntero);
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
                return null; // sigfinica que el elemento ya estaba
            }

            if (maximo.valor.compareTo(elem) < 0) { // en caso de que el nuevo elemento sea más grande
                maximo = nuevoNodo;
            }
            if (minimo.valor.compareTo(elem) > 0) {
                minimo = nuevoNodo;
            }

            return new HandleABB(nuevoNodo);
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

    // buscamos el más grande de la rama izquierda:
    private Nodo buscarPredecesor(Nodo nodo, Nodo nodoAnterior) {
        if (nodo == null) {
            return nodoAnterior;
        } else {
            return buscarPredecesor(nodo.derecha, nodo);
        }
    }

    public void eliminar(Nodo nodoToBorrar) {
        Nodo padre = nodoToBorrar.padre;

        tamaño -= 1;

        // Caso 1: es una hoja (sin hijos)
        if (nodoToBorrar.izquierda == null && nodoToBorrar.derecha == null) {
            if (padre == null) {
                puntero = null;
            } else if (padre.izquierda == nodoToBorrar) {
                padre.izquierda = null;
            } else {
                padre.derecha = null;
            }
        }

        // Caso 2: solo tiene hijo derecho
        else if (nodoToBorrar.izquierda == null) {
            if (padre == null) {
                puntero = nodoToBorrar.derecha;
                puntero.padre = null;
            } else if (padre.izquierda == nodoToBorrar) {
                padre.izquierda = nodoToBorrar.derecha;
                nodoToBorrar.derecha.padre = padre;
            } else {
                padre.derecha = nodoToBorrar.derecha;
                nodoToBorrar.derecha.padre = padre;
            }
        }

        // Caso 3: solo tiene hijo izquierdo
        else if (nodoToBorrar.derecha == null) {
            if (padre == null) {
                puntero = nodoToBorrar.izquierda;
                puntero.padre = null;
            } else if (padre.izquierda == nodoToBorrar) {
                padre.izquierda = nodoToBorrar.izquierda;
                nodoToBorrar.izquierda.padre = padre;
            } else {
                padre.derecha = nodoToBorrar.izquierda;
                nodoToBorrar.izquierda.padre = padre;
            }
        }

        // Caso 4: tiene ambos hijos
        else {
            Nodo predecesor = buscarPredecesor(nodoToBorrar.izquierda, null);

            // Usamos la variable 'padre' ya declarada al principio del método
            // Guardamos la rama derecha del nodo a borrar
            Nodo hijoDerecho = nodoToBorrar.derecha;

            // Si el predecesor no es hijo directo del nodo a borrar,
            // hay que "extraerlo" de donde está y conectar su posible hijo izquierdo
            if (predecesor.padre != nodoToBorrar) {
                // El predecesor puede tener a lo sumo un hijo izquierdo
                if (predecesor.padre.izquierda == predecesor) {
                    predecesor.padre.izquierda = predecesor.izquierda;
                } else {
                    predecesor.padre.derecha = predecesor.izquierda;
                }
                if (predecesor.izquierda != null) {
                    predecesor.izquierda.padre = predecesor.padre;
                }

                // Ahora hacemos que el predecesor adopte la izquierda del nodo a borrar
                predecesor.izquierda = nodoToBorrar.izquierda;
                if (predecesor.izquierda != null) predecesor.izquierda.padre = predecesor;
            }

            // En todos los casos, el predecesor debe adoptar la derecha del nodo a borrar
            predecesor.derecha = hijoDerecho;
            if (hijoDerecho != null) hijoDerecho.padre = predecesor;

            // Recolocamos el predecesor en el lugar del nodo a borrar
            if (padre == null) {
                puntero = predecesor;
                predecesor.padre = null;
            } else if (padre.izquierda == nodoToBorrar) {
                padre.izquierda = predecesor;
                predecesor.padre = padre;
            } else {
                padre.derecha = predecesor;
                predecesor.padre = padre;
            }

            // NOTA: no llamamos a eliminar(predecesor.valor) aquí porque ya reubicamos
            // el nodo físico. Además, como estamos manipulando nodos directamente,
            // asegurar que no modifiquemos tamaño dos veces ni perdamos referencias.
        }


        // Actualizamos mínimo y máximo si era necesario
        if (maximo != null && maximo.valor.compareTo(nodoToBorrar.valor) == 0) {
            actualizarMaximos(puntero, null);
        }
        if (minimo != null && minimo.valor.compareTo(nodoToBorrar.valor) == 0) {
            actualizarMinimos(puntero, null);
        }
}




    @Override
    public String toString() {
        ABB_Iterador it = iterador(); // Usa el iterador Inorden
        String texto = "{";           // Abre con llave
        boolean esPrimero = true;
        
        while (it.haySiguiente()) {
            T valor = it.siguiente(); 
            
            if (!esPrimero) {
                texto += ", "; // <--- CORRECCIÓN: Agregamos la coma Y el espacio
            }
            
            texto += valor.toString(); // Usa el Pedido.toString() (que devuelve el ID)
            esPrimero = false;
        }
        
        texto += "}"; // Cierra con llave

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
