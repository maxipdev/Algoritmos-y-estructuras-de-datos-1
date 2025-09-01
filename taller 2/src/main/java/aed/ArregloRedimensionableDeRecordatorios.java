package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] arrayDeRecordatorios;

    public ArregloRedimensionableDeRecordatorios() {
        this.arrayDeRecordatorios = new Recordatorio[0]; // agrego uno vacio
    }

    public int longitud() {
        return arrayDeRecordatorios.length;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] nuevoArray = new Recordatorio[longitud() + 1];

        for (int j = 0; j < longitud(); j++) {
            nuevoArray[j] = arrayDeRecordatorios[j];
        }

        // agrego el neuvo elemento;
        nuevoArray[nuevoArray.length - 1] = i;

        // le cambio la direccion de memoria
        arrayDeRecordatorios = nuevoArray;
    }

    public Recordatorio obtener(int i) {
        // devuelve el elemento en la posicion i
        return arrayDeRecordatorios[i];
    }

    public void quitarAtras() {
        Recordatorio[] nuevoArray = new Recordatorio[longitud() - 1];

        for (int i = 0; i < longitud() - 1; i++) {
            nuevoArray[i] = arrayDeRecordatorios[i];
        }

        // cambio la direccion de memoria
        arrayDeRecordatorios = nuevoArray;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        arrayDeRecordatorios[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        this.arrayDeRecordatorios = new Recordatorio[vector.longitud()];

        for (int i = 0; i < vector.longitud(); i++) {
            // no hace falta hacer un new Recordatorio(vector.obtener(i)) pq el
            // recorrdatorio es inmutable desde fuera ya que no tiene setters
            arrayDeRecordatorios[i] = vector.obtener(i);
        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        // el this sirve para tomar la msima instancia declase del msimo lugar en donde
        // se la invoca
        return new ArregloRedimensionableDeRecordatorios(this);
    }
}
