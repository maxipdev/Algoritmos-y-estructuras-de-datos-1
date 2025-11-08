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

    public Pedido proximoPedido() { // TOTAL: =(log n)
        // quiero obtener el primer elemento
        ABB<Pedido>.HandleABB handle = lista.obtener(0); // O(1)

        handle.eliminar(); // O(log n)
        // quiero eliminar el primer elemento
        lista.eliminar(0); // O(1)

        return handle.valor();
    }

    public Pedido pedidoMenorId() {
        return diccionarioABB.minimo();
    }

    public String obtenerPedidosEnOrdenDeLlegada() {
        return lista.toString();
    }

    public String obtenerPedidosOrdenadosPorId() {
        return diccionarioABB.toString();
    }
}