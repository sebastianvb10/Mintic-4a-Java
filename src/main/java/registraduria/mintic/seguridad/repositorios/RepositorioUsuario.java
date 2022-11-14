package registraduria.mintic.seguridad.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import registraduria.mintic.seguridad.modelos.Usuario;

public interface RepositorioUsuario extends MongoRepository<Usuario,String> {

    @Query("{'correo':?0}")
    public Usuario findByEmail(String correo1);


}
