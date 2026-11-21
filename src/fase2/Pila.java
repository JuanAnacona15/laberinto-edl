package fase2;

import fase1.Celda;

public class Pila {

    private static class Nodo {
        Celda celda;
        Nodo siguiente;

        Nodo(Celda c) {
            celda = c;
        }
    }

    private Nodo tope;

    public void push(Celda c) {
        Nodo nuevo = new Nodo(c);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    public Celda pop() {
        if (estaVacia()) return null;
        Celda c = tope.celda;
        tope = tope.siguiente;
        return c;
    }

    public boolean estaVacia() {
        return tope == null;
    }
}