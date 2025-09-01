package aed;

public class Agenda {
    private Fecha fecha;
    private ArregloRedimensionableDeRecordatorios listaDeRecordatorios;

    public Agenda(Fecha fechaActual) {
        this.fecha = new Fecha(fechaActual);
        this.listaDeRecordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    // reutlizo la logica creada antes
    public void agregarRecordatorio(Recordatorio recordatorio) {
        listaDeRecordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        Fecha fechaActual = fechaActual();
        String resultado = "";

        // ahora miro todos los recordatorios que tengan esta fecha
        for (int i = 0; i < listaDeRecordatorios.longitud(); i++) {
            Recordatorio elemento = listaDeRecordatorios.obtener(i);
            if (elemento.fecha().equals(fechaActual)) {
                resultado += elemento + "\n";
            }
        }
        return fechaActual + "\n" + "=====" + "\n" + resultado;
    }

    public void incrementarDia() {
        fecha.incrementarDia();
    }

    public Fecha fechaActual() {
        return new Fecha(this.fecha);
    }

}
