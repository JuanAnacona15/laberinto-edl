package fase3;

import fase1.Laberinto;

public class LaberintoConfigurable extends Laberinto {

    public enum Tamaño {
        PEQUEÑO(10), MEDIANO(15), GRANDE(21);
        public final int dim;
        Tamaño(int d) { dim = d; }
    }

    public LaberintoConfigurable(Tamaño t, long semilla) {
        super(t.dim, t.dim, semilla);
    }

    public static LaberintoConfigurable conBacktracker(Tamaño t, long semilla) {
        GeneradorBacktracker gen = new GeneradorBacktracker(t.dim, t.dim, semilla);
        char[][] matr = gen.generar();
        int[] dims = gen.getFilasColumnas();
        return new LaberintoConfigurableDesdeMatriz(dims[0], dims[1], matr, semilla);
    }

    private static class LaberintoConfigurableDesdeMatriz extends LaberintoConfigurable {
        LaberintoConfigurableDesdeMatriz(int f, int c, char[][] m, long s) {
            super(Tamaño.PEQUEÑO, s);
            setMatriz(m, f, c);
        }
    }
}