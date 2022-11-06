package registraduria.mintic.seguridad.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import registraduria.mintic.seguridad.modelos.Permiso;

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
}
