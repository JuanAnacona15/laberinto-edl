package fase1;

public class Cola {
    private static class Nodo {
        Celda celda;
        Nodo siguiente;
        Nodo(Celda c) { celda = c; siguiente = null; }
    }

    private Nodo frente, final_q;

    public void encolar(Celda c) {
        Nodo nuevo = new Nodo(c);
        if (estaVacia()) { frente = final_q = nuevo; }
        else { final_q.siguiente = nuevo; final_q = nuevo; }
    }

    public Celda desencolar() {
        if (estaVacia()) return null;
        Celda c = frente.celda;
        frente = frente.siguiente;
        if (frente == null) final_q = null;
        return c;
    }

    public boolean estaVacia() { return frente == null; }
}
