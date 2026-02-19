package es.fplumara.dam1.prestamos.model;

import java.util.Set;

public class Proyector extends Material  {
    private int lumens;



    public Proyector(String id, String nombre, EstadoMaterial estado, Set<String> etiquetas, int lumens) {
        super(id, nombre, estado, etiquetas);
        this.lumens = lumens;
    }

    public int getLumens() {
        return lumens;
    }

    public void setLumens(int lumens) {
        this.lumens = lumens;
    }

    @Override
    String getTipo() {
        return "Proyector";
    }
}
