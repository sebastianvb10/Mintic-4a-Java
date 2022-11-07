package registraduria.mintic.seguridad.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import registraduria.mintic.seguridad.modelos.Permiso;
import registraduria.mintic.seguridad.modelos.PermisosRol;
import registraduria.mintic.seguridad.modelos.Rol;
import registraduria.mintic.seguridad.repositorios.RepositorioPermiso;
import registraduria.mintic.seguridad.repositorios.RepositorioPermisosRol;
import registraduria.mintic.seguridad.repositorios.RepositorioRol;

@CrossOrigin
@RestController
@RequestMapping("/permisosRol")
public class ControladorPermisosRol {
    @Autowired
    private RepositorioRol repoRol1;
    @Autowired
    private RepositorioPermiso repoPermiso1;
    @Autowired
    private RepositorioPermisosRol repoPermisosRol1;
    @PostMapping("/rol/{idRol}/permiso/{idPermiso}")
    public PermisosRol crearPermisosRoles(@PathVariable String idRol,@PathVariable String idPermiso){
        Permiso permiso1=repoPermiso1
                .findById(idPermiso)
                .orElseThrow(RuntimeException::new);
        Rol rol1=repoRol1
                .findById(idRol)
                .orElseThrow(RuntimeException::new);
        PermisosRol permisosRol=new PermisosRol(rol1,permiso1);
        return repoPermisosRol1.save(permisosRol);
    }
    @GetMapping
    public
}
