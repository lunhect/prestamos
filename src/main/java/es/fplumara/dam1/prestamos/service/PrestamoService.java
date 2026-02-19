package es.fplumara.dam1.prestamos.service;

import es.fplumara.dam1.prestamos.exception.MaterialNoDisponibleException;
import es.fplumara.dam1.prestamos.exception.NoEncontradoException;
import es.fplumara.dam1.prestamos.model.EstadoMaterial;
import es.fplumara.dam1.prestamos.model.Material;
import es.fplumara.dam1.prestamos.model.Prestamo;
import es.fplumara.dam1.prestamos.repository.MaterialRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PrestamoService {

    private Repository<Material> materialRepository;
    private Repository<Prestamo>  prestamoRepository;

    public PrestamoService(Repository<Material> materialRepository, Repository<Prestamo> prestamoRepository) {
        this.materialRepository = materialRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public void crearPrestamo(String idMaterial, String profesor, LocalDate fecha) {

        if (idMaterial == null || idMaterial.isBlank()
                || profesor == null || profesor.isBlank()
                || fecha == null) {
            throw new IllegalArgumentException("");
        }

        Optional<Material> materialOpt = materialRepository.findById(idMaterial);

        if (materialOpt.isEmpty()) {
            throw new NoEncontradoException("");

        } else if (materialOpt.get().getEstado() != EstadoMaterial.DISPONIBLE) {
            throw new MaterialNoDisponibleException("");

        } else {

           Material material = materialOpt.get(); //material

            Prestamo prestamo = new Prestamo(
                    fecha,
                    UUID.randomUUID().toString(),
                    idMaterial,
                    profesor
            );
            prestamoRepository.save(prestamo);

            material.setEstado(EstadoMaterial.PRESTADO);
            materialRepository.save(material);
        }
    }

    void  devolverMaterial(String idMaterial){

        if (idMaterial == null || idMaterial.isBlank()) {

            throw new IllegalArgumentException();

        }

        Optional<Material> materialOpt = materialRepository.findById(idMaterial);

        if (materialOpt.isEmpty()){
            throw new NoEncontradoException("No se ha encotrnado el material");
        }

        if (!materialOpt.get().getEstado().equals(EstadoMaterial.PRESTADO)){
            throw new MaterialNoDisponibleException("SU estado no es prestado");
        } else {

            Material material = materialOpt.get(); //material

            material.setEstado(EstadoMaterial.DISPONIBLE);
            materialRepository.save(material);

        }

    }

    List<Prestamo>listarPrestamos(){

        return prestamoRepository.listAll();
    }

    }

