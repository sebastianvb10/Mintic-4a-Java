package registraduria.mintic.seguridad.controladores;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import registraduria.mintic.seguridad.modelos.Permiso;
import registraduria.mintic.seguridad.modelos.Rol;
import registraduria.mintic.seguridad.modelos.Usuario;
import registraduria.mintic.seguridad.repositorios.RepositorioPermiso;
import registraduria.mintic.seguridad.repositorios.RepositorioRol;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController //le dice a java y spring que busque las funciones para desplegarlas en el servidor
@RequestMapping("/roles") //le dice a java bajo que path se corren los servicios rest
public class ControladorRol {
    @Autowired
    RepositorioRol repoRoles1;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Rol crearRol(@RequestBody Rol infoRol){
        log.info("Creando un roles");
        return repoRoles1.save(infoRol);
    }
    @GetMapping
    public List<Rol> buscarTodosLosRoles(){
        log.info("Buscando los roles");
        return repoRoles1.findAll();
    }
    @GetMapping("{idRol}")
    public Rol buscarRol(@PathVariable String idRol){
        return   repoRoles1
                .findById(idRol)
                .orElse(new Rol("", ""));
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping ("{idRol}")
    public void borrarRol(@PathVariable String idRol){
        Rol rol1=repoRoles1
                .findById(idRol)
                .orElse(null);
        if(rol1!=null){
            repoRoles1.delete(rol1);
        }
    }
    @PutMapping("{idRol}")
    public Rol modificarRol(@PathVariable String idRol,@RequestBody Rol inforol){
        log.info("Modificando el usuario con id: {}",idRol);
        Rol rol1= repoRoles1
                .findById(idRol)
                .orElse(null);
        log.info("Usuario encontrado en base de datos: {}",rol1);
        if(rol1!=null){
            rol1.setNombre(inforol.getNombre());
            rol1.setDescripcion(inforol.getDescripcion());
            return repoRoles1.save(rol1);
        }
        else {
            return null;
        }
    }
}
