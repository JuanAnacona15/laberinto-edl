package fase1;

public class ListaEnlazada {
    private static class Nodo {
        Celda celda;
        Nodo siguiente;
        Nodo(Celda c) { celda = c; siguiente = null; }
    }

    private Nodo cabeza;
    private int tamaño;

    public void agregarAlFrente(Celda c) {
        Nodo nuevo = new Nodo(c);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        tamaño++;
    }

    public int getTamaño() { return tamaño; }

    public void imprimir() {
        Nodo actual = cabeza;
        System.out.print("Camino: ");
        while (actual != null) {
            System.out.printf("(%d,%d)", actual.celda.fila, actual.celda.columna);
            if (actual.siguiente != null) System.out.print(" → ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}
