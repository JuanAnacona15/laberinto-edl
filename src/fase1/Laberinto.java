package fase1;

import java.util.Random;

public class Laberinto {
    private char[][] matriz;
    private int filas, columnas;
    private int[] inicio, salida;

    public Laberinto(int filas, int columnas, long semilla) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new char[filas][columnas];
        generarAleatorio(semilla);
    }

    private void generarAleatorio(long semilla) {
        Random rand = new Random(semilla);
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                matriz[i][j] = rand.nextDouble() < 0.3 ? 'W' : 'C';

        // Forzar bordes como paredes
        for (int i = 0; i < filas; i++) {
            matriz[i][0] = matriz[i][columnas-1] = 'W';
        }
        for (int j = 0; j < columnas; j++) {
            matriz[0][j] = matriz[filas-1][j] = 'W';
        }

        inicio = new int[]{1, 1};
        salida = new int[]{filas-2, columnas-2};
        matriz[inicio[0]][inicio[1]] = 'S';
        matriz[salida[0]][salida[1]] = 'E';
    }

    public void imprimir() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                switch (matriz[i][j]) {
                    case 'W': System.out.print("█"); break;
                    case 'C': System.out.print(" "); break;
                    case 'S': System.out.print("S"); break;
                    case 'E': System.out.print("E"); break;
                }
            }
            System.out.println();
        }
    }

    public ListaEnlazada resolverBFS() {
        boolean[][] visitado = new boolean[filas][columnas];
        Cola cola = new Cola();
        Celda celdaInicio = new Celda(inicio[0], inicio[1], 'S');
        cola.encolar(celdaInicio);
        visitado[inicio[0]][inicio[1]] = true;

        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        while (!cola.estaVacia()) {
            Celda actual = cola.desencolar();
            if (actual.fila == salida[0] && actual.columna == salida[1]) {
                return reconstruirCamino(actual);
            }
            for (int[] d : dirs) {
                int nf = actual.fila + d[0], nc = actual.columna + d[1];
                if (nf >= 0 && nf < filas && nc >= 0 && nc < columnas
                        && !visitado[nf][nc] && matriz[nf][nc] != 'W') {
                    visitado[nf][nc] = true;
                    Celda vecino = new Celda(nf, nc, matriz[nf][nc]);
                    vecino.anterior = actual;
                    cola.encolar(vecino);
                }
            }
        }
        return null; // no hay camino
    }

    private ListaEnlazada reconstruirCamino(Celda fin) {
        ListaEnlazada camino = new ListaEnlazada();
        Celda actual = fin;
        while (actual != null) {
            camino.agregarAlFrente(actual);
            actual = actual.anterior;
        }
        return camino;
    }

    public ListaEnlazada resolverDFS() {
        boolean[][] visitado = new boolean[filas][columnas];
        fase2.Pila pila = new fase2.Pila();
        Celda celdaInicio = new Celda(inicio[0], inicio[1], 'S');
        pila.push(celdaInicio);
        visitado[inicio[0]][inicio[1]] = true;

        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        while (!pila.estaVacia()) {
            Celda actual = pila.pop();
            if (actual.fila == salida[0] && actual.columna == salida[1]) {
                return reconstruirCamino(actual);
            }
            for (int[] d : dirs) {
                int nf = actual.fila + d[0], nc = actual.columna + d[1];
                if (nf >= 0 && nf < filas && nc >= 0 && nc < columnas
                        && !visitado[nf][nc] && matriz[nf][nc] != 'W') {
                    visitado[nf][nc] = true;
                    Celda vecino = new Celda(nf, nc, matriz[nf][nc]);
                    vecino.anterior = actual;
                    pila.push(vecino);
                }
            }
        }
        return null; // no hay camino
    }

    public char[][] getMatriz() { return matriz; }
    public int[] getInicio() { return inicio; }
    public int[] getSalida() { return salida; }
}
