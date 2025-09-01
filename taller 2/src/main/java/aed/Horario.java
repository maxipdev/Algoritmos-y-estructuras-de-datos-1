package aed;

public class Horario {
    private int hora;
    private int minutos;

    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public int hora() {
        return this.hora;
    }

    public int minutos() {
        return this.minutos;
    }

    @Override
    public String toString() {
        return this.hora + ":" + this.minutos;
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null) {
            return false; // false pq es nulo
        }

        if (otro.getClass() != this.getClass()) {
            return false; // pq son clases distintas
        }

        // casteo
        Horario otroHorario = (Horario) otro;

        // compración posta:
        return otroHorario.hora == this.hora && otroHorario.minutos == this.minutos;
    }

}
