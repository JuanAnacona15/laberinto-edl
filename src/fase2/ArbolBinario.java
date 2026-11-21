package fase2;

public class ArbolBinario {

    private static class Nodo {
        int clave; // pasos
        Record record;
        Nodo izq, der;

        Nodo(int k, Record r) {
            clave = k;
            record = r;
        }
    }

    private Nodo raiz;

    public void insertar(int pasos, Record r) {
        raiz = insertarRec(raiz, pasos, r);
    }

    private Nodo insertarRec(Nodo n, int pasos, Record r) {
        if (n == null) return new Nodo(pasos, r);
        if (pasos < n.clave) n.izq = insertarRec(n.izq, pasos, r);
        else n.der = insertarRec(n.der, pasos, r);
        return n;
    }

    /** Recorre inOrden (menor a mayor pasos) e imprime top N */
    public void top(int n) {
        System.out.println("=== TOP " + n + " MEJORES SOLUCIONES ===");
        int[] contador = {0};
        inOrden(raiz, n, contador);
    }

    private void inOrden(Nodo n, int max, int[] cnt) {
        if (n == null || cnt[0] >= max) return;
        inOrden(n.izq, max, cnt);
        if (cnt[0] < max) {
            cnt[0]++;
            System.out.println("  #" + cnt[0] + ": " + n.record);
            inOrden(n.der, max, cnt);
        }
    }
}