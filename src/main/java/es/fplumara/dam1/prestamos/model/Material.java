package es.fplumara.dam1.prestamos.model;

import java.util.Set;

public abstract class Material implements Identificable  {
      private String id;
      private String nombre;
      EstadoMaterial estado;
      Set<String> etiquetas;

    public String getId() {
        return id;
    }

    public Material(String id, String nombre, EstadoMaterial estado, Set<String> etiquetas) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.etiquetas = etiquetas;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoMaterial getEstado() {
        return estado;
    }

    public void setEstado(EstadoMaterial estado) {
        this.estado = estado;
    }

    public Set<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<String> etiquetas) {
        this.etiquetas = etiquetas;
    }
    abstract String getTipo();

}
