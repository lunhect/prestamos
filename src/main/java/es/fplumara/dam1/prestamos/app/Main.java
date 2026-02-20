package es.fplumara.dam1.prestamos.app;

import es.fplumara.dam1.prestamos.csv.CSVMaterialExporter;
import es.fplumara.dam1.prestamos.csv.CSVMaterialImporter;
import es.fplumara.dam1.prestamos.model.*;
import es.fplumara.dam1.prestamos.repository.MaterialRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.PrestamoRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.Repository;
import es.fplumara.dam1.prestamos.service.MaterialService;
import es.fplumara.dam1.prestamos.service.*;
import es.fplumara.dam1.prestamos.csv.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Main de ejemplo para demostrar el flujo mínimo del examen (sin menú complejo).
 * La idea es que este método ejecute una "demo" por consola.
 */
public class Main {

    public static <registroMaterialCsv> void main(String[] args) {
        System.out.println("Examen DAM1 - Préstamo de Material (Java 21)");


        // *    - Crear MaterialRepositoryImpl (almacena materiales en memoria).
        //         *    - Crear PrestamoRepositoryImpl (almacena préstamos en memoria).

        Repository<Material> materialRepository = new MaterialRepositoryImpl();
        Repository<Prestamo> prestamoRepository = new PrestamoRepositoryImpl();

        // * 2) Crear servicios
        //         *    - Crear MaterialService usando el repositorio de materiales.
        //         *    - Crear PrestamoService usando el repositorio de materiales y el de préstamos.

        MaterialService materialService = new MaterialService(materialRepository);
        PrestamoService prestamoService = new PrestamoService(prestamoRepository);


        //* 3) Cargar materiales desde CSV (código proporcionado)
        //         *    - Usar CsvMaterialImporter para leer "materiales.csv".
        //         *    - El importer devuelve registros (por ejemplo RegistroMaterialCsv).
        //         *    - Convertir cada registro a tu modelo:
        //         *        - Si tipo == "PORTATIL" -> crear Portatil (extra = ramGB)
        //         *        - Si tipo == "PROYECTOR" -> crear Proyector (extra = lumens)
        //         *      (aplicando estado y etiquetas)
        //         *    - Registrar cada Material llamando a MaterialService.registrarMaterial(...)


        CSVMaterialImporter csvMaterialImporter = new CSVMaterialImporter();

        CSVMaterialExporter csvMaterialExporter = new CSVMaterialExporter();

        // lista para leer materiales.csv
        List<RegistroMaterialCsv> registroMaterialCsvs = csvMaterialImporter.leer("data/materiales.csv");


        List<Portatil> portatils = new ArrayList<>();
        List<Proyector> proyectors = new ArrayList<>();

        // for y dentro el if

        for (RegistroMaterialCsv p : registroMaterialCsvs){
            if (p.tipo().equals("PROYECTOR")){
                proyectors.add(new Proyector(p.id(),p.nombre(), EstadoMaterial.valueOf(p.estado(),p.extra(),p.etiquetas())))


            }


        }

















        /*
         * FLUJO MÍNIMO OBLIGATORIO (lo que debe hacer tu main)
         *
         * 1) Crear repositorios en memoria
         *
         *

        // *    - Crear MaterialRepositoryImpl (almacena materiales en memoria).
        //         *    - Crear PrestamoRepositoryImpl (almacena préstamos en memoria)
         * 2) Crear servicios
         *    - Crear MaterialService usando el repositorio de materiales.
         *    - Crear PrestamoService usando el repositorio de materiales y el de préstamos.
         *
         * 3) Cargar materiales desde CSV (código proporcionado)
         *    - Usar CsvMaterialImporter para leer "materiales.csv".
         *    - El importer devuelve registros (por ejemplo RegistroMaterialCsv).
         *    - Convertir cada registro a tu modelo:
         *        - Si tipo == "PORTATIL" -> crear Portatil (extra = ramGB)
         *        - Si tipo == "PROYECTOR" -> crear Proyector (extra = lumens)
         *      (aplicando estado y etiquetas)
         *    - Registrar cada Material llamando a MaterialService.registrarMaterial(...)
         *
         * 4) Crear un préstamo
         *    - Elegir un id de material existente (por ejemplo "M001").
         *    - Llamar a PrestamoService.crearPrestamo("M001", "Nombre Profesor", fecha)
         *    - Comprobar que el material pasa a estado PRESTADO
         *
         * 5) Listar por consola
         *    - Imprimir todos los materiales (MaterialService.listar()) mostrando: id, nombre, estado, tipo.
         *    - Imprimir todos los préstamos (PrestamoService.listarPrestamos()) mostrando: id, idMaterial, profesor, fecha.
         *
         * 6) Devolver el material
         *    - Llamar a PrestamoService.devolverMaterial("M001")
         *    - Comprobar que vuelve a estado DISPONIBLE
         *
         * 7) Exportar a CSV (código proporcionado)
         *    - Convertir tu lista de Material a la estructura que pida el exporter (por ejemplo RegistroMaterialCsv).
         *    - Usar CsvMaterialExporter para escribir "salida_materiales.csv".
         *
         * Nota:
         * - No hace falta interfaz, ni menú, ni pedir datos por teclado: valores fijos y salida por consola es suficiente.
         */
    }
}