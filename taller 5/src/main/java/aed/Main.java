package aed;

import aed.Pedido;
import aed.SistemaPedidos;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hola mundo");

        SistemaPedidos sistema = new SistemaPedidos();

        Pedido p1 = new Pedido(100, 1, 1);
        Pedido p2 = new Pedido(50, 2, 2);
        Pedido p3 = new Pedido(150, 3, 3);
        Pedido p4 = new Pedido(25, 4, 4);

        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.agregarPedido(p3);
        sistema.agregarPedido(p4);

        System.out.println(sistema.proximoPedido());
        System.out.println(sistema.obtenerPedidosOrdenadosPorId());

        Pedido elemento = sistema.proximoPedido();
        System.out.println(elemento);
        System.out.println(elemento);
        System.out.println(sistema.obtenerPedidosOrdenadosPorId());
    }
}