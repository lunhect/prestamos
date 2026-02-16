package es.fplumara.dam1.prestamos.model;

public class Proyector extends Material  {
    private int lumens;

    public int getLumens() {
        return lumens;
    }

    public void setLumens(int lumens) {
        this.lumens = lumens;
    }

    @Override
    String getTipo() {
        return "";
    }
}
