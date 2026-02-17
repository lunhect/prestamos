package es.fplumara.dam1.prestamos.service;

import es.fplumara.dam1.prestamos.exception.MaterialNoDisponibleException;
import es.fplumara.dam1.prestamos.exception.NoEncontradoException;
import es.fplumara.dam1.prestamos.model.EstadoMaterial;
import es.fplumara.dam1.prestamos.model.Material;
import es.fplumara.dam1.prestamos.model.Prestamo;
import es.fplumara.dam1.prestamos.repository.MaterialRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class PrestamoService {

    private Repository<Material> materialRepository;

    void crearPrestamo(String idMaterial, String profesor, LocalDate fecha) {

        if (idMaterial == null || idMaterial.isEmpty() || profesor == null || profesor.isEmpty() || fecha == null) {
            throw new IllegalArgumentException("");
        } else if (materialRepository.findById(idMaterial) == null) {
            throw new NoEncontradoException("");


        } else if (!materialRepository.findById(idMaterial).get().getEstado().equals(EstadoMaterial.DISPONIBLE)) {
            throw new MaterialNoDisponibleException("");
        } else {
          Prestamo Prestamos = new Prestamo(UUID.randomUUID().t)

        }
    }