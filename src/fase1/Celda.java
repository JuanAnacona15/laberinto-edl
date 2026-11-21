package fase1;

public class Celda {
    public int fila, columna;
    // Tipos: 'W'=pared, 'C'=camino, 'S'=inicio, 'E'=salida
    public char tipo;
    public Celda anterior; // para reconstruir camino en BFS

    public Celda(int fila, int columna, char tipo) {
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
        this.anterior = null;
    }
}
