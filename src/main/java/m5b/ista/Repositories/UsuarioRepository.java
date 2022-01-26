package m5b.ista.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import m5b.ista.Models.usuario;

public interface UsuarioRepository extends JpaRepository<usuario,Long>{

    @Query("SELECT u FROM usuario u WHERE u.nombre = :nombre  AND  u.clave = :clave")
    usuario IniciarSesion(String nombre, String clave);
    
}
//cambios
