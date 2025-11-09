package aed;

public class SistemaPedidos {
    private ABB<Pedido> diccionarioABB;
    private ListaEnlazada<ABB<Pedido>.HandleABB> lista;

    public SistemaPedidos() {
        diccionarioABB = new ABB<Pedido>();
        lista = new ListaEnlazada<ABB<Pedido>.HandleABB>();
    }

    public void agregarPedido(Pedido pedido) { // TOTAL: O(log n)
        ABB<Pedido>.HandleABB handle = diccionarioABB.insertar(pedido); // O(log n)
        // ahora lo agrego en la lista:
        lista.agregarAtras(handle); // O(1)
    }

    public Pedido proximoPedido() { 
        ABB<Pedido>.HandleABB handle = lista.obtener(0); 
        Pedido p = handle.valor();
        // ¿Qué imprime esto? Si es 30, el fallo está antes de 'return'.
        System.out.println("ID a devolver: " + p.toString()); 

        handle.eliminar(); 
        lista.eliminar(0); 

        return p; // Devuelve la variable p para evitar llamadas dobles.
    }

    public Pedido pedidoMenorId() {
        return diccionarioABB.minimo();
    }

    public String obtenerPedidosEnOrdenDeLlegada() {
        String texto = "[";
        
        // 1. Usa el iterador de la lista de Handles
        ListaEnlazada<ABB<Pedido>.HandleABB>.ListaIterador it = lista.iterador();
        
        // 2. Procesa el primer elemento
        if (it.haySiguiente()) {
            ABB<Pedido>.HandleABB handle = it.siguiente(); 
            
            // Obtener el Pedido del Handle (asumo que se llama valor())
            Pedido pedido = handle.valor(); 
            
            // Agregar el ID del pedido (usando Pedido.toString() que devuelve el ID)
            texto += pedido.toString(); 
        }

        // 3. Procesa el resto de los elementos, agregando ", " ANTES.
        while (it.haySiguiente()) {
            ABB<Pedido>.HandleABB handle = it.siguiente(); 
            Pedido pedido = handle.valor();
            
            // Añade el separador ", " y luego el ID (vía Pedido.toString())
            texto += ", ";
            texto += pedido.toString();
        }
        
        // 4. Cerrar el corchete
        texto += "]";
        
        return texto;
    }

    public String obtenerPedidosOrdenadosPorId() {
        return diccionarioABB.toString();
    }
}