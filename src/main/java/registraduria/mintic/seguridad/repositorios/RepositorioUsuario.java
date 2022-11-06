package registraduria.mintic.seguridad.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import registraduria.mintic.seguridad.modelos.Usuario;

public interface RepositorioUsuario extends MongoRepository<Usuario,String> {

}
