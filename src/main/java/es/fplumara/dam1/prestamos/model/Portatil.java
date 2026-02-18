package es.fplumara.dam1.prestamos.model;

public class Portatil extends Material {
private int ramGB;

    public int getRamGB() {
        return ramGB;
    }

    public Portatil(int ramGB) {
        this.ramGB = ramGB;
    }

    @Override
    String getTipo() {
        return "Portatil";

    }
}
