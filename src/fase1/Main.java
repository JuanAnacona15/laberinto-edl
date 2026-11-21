package fase1;

public class Main {
    public static void main(String[] args) {
        long semilla = args.length > 0 ? Long.parseLong(args[0]) : 42L;
        int tamaño = 10;

        System.out.println("=== LABERINTO (semilla: " + semilla + ") ===");
        Laberinto lab = new Laberinto(tamaño, tamaño, semilla);
        lab.imprimir();

        System.out.println("\n=== RESOLVIENDO CON BFS ===");
        long tInicio = System.currentTimeMillis();
        ListaEnlazada camino = lab.resolverBFS();
        long tFin = System.currentTimeMillis();

        if (camino == null) {
            System.out.println("❌ No existe camino desde S hasta E.");
        } else {
            camino.imprimir();
            System.out.println("✅ Distancia mínima: " + (camino.getTamaño()-1) + " pasos");
            System.out.println("⏱ Tiempo BFS: " + (tFin - tInicio) + " ms");
        }
    }
}
