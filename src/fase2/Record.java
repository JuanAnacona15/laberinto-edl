package fase2;

public class Record {

    public long semilla;
    public int pasos;
    public long tiempoMs;

    public Record(long semilla, int pasos, long tiempoMs) {
        this.semilla = semilla;
        this.pasos = pasos;
        this.tiempoMs = tiempoMs;
    }

    @Override
    public String toString() {
        return String.format("semilla=%-8d pasos=%-4d tiempo=%dms",
                semilla, pasos, tiempoMs);
    }
}