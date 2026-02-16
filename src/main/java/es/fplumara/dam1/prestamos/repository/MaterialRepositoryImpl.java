package es.fplumara.dam1.prestamos.repository;

import es.fplumara.dam1.prestamos.model.Material;

public class MaterialRepositoryImpl extends BaseRepository<Material> {
                      //Singleton es un "unico armario para guardar todo"
       //creamos un UNICO objeto, variable static,
private static final MaterialRepositoryImpl INSTANCE = new MaterialRepositoryImpl();

    //constructor privado, prohibido un new desde afuera, fuerzo el getInstance().
    private MaterialRepositoryImpl() {
        super(); //esto es si tiene constructor, no lo lleva pero bueno si lo lleva se queda
    }
//mismo para todos, esto todavia no lo pillo, vamos!!                                     lo acabare pillando, lo se, confio en ello...
public static MaterialRepositoryImpl getInstance(){
    return INSTANCE;
}
}


