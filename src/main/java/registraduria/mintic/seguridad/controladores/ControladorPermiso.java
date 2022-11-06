package registraduria.mintic.seguridad.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import registraduria.mintic.seguridad.modelos.Permiso;

import registraduria.mintic.seguridad.modelos.Usuario;
import registraduria.mintic.seguridad.repositorios.RepositorioPermiso;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@CrossOrigin
@RestController //le dice a java y spring que busque las funciones para desplegarlas en el servidor
@RequestMapping("/permisos") //le dice a java bajo que path se corren los servicios rest
public class ControladorPermiso {
    @Autowired
    RepositorioPermiso repoPermi1;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Permiso crearPermiso(@RequestBody Permiso infoPermiso){
        log.info("Creando un permiso");
        return repoPermi1.save(infoPermiso);
    }
    @GetMapping
    public List<Permiso> buscarTodosLosPermisos(){
        log.info("Buscando los permiso");
        return repoPermi1.findAll();
    }
    @GetMapping("{idPermiso}")
    public Permiso buscarPermiso(@PathVariable String idPermiso){
        return   repoPermi1
                .findById(idPermiso)
                .orElse(new Permiso("", ""));
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping ("{idPermiso}")
    public void borrarPermiso(@PathVariable String idPermiso){
        Permiso usuario1=repoPermi1
                .findById(idPermiso)
                .orElse(null);
        if(usuario1!=null){
            repoPermi1.delete(usuario1);
        }
    }
    @PutMapping("{idPermiso}")
    public Permiso modificarUsuario(@PathVariable String idUsuario,@RequestBody Usuario infoUsuario){
        log.info("Modificando el usuario con id: {}",idUsuario);
        Permiso permiso1= repoPermi1
                .findById(idUsuario)
                .orElse(null);
        log.info("Usuario encontrado en base de datos: {}",permiso1);
        if(permiso1!=null){
            permiso1.setUrl(infoUsuario.getSeudonimo());
            permiso1.setMetodo(infoUsuario.getCorreo());
            return repoPermi1.save(permiso1);
        }
        else {
            return null;
        }
    }
}
