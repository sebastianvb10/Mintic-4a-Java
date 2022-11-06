package registraduria.mintic.seguridad.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import registraduria.mintic.seguridad.modelos.Rol;

public interface RepositorioRol extends MongoRepository<Rol,String> {

}
