package registraduria.mintic.seguridad.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import registraduria.mintic.seguridad.modelos.Permiso;

public interface RepositorioPermiso extends MongoRepository<Permiso,String> {
    @Query("{'url': ?0, 'metodo': ?1}")
    public Permiso findByMethodAndUrl(String url, String metodo);

}
