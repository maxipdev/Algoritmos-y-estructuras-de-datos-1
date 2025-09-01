package aed;

public class Recordatorio {
    private Fecha fecha;
    private String mensaje;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.fecha = new Fecha(fecha); // hacemos esto para tener una copia del objeto, ya que podriamos
        // tener problemas al momento de crearlo y si se le psan con ref y tener
        // problemas con alliasing
        // ademas es imporante aclarar que esto se podria modificar de afuera por culpa
        // de allisaing pq tiene setters
        // de esta manera si creamos una nueva instancia no se pueden modificar de
        // afuera
        // en cambio si no tenemos setters, no es necesario hacer esto (a menos que sea
        // un objeto que de por si se lo puedan cambiar como los arrays)
        // en este caso la clase prohibe los cambios
        this.mensaje = mensaje;
        this.horario = horario;
    }

    public Horario horario() {
        return this.horario;
    }

    public Fecha fecha() {
        return new Fecha(this.fecha);
    }

    public String mensaje() {
        return this.mensaje;
    }

    @Override
    public String toString() {
        return mensaje + " " + "@" + " " + fecha + " " + horario;
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null)
            return false;

        if (otro.getClass() != this.getClass())
            return false;

        Recordatorio otroRecordatorio = (Recordatorio) otro;

        return this.mensaje == otroRecordatorio.mensaje && this.horario.equals(otroRecordatorio.horario)
                && this.fecha.equals(otroRecordatorio.fecha);
    }

}
