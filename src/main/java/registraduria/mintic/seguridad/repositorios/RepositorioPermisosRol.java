package registraduria.mintic.seguridad.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import registraduria.mintic.seguridad.modelos.Permiso;
import registraduria.mintic.seguridad.modelos.PermisosRol;

public interface RepositorioPermisosRol extends MongoRepository<PermisosRol,String> {

        @Query("{'rol.$id':ObjectId(?0),'permiso.$id':ObjectId(?1)}")//Se accede a un atributo con.$
        public PermisosRol findByRolandPermissions(String idRol,String idPermiso);

}
