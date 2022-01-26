package m5b.ista.Config;

import java.util.List;

public class RespuestaGenerica<T>  {

    private String mensaje;
    private List<T> data;
    private int estado;

    public RespuestaGenerica(String mensaje, List<T> data,int estado) {
        this.mensaje = mensaje;
        this.data = data;
        this.estado = estado;
    }
    public RespuestaGenerica() {

    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }

    

    

    
}
