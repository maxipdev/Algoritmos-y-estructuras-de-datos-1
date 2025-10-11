
public interface Secuencia<T> {
    int longitud();

    void agregarAdelante(T elem);

    void agregarAtras(T elem);

    void eliminar(int i);

    T obtener(int i);

    void modificarPosicion(int i, T elem);

    String toString();

    Iterator<T> iterador();
}
