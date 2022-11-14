package registraduria.mintic.seguridad.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import registraduria.mintic.seguridad.modelos.Permiso;
import registraduria.mintic.seguridad.modelos.PermisosRol;

public interface RepositorioPermisosRol extends MongoRepository<PermisosRol,String> {

    @Query("{'url':?0, 'metodo':?1")
    public Permiso findByMethodAndUrl(String url, String metodo);

}
