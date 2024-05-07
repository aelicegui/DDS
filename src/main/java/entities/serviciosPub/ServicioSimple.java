package entities.serviciosPub;

import entities.comunidad.Usuario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collections;

@Entity
@DiscriminatorValue("Simple")
public class ServicioSimple extends Servicio{

    public ServicioSimple(){
        this.historialDeEstados = new ArrayList<>();
    }

    @Override
    public void cambiarEstado(EstadoServicio nuevoEstado, Usuario usuario){
        this.estado = nuevoEstado;
        CambioDeEstado cambioDeEstado = new CambioDeEstado(nuevoEstado, usuario);
        agregarCambioDeEstado(cambioDeEstado);

    }

    @Override
    public Boolean estaDisponible(){
        return (this.estado == EstadoServicio.EN_FUNCIONAMIENTO);
    }

    @Override
    public void agregarCambioDeEstado(CambioDeEstado... cambioDeEstadosNuevo){
        Collections.addAll(super.historialDeEstados, cambioDeEstadosNuevo);
    }
}
