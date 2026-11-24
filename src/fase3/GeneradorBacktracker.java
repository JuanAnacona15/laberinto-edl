package fase3;

import java.util.Random;

public class GeneradorBacktracker {
    private char[][] matriz;
    private int filas, columnas;
    private boolean[][] visitado;
    private Random rand;

    // Filas y columnas deben ser impares
    public GeneradorBacktracker(int filas, int columnas, long semilla) {
        this.filas = filas % 2 == 0 ? filas + 1 : filas;
        this.columnas = columnas % 2 == 0 ? columnas + 1 : columnas;
        this.matriz = new char[this.filas][this.columnas];
        this.visitado = new boolean[this.filas][this.columnas];
        this.rand = new Random(semilla);
    }

    public char[][] generar() {
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                matriz[i][j] = 'W';

        excavar(1, 1);

        matriz[1][1] = 'S';
        matriz[filas - 2][columnas - 2] = 'E';
        return matriz;
    }

    private void excavar(int f, int c) {
        visitado[f][c] = true;
        matriz[f][c] = 'C';

        int[][] dirs = {{-2,0},{2,0},{0,-2},{0,2}};
        mezclar(dirs);

        for (int[] d : dirs) {
            int nf = f + d[0], nc = c + d[1];
            if (nf > 0 && nf < filas - 1 && nc > 0 && nc < columnas - 1
                    && !visitado[nf][nc]) {
                matriz[f + d[0] / 2][c + d[1] / 2] = 'C';
                excavar(nf, nc);
            }
        }
    }

    private void mezclar(int[][] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int[] tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
        }
    }

    public int[] getFilasColumnas() {
        return new int[]{filas, columnas};
    }
}