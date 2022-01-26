package m5b.ista.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import m5b.ista.Config.*;
import m5b.ista.Models.usuario;
import m5b.ista.Repositories.UsuarioRepository;

@RestController
public class UsuarioController {
    


    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/ListaUsuarios")
    public ResponseEntity<RespuestaGenerica> getVehiculos(){
        List<usuario> lista = usuarioRepository.findAll();
        RespuestaGenerica<usuario> respuesta = new RespuestaGenerica<>("Listado generado exitosamente",lista,0);
        return new ResponseEntity<RespuestaGenerica>(respuesta,HttpStatus.OK);
        
    }


    @PostMapping("/CrearUsuario")
    public ResponseEntity<RespuestaGenerica> CrearUsuario(@RequestBody usuario usuario){
        
        List<usuario> data = new ArrayList<usuario>();
        RespuestaGenerica<usuario> respuesta = new RespuestaGenerica<>();


        try{
            System.out.println("El nombre es "+usuario.getNombre());
            
            usuario vehiculoInsert = usuarioRepository.save(usuario);
            if(vehiculoInsert!=null){
                data.add(vehiculoInsert);
                respuesta.setMensaje("Usuario ingresado exitosamente");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }
            
            
           
        }
        catch(Exception ex){
            respuesta.setMensaje("Hubo un problema al insertar el Usuario");
            respuesta.setData(data);
            respuesta.setEstado(1);
            System.out.println(ex.getMessage());
            //return new ResponseEntity<>("El vehículo no ha sido ingresado",HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta,HttpStatus.CREATED);    

    }


    @PutMapping("/EditarUsuario/{id}")
    public ResponseEntity<RespuestaGenerica> EditarUsuario(@RequestBody usuario newUsuario,@PathVariable Long id){
        List<usuario> data = new ArrayList<usuario>();
        RespuestaGenerica<usuario> respuesta = new RespuestaGenerica<>();
        try{
            usuario v1 =usuarioRepository.findById(id)
            .map(usuario ->{
                usuario.setClave(newUsuario.getClave());
                usuario.setNombre(newUsuario.getNombre());
                usuario.setEmail(newUsuario.getEmail());
                usuario.setEstado(newUsuario.getEstado());
                return usuarioRepository.save(usuario);
            })
            .orElseGet(
                () ->{
                
                    return new usuario();
                }
            );

            if (v1!=null) {
                data.add(newUsuario);
                respuesta.setMensaje("Usuario Actualizado exitosamente");
                respuesta.setData(data);
                respuesta.setEstado(0); 
            }else{
                data.add(newUsuario);
                respuesta.setMensaje("Usuario No Actualizado exitosamente");
                respuesta.setData(data);
                respuesta.setEstado(1); 
                
            }


            
        }
        catch(Exception ex){
            respuesta.setMensaje("Hubo un problema al actualizar el Usuario");
            respuesta.setData(data);
            respuesta.setEstado(1);
            System.out.println("No se pudo almacenar el objeto en la base de datos");
            //return new ResponseEntity<>("El vehículo no ha sido ingresado",HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta,HttpStatus.OK);
    }


    @DeleteMapping("/BorrarUsuario/{id}")
    public ResponseEntity BorrarPlan(@PathVariable Long id ){
        List<usuario> data = new ArrayList<usuario>();
        RespuestaGenerica<usuario> respuesta = new RespuestaGenerica<>();
        try {
            
            usuarioRepository.deleteById(id);
            if(usuarioRepository!=null){
                data.add(new usuario());
                respuesta.setMensaje("Usuario Eliminado exitosamente");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                data.add(new usuario());
                respuesta.setMensaje("Usuario NO Eliminado exitosamente");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            respuesta.setMensaje("Hubo un problema al eliminar el Usuario");
            respuesta.setData(data);
            respuesta.setEstado(1);
            System.out.println("No se pudo almacenar el objeto en la base de datos");
        }
        
        return new ResponseEntity<RespuestaGenerica>(respuesta,HttpStatus.OK);
    }
}
