package aed;

public class Fecha {
    // elementos:
    private int dia;
    private int mes;

    // constructor
    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    // contrusctor de la copia
    public Fecha(Fecha fecha) {
        this.dia = fecha.dia;
        this.mes = fecha.mes;
    }

    public Integer dia() {
        return dia;
    }

    public Integer mes() {
        return mes;
    }

    @Override // sobrescribe el metodo
    public String toString() {
        return dia + "/" + mes;
    }

    @Override
    public boolean equals(Object otra) {
        boolean otroIsNull = (otra == null);
        if (otroIsNull) {
            return false; // damos falso es vacío
        }

        // tenemos que hacer esto pueden venir cualquier elemento
        boolean claseDistinta = (this.getClass() != otra.getClass());
        if (claseDistinta) {
            return false; // damos false pq son distintas clases
        }

        // ahora comparamos que realmente sean lo mismo
        Fecha otraFecha = (Fecha) otra; // casteamos fecha para que ahora tenga la misma clase que fecha
        // es decir hacemos esto pq le habiamo dichoa java la otra fecha es un objeto y
        // entonces java no sabe como tratarlo
        // pero como ya sabemos que en este punto son la msima clase hay que decirselo a
        // java

        // ahora las comparmso en serio:
        return this.dia == otraFecha.dia && this.mes == otraFecha.mes;
    }

    public void incrementarDia() {
        // miramos en el mes que estamos y obtenemos la cantidad de dias que tiene esa
        // fecha
        int cantidadDeDias = diasEnMes(mes);
        int diaSiguente = dia + 1;

        // cambiamos el mes si es más grande
        if (diaSiguente > cantidadDeDias) {
            int nuevoMes = mes + 1;
            if (nuevoMes > 12) {
                this.mes = 1; // seteo por default que sea enero
            } else {
                this.mes = nuevoMes;
            }
            // esto tiene que pasar siempre
            this.dia = 1; // lo dejamos en el dia 1
        } else {
            this.dia = dia + 1; // acá solo actualiza el dia
        }

    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
