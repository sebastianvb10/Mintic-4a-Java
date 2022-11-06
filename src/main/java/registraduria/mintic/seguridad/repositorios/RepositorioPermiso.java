package registraduria.mintic.seguridad.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import registraduria.mintic.seguridad.modelos.Permiso;

public interface RepositorioPermiso extends MongoRepository<Permiso,String> {

}
