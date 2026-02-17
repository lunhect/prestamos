package es.fplumara.dam1.prestamos.repository;

import es.fplumara.dam1.prestamos.model.Identificable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


// REPASO PROBLEMAS FORTNITEEEEEEE


public class BaseRepository<T extends Identificable> implements Repository<T> {
    Map<String, T> datos;


    @Override
    public void save(T elemento) {

        datos.put(elemento.getId(), elemento); //put: guardo, elemento getId,
    }

    @Override
    public Optional<T> findById(String id) {
        Optional<T> fortnite = Optional.empty(); //empty: vacio
        fortnite = Optional.of(datos.get(id));

        return fortnite;

    }


    @Override
    public List<T> listAll() { //pasado los datos de map a lista
        List<T> todos = new ArrayList<>(datos.values());


        return todos;
    }

    @Override
    public void delete(String id) {
        datos.remove(id);
    }


}
