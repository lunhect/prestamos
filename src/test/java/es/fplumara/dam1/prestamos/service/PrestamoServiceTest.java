package es.fplumara.dam1.prestamos.service;

import es.fplumara.dam1.prestamos.exception.MaterialNoDisponibleException;
import es.fplumara.dam1.prestamos.exception.NoEncontradoException;
import es.fplumara.dam1.prestamos.model.*;
import es.fplumara.dam1.prestamos.repository.MaterialRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.time.LocalTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrestamosServiceTest {

    // TODO (alumnos): añadir JUnit 5 y Mockito en el pom.xml y completar:
    //
    // - crearPrestamo_ok_cambiaEstado_y_guarda()

    @Mock
    Repository<Material> materialRepository; //CREO UN OBJETO FALSO = A RepositoryMaterial
    @Mock
    Repository<Prestamo> prestamoRepository;

    //clase que vamos a probr
    PrestamoService prestamoService;

    //antes de cada TEST,
    @BeforeEach
    void setUp() {
        prestamoService = new PrestamoService(materialRepository, prestamoRepository);
    }
//setUp se ejecuta antes de cada test


    //MOCKITO: 1CASO,1QUE NECESITO, 3 VERIF>Y

    @Test
    void crearPrestamo_Ok_cambiaEstadoyGuarda() {

        String idMaterial = "6769HQ";
        Proyector proyectorPrestado = new Proyector("0000", "HP", EstadoMaterial.BAJA, new HashSet<>(), 9);
        proyectorPrestado.setId(idMaterial);
        proyectorPrestado.setEstado(EstadoMaterial.DISPONIBLE);
        proyectorPrestado.setEtiquetas(new HashSet<>());

        //te llamo y devuelvo esto
        when(materialRepository.findById(idMaterial)).thenReturn(Optional.of(proyectorPrestado));


        //act, creo el prestamo

        prestamoService.crearPrestamo(idMaterial, "IVANGOAT", LocalDate.now());


        //assert
        verify(prestamoRepository).save(any()); //no lo entiendo, 19!

        //estado del proyector

        assertEquals(EstadoMaterial.PRESTADO, proyectorPrestado.getEstado());

        //verify

        verify(materialRepository).save(proyectorPrestado);
    }


    // - crearPrestamo_materialNoExiste_lanzaNoEncontrado()

    @Test
    void crearPrestamo_materialNoexiste_lanzaNoencontrado() {


        NoEncontradoException ex = assertThrows(NoEncontradoException.class, () -> prestamoService.crearPrestamo("604895J", "JOSELUGOAT", LocalDate.now())
        );

    }

    // - crearPrestamo_materialNoDisponible_lanzaMaterialNoDisponible()
    @Test
    void crearPrestamo_materialNoDisponible_lanzaMaterialNoDisponible(){

        when(materialRepository.findById("00067")).thenReturn(Optional.of(new Proyector("00067", "HP", EstadoMaterial.BAJA, new HashSet<>(), 6)));


        MaterialNoDisponibleException ex = assertThrows(MaterialNoDisponibleException.class, () -> prestamoService.crearPrestamo("00067", "AINARAPRO", LocalDate.now()));



    }
    // - devolverMaterial_ok_cambiaADisponible()


    void devolverMaterial_ok_cambiaAdisponible(){






    }
    //
    // Requisito: usar mocks de repositorios y verify(...)
}






