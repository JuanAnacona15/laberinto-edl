
package fase3;

import fase1.Celda;

public class Grafo {

    private static class Nodo {
        Celda celda;
        ListaAdyacencia vecinos;
        Nodo(int f, int c, char t) {
            celda = new Celda(f, c, t);
            vecinos = new ListaAdyacencia();
        }
    }

    private static class ListaAdyacencia {
        Nodo[] items = new Nodo[4];
        int tamaño = 0;
        void agregar(Nodo n) { items[tamaño++] = n; }
    }

    private Nodo[][] nodos;
    private int filas, columnas;

    public static Grafo desdeMatriz(char[][] matriz) {
        Grafo g = new Grafo();
        g.filas = matriz.length;
        g.columnas = matriz[0].length;
        g.nodos = new Nodo[g.filas][g.columnas];

        // Nodos: celdas que no son pared
        for (int i = 0; i < g.filas; i++)
            for (int j = 0; j < g.columnas; j++)
                if (matriz[i][j] != 'W')
                    g.nodos[i][j] = new Nodo(i, j, matriz[i][j]);

        // Aristas: 4 direcciones
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int i = 0; i < g.filas; i++) {
            for (int j = 0; j < g.columnas; j++) {
                if (g.nodos[i][j] == null) continue;
                for (int[] d : dirs) {
                    int ni = i + d[0], nj = j + d[1];
                    if (ni >= 0 && ni < g.filas && nj >= 0 && nj < g.columnas
                            && g.nodos[ni][nj] != null)
                        g.nodos[i][j].vecinos.agregar(g.nodos[ni][nj]);
                }
            }
        }
        return g;
    }

    public void imprimirEstadisticas() {
        int nodosCt = 0, aristasCt = 0;
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                if (nodos[i][j] != null) {
                    nodosCt++;
                    aristasCt += nodos[i][j].vecinos.tamaño;
                }
        System.out.printf("Grafo: %d nodos, %d aristas%n", nodosCt, aristasCt / 2);
    }

    public Nodo getNodo(int f, int c) { return nodos[f][c]; }
}