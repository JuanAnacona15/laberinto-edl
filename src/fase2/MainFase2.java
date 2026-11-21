package fase2;

import fase1.*;

public class MainFase2 {

    public static void main(String[] args) {
        TablaHash cache = new TablaHash();
        ArbolBinario ranking = new ArbolBinario();

        long[] semillas = {42L, 99L, 7L, 1234L, 42L}; // 42 repetida a propósito

        for (long semilla : semillas) {
            Laberinto lab;
            if (cache.contiene(semilla)) {
                System.out.println("[CACHÉ HIT] semilla=" + semilla);
                lab = cache.get(semilla);
            } else {
                lab = new Laberinto(10, 10, semilla);
                cache.put(semilla, lab);
                System.out.println("[NUEVO] semilla=" + semilla);
            }

            // BFS
            long t0 = System.currentTimeMillis();
            ListaEnlazada camBFS = lab.resolverBFS();
            long tBFS = System.currentTimeMillis() - t0;

            // DFS
            long t1 = System.currentTimeMillis();
            ListaEnlazada camDFS = lab.resolverDFS();
            long tDFS = System.currentTimeMillis() - t1;

            if (camBFS != null) {
                int pasosBFS = camBFS.getTamaño() - 1;
                int pasosDFS = camDFS != null ? camDFS.getTamaño() - 1 : -1;
                System.out.printf("  BFS: %d pasos (%dms) | DFS: %d pasos (%dms)%n",
                        pasosBFS, tBFS, pasosDFS, tDFS);
                ranking.insertar(pasosBFS, new Record(semilla, pasosBFS, tBFS));
            } else {
                System.out.println("  Sin camino");
            }
        }

        System.out.println();
        ranking.top(5);
    }
}