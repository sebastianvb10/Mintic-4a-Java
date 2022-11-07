package registraduria.mintic.seguridad.controladores;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import registraduria.mintic.seguridad.modelos.Rol;
import registraduria.mintic.seguridad.modelos.Usuario;
import registraduria.mintic.seguridad.repositorios.RepositorioRol;
import registraduria.mintic.seguridad.repositorios.RepositorioUsuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController //le dice a java y spring que busque las funciones para desplegarlas en el servidor
@RequestMapping("/usuarios") //le dice a java bajo que path se corren los servicios rest
public class ControladorUsuario {
    @Autowired
    RepositorioUsuario repoUsu1;

    @Autowired
    RepositorioRol repoRol1;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario infoUsuario){
        log.info("Creando un usuario");
        String contraseñaCifrada =convertirSHA256(infoUsuario.getContrasena());
        infoUsuario.setContrasena(contraseñaCifrada);
        return repoUsu1.save(infoUsuario);
    }
    @GetMapping
    public List<Usuario> buscarTodosLosUsuarios(){
        log.info("Buscando los usuarios");
        return repoUsu1.findAll();
    }
    @GetMapping("{idUsuario}")
    public Usuario buscarUsuario(@PathVariable String idUsuario){
        return   repoUsu1
                .findById(idUsuario)
                .orElse(new Usuario("", "", ""));

    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping ("{idUsuario}")
    public void borrarUsuario(@PathVariable String idUsuario){
        Usuario usuario1=repoUsu1
                .findById(idUsuario)
                .orElse(null);
        if(usuario1!=null){
            repoUsu1.delete(usuario1);
        }
    }
    @PutMapping("{idUsuario}")
    public Usuario modificarUsuario(@PathVariable String idUsuario,@RequestBody Usuario infoUsuario){
        log.info("Modificando el usuario con id: {}",idUsuario);
        Usuario usuario1= repoUsu1
                .findById(idUsuario)
                .orElse(null);
        log.info("Usuario encontrado en base de datos: {}",usuario1);
        if(usuario1!=null){
            usuario1.setSeudonimo(infoUsuario.getSeudonimo());
            usuario1.setCorreo(infoUsuario.getCorreo());
            usuario1.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
            return repoUsu1.save(usuario1);
        }
        else {
            return null;
        }
    }
    @PutMapping("{idusuario}/rol/{idRol}")
    public Usuario asociarUnRol(@PathVariable String idUsuario, @PathVariable String idRol){
        Usuario usuario=repoUsu1
                .findById(idUsuario)
                .orElse(null);
        Rol rol= repoRol1
                .findById(idRol)
                .orElse(null);
        if(usuario!=null && rol!=null){
            usuario.setRol(rol);
            repoUsu1.save(usuario);
        }else{
            return null;
        }
        return usuario;
    }
    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
