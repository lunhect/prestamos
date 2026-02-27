package es.fplumara.dam1.prestamos.app;

import es.fplumara.dam1.prestamos.csv.CSVMaterialExporter;


import es.fplumara.dam1.prestamos.csv.CSVMaterialExporter;
import es.fplumara.dam1.prestamos.csv.CSVMaterialImporter;
import es.fplumara.dam1.prestamos.exception.NoEncontradoException;
import es.fplumara.dam1.prestamos.model.*;
import es.fplumara.dam1.prestamos.repository.MaterialRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.PrestamoRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.Repository;
import es.fplumara.dam1.prestamos.service.MaterialService;
import es.fplumara.dam1.prestamos.service.*;
import es.fplumara.dam1.prestamos.csv.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Main de ejemplo para demostrar el flujo mínimo del examen (sin menú complejo).
 * La idea es que este método ejecute una "demo" por consola.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Examen DAM1 - Préstamo de Material (Java 21)");


        // *    - Crear MaterialRepositoryImpl (almacena materiales en memoria).
        //         *    - Crear PrestamoRepositoryImpl (almacena préstamos en memoria).

        Repository<Material> materialRepository = new MaterialRepositoryImpl();
        Repository<Prestamo> prestamoRepository = new PrestamoRepositoryImpl();

        // * 2) Crear servicios
        //         *    - Crear MaterialService usando el repositorio de materiales.
        //         *    - Crear PrestamoService usando el repositorio de materiales y el de préstamos.

        MaterialService materialService = new MaterialService(materialRepository);
        PrestamoService prestamoService = new PrestamoService(materialRepository, prestamoRepository); //preguntar


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

        //         *    - Usar CsvMaterialImporter para leer "materiales.csv".
        // lista para leer materiales.csv
        List<RegistroMaterialCsv> registroMaterialCsvs = csvMaterialImporter.leer("data/materiales.csv");





        // for y dentro el if

        for (RegistroMaterialCsv p : registroMaterialCsvs) { // Convertir cada registro a tu modelo:
            //tipo, variable, dentro de, lista
            if (p.tipo().equalsIgnoreCase("PROYECTOR")) {

                materialService.registrarMaterial(
                        new Proyector(
                                p.id(),
                                p.nombre(),
                                EstadoMaterial.valueOf(p.estado()),
                                p.etiquetas(),
                                p.extra() // lumens
                        )
                );

            } else if (p.tipo().equalsIgnoreCase("PORTATIL")) {

                materialService.registrarMaterial(
                        new Portatil(
                                p.id(),
                                p.nombre(),
                                EstadoMaterial.valueOf(p.estado()), // mirar
                                p.etiquetas(),
                                p.extra() // ramGB
                        )
                );

            }
        }


// 4) Crear un préstamo
        //        *    - Elegir un id de material existente (por ejemplo "M001").
        //       *    - Llamar a PrestamoService.crearPrestamo("M001", "Nombre Profesor", fecha)
        //    *    - Comprobar que el material pasa a estado PRESTADO


        //materialRepository tiene prestamosservice,
        Material material = materialRepository.findById("M001").orElseThrow(() -> new NoEncontradoException("No existe un material  con este id"));

        prestamoService.crearPrestamo("M001", "SIMEONE", LocalDate.now());

        if (material.getEstado() == EstadoMaterial.PRESTADO) {
            System.out.println("El material esta prestado");
        }
        // El CSV  sirve para cargar datos
        // El Service  viven los datos reales del siste

        //* 5) Listar por consola
        //         *    - Imprimir todos los materiales (MaterialService.listar()) mostrando: id, nombre, estado, tipo.
        //         *    - Imprimir todos los préstamos (PrestamoService.listarPrestamos()) mostrando: id, idMaterial, profesor, fecha.


        List<Material> materiales = materialService.listar();
        List<Prestamo> prestamos = prestamoService.listarPrestamos();

        for (Material m : materiales) {
            System.out.println(m.getId());
            System.out.println(m.getNombre());
            System.out.println(m.getEstado());

            //tipo instance of: "Este objeto es de este tipo".

            if (m instanceof Proyector) {
                System.out.println("Proyector");
            } else if (m instanceof Portatil) {
                System.out.println("Portatil");
            }
        }

        for (Prestamo p : prestamos) {

            System.out.println(p.getId());
            System.out.println(p.getIdMaterial());
            System.out.println(p.getProfesor());
            System.out.println(p.getFecha());
        }


        // 6) Devolver el material
        //      *    - Llamar a PrestamoService.devolverMaterial("M001")
        //   *    - Comprobar que vuelve a estado DISPONIBLE

        prestamoService.devolverMaterial("M001");

        Material material1 = materialRepository.findById("M001").orElseThrow(() -> new NoEncontradoException("No se ha encontrado"));
        if (material1.getEstado() == EstadoMaterial.DISPONIBLE) {
            System.out.println("El materia esta disponible");
        }

        // * 7) Exportar a CSV (código proporcionado)
        //         *    - Convertir tu lista de Material a la estructura que pida el exporter (por ejemplo RegistroMaterialCsv).
        //         *    - Usar CsvMaterialExporter para escribir "salida_materiales.csv".
        //         *

        List<RegistroMaterialCsv> registros = new ArrayList<>();

        for (Material m2 : materiales) {

            if (m2 instanceof Portatil) {
                Portatil p = (Portatil) m2; // casting convierte temporalmente
                int ram = p.getRamGB();

                RegistroMaterialCsv registroPortatil = new RegistroMaterialCsv(

                        "PORTATIL",
                        p.getId(),
                        p.getNombre(),
                        p.getEstado().toString(),
                        ram,
                        p.getEtiquetas()

                );
                registros.add(registroPortatil);

            } else if (m2 instanceof Proyector) {
                Proyector p2 = (Proyector) m2;
                int lum = p2.getLumens();

                RegistroMaterialCsv registroPROYECTOR = new RegistroMaterialCsv(

                        "PROYECTOR",
                        p2.getId(),
                        p2.getNombre(),
                        p2.getEstado().toString(),
                        lum,
                        p2.getEtiquetas()


                );
                registros.add(registroPROYECTOR);
            }



            //public void escribir(String ruta, List<RegistroMaterialCsv> registros) {
            // List<Material> materiales = materialService.listar();


            /*
             * FLUJO MÍNIMO OBLIGATORIO (lo qu  e debe hacer tu main)
             *
             * 1) Crear repositorios en memoria
             *


             *    - Crear MaterialRepositoryImpl (almacena materiales en memoria).
             *    - Crear PrestamoRepositoryImpl (almacena préstamos en memoria).
             *
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
        csvMaterialExporter.escribir("salida_materiales.csv", registros);
    }
}