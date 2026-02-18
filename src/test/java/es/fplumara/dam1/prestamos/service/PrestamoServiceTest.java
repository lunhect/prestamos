package es.fplumara.dam1.prestamos.service;

import es.fplumara.dam1.prestamos.model.Material;
import es.fplumara.dam1.prestamos.model.Prestamo;
import es.fplumara.dam1.prestamos.model.Proyector;
import es.fplumara.dam1.prestamos.repository.MaterialRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrestamosServiceTest {

    // TODO (alumnos): añadir JUnit 5 y Mockito en el pom.xml y completar:
    //
    // - crearPrestamo_ok_cambiaEstado_y_guarda()

    @Mock
    Repository<Material> materialRepository;
    @Mock
    Repository<Prestamo> prestamoRepository;

    //clase que probamos
    PrestamoService prestamoService;

//antes de cada TEST, nuneva instacia
    @BeforeEach
    void setUp() {
        PrestamoService prestamoService = new PrestamoService();
    }
//setUp se ejecuta antes de cada test


    @Test
    void crearPrestamo_Ok_cambiaEstadoyGuarda() {



        when(materialRepository.findById("6769HL")).thenReturn(Optional.of(new Proyector()));


    }


    // - crearPrestamo_materialNoExiste_lanzaNoEncontrado()
    // - crearPrestamo_materialNoDisponible_lanzaMaterialNoDisponible()
    // - devolverMaterial_ok_cambiaADisponible()
    //
    // Requisito: usar mocks de repositorios y verify(...)
}
