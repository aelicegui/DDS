package entities.serviciosPub;

import entities.comunidad.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Entity
@DiscriminatorValue("Compuesto")
public class ServicioCompuesto extends Servicio{
    @ManyToMany
    @JoinColumn(name="servicio_id", referencedColumnName = "id")
    private List<Servicio> servicios;

    @Override
    public Boolean estaDisponible(){
        return servicios.stream().allMatch(servicio -> servicio.getEstado() == EstadoServicio.EN_FUNCIONAMIENTO);
    }

    @Override
    public void cambiarEstado(EstadoServicio nuevoEstado, Usuario usuario){
        this.estado = nuevoEstado;
        CambioDeEstado cambioDeEstado = new CambioDeEstado(nuevoEstado, usuario);
        agregarCambioDeEstado(cambioDeEstado);
        servicios.forEach(servicio -> servicio.cambiarEstado(nuevoEstado, usuario));
    }

    @Override
    public void agregarCambioDeEstado(CambioDeEstado... cambioDeEstadosNuevo){
        Collections.addAll(this.historialDeEstados, cambioDeEstadosNuevo);
    }

    public ServicioCompuesto(){
        this.servicios = new ArrayList<>();
        this.historialDeEstados = new ArrayList<>();
    }
}
