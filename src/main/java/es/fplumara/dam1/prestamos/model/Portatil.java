package es.fplumara.dam1.prestamos.model;

import java.util.Set;

public class Portatil extends Material {
private int ramGB;




    public int getRamGB() {
        return ramGB;
    }



    public Portatil(String id, String nombre, EstadoMaterial estado, Set<String> etiquetas, int ramGB) {
        super(id, nombre, estado, etiquetas);
        this.ramGB = ramGB;
    }

    @Override
    String getTipo() {
        return "Portatil";

    }
}
