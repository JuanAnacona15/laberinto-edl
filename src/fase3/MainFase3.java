package fase3;

import fase1.ListaEnlazada;
import fase2.ArbolBinario;
import fase2.Record;

public class MainFase3 {
    public static void main(String[] args) {
        long semilla = args.length > 0 ? Long.parseLong(args[0]) : 42L;
        String tamaStr = args.length > 1 ? args[1].toUpperCase() : "MEDIANO";

        LaberintoConfigurable.Tamaño tam = tamaStr.startsWith("PEQ")
                ? LaberintoConfigurable.Tamaño.PEQUEÑO
                : LaberintoConfigurable.Tamaño.valueOf(tamaStr);

        System.out.println("LABERINTO " + tam + " - semilla " + semilla);

        // Laberinto generado con Recursive Backtracker
        LaberintoConfigurable lab = LaberintoConfigurable.conBacktracker(tam, semilla);
        lab.imprimir();

        // Grafo
        Grafo grafo = Grafo.desdeMatriz(lab.getMatriz());
        grafo.imprimirEstadisticas();

        // BFS
        System.out.println("\n--- BFS ---");
        long t0 = System.currentTimeMillis();
        ListaEnlazada camino = lab.resolverBFS();
        long tMs = System.currentTimeMillis() - t0;

        if (camino == null) {
            System.out.println("Sin camino");
        } else {
            camino.imprimir();
            int pasos = camino.getTamaño() - 1;
            System.out.printf("Distancia mínima: %d pasos | Tiempo: %dms%n", pasos, tMs);

            ArbolBinario ranking = new ArbolBinario();
            ranking.insertar(pasos, new Record(semilla, pasos, tMs));
            ranking.top(5);
        }
    }
}