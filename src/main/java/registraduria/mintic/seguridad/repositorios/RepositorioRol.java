package registraduria.mintic.seguridad.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import registraduria.mintic.seguridad.modelos.Permiso;
import registraduria.mintic.seguridad.modelos.Rol;

public interface RepositorioRol extends MongoRepository<Rol,String> {

}
