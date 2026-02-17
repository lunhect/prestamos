package es.fplumara.dam1.prestamos.service;

import es.fplumara.dam1.prestamos.exception.DuplicadoException;
import es.fplumara.dam1.prestamos.model.Material;
import es.fplumara.dam1.prestamos.repository.Repository;
import es.fplumara.dam1.prestamos.exception.DuplicadoException;
import es.fplumara.dam1.prestamos.exception.MaterialNoDisponibleException;
import es.fplumara.dam1.prestamos.exception.NoEncontradoException;
import es.fplumara.dam1.prestamos.model.EstadoMaterial;
import es.fplumara.dam1.prestamos.model.Material;
import es.fplumara.dam1.prestamos.repository.Repository;

import java.util.List;
import java.util.Optional;


// no tiene porque hacer falta el Singleton, son reglas, no almacena datos.           //reglas = comprobaciones
public class MaterialService {

    //repo de materiales para pruebas
    private Repository<Material> materialRepository;


    //reglas

    void registrarMaterial(Material m) {

        // mismo id = Duplicado exception

        if (materialRepository.findById(m.getId()).equals(m)) {
            throw new DuplicadoException();
        } else if (m == null || m.getId() == null || m.getId().isEmpty()) {
            throw new IllegalArgumentException();

        }


    }

 //repaso casa
    void darDeBaja(String idMaterial) {
        Optional<Material> material = materialRepository.findById(idMaterial);
        if (material.isEmpty()) {
            throw new NoEncontradoException("");
        } else if (material.get().getEstado().equals(EstadoMaterial.BAJA)) {
            throw new MaterialNoDisponibleException("");
        } else {
            material.get().setEstado(EstadoMaterial.BAJA);
        }


    }


List<Material> listar(){
        return materialRepository.listAll();


    }



}

