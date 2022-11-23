package registraduria.mintic.seguridad.controladores;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import registraduria.mintic.seguridad.modelos.Permiso;
import registraduria.mintic.seguridad.modelos.PermisosRol;
import registraduria.mintic.seguridad.modelos.Rol;
import registraduria.mintic.seguridad.repositorios.RepositorioPermiso;
import registraduria.mintic.seguridad.repositorios.RepositorioPermisosRol;
import registraduria.mintic.seguridad.repositorios.RepositorioRol;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/roles/{idRol}/permisos/{idPermiso}")
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
    @GetMapping("validar-permisos/rol/{idRol}")
    public PermisosRol validarPermisosRol(@PathVariable String idRol, @RequestBody Permiso infoPermiso, HttpServletResponse response) throws IOException {
        Rol rolEncontrado =repoRol1
                .findById(idRol)
                .orElse(null);
        Permiso permisoEncontrado=repoPermiso1.findByMethodAndUrl(infoPermiso.getUrl(), infoPermiso.getMetodo());
        System.out.println(permisoEncontrado);
        if(rolEncontrado!=null &&permisoEncontrado!=null){
            PermisosRol permisoRol=repoPermisosRol1.findByRolandPermissions(rolEncontrado.get_id(), permisoEncontrado.get_id());
            //Buscar si tiene asociados los permisos solicitados
            log.info("Se encuentra permiso solicitado");
        if(permisoRol!=null) {
            return permisoRol;
        }
        else{
            log.error("No se encuentra el Permiso rol en base de datos");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        }
        else{
            log.error("No se encuentra el rol en base de datos");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        //validar si existe el rol en db

    }
}
