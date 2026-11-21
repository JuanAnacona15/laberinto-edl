package fase2;

import fase1.Laberinto;

public class TablaHash {

    private static final int CAPACIDAD = 16;

    private static class Entrada {
        long clave;
        Laberinto valor;
        Entrada siguiente;

        Entrada(long k, Laberinto v) {
            clave = k;
            valor = v;
        }
    }

    private Entrada[] tabla = new Entrada[CAPACIDAD];

    private int indice(long clave) {
        return (int) (Math.abs(clave) % CAPACIDAD);
    }

    public void put(long semilla, Laberinto lab) {
        int idx = indice(semilla);
        Entrada e = tabla[idx];
        while (e != null) {
            if (e.clave == semilla) {
                e.valor = lab;
                return;
            }
            e = e.siguiente;
        }
        // Nueva entrada al frente (encadenamiento)
        Entrada nueva = new Entrada(semilla, lab);
        nueva.siguiente = tabla[idx];
        tabla[idx] = nueva;
    }

    public Laberinto get(long semilla) {
        int idx = indice(semilla);
        Entrada e = tabla[idx];
        while (e != null) {
            if (e.clave == semilla) return e.valor;
            e = e.siguiente;
        }
        return null;
    }

    public boolean contiene(long semilla) {
        return get(semilla) != null;
    }
}