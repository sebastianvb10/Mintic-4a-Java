package registraduria.mintic.seguridad.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import registraduria.mintic.seguridad.modelos.Permiso;
import registraduria.mintic.seguridad.modelos.PermisosRol;
import registraduria.mintic.seguridad.modelos.Rol;
import registraduria.mintic.seguridad.repositorios.RepositorioPermiso;
import registraduria.mintic.seguridad.repositorios.RepositorioPermisosRol;
import registraduria.mintic.seguridad.repositorios.RepositorioRol;
import java.util.List;

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
    public List<PermisosRol> buscarTodosPermisosRoles(){
        return repoPermisosRol1.findAll();
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping ("{idPermisosRol}")
    public void borrarPermisosRol(@PathVariable String idPermisosRol){
        PermisosRol permisosRol=repoPermisosRol1
                .findById(idPermisosRol)
                .orElse(null);
    }
    @PutMapping("{id}/rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRol update(@PathVariable String id,@PathVariable
    String id_rol,@PathVariable String id_permiso){
        PermisosRol permisosRolesActual=this.repoPermisosRol1
                .findById(id)
                .orElse(null);
        Rol rol1=this.repoPermisosRol1.findById(id_rol).get().getRol();
        Permiso
                permiso1=this.repoPermisosRol1.findById(id_permiso).get().getPermiso();
        if(permisosRolesActual!=null && permiso1!=null && rol1!=null){
            permisosRolesActual.setPermiso(permiso1);
            permisosRolesActual.setRol(rol1);
            return
                    this.repoPermisosRol1.save(permisosRolesActual);
        }else{
            return null;
        }
    }
}
