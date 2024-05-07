package entities.comunidad;

import lombok.Getter;
import entities.notificador.Notificador;
import entities.geoRef.entidades.Localizador;
import entities.serviciosPub.EstadoIncidente;
import entities.serviciosPub.EstadoServicio;
import entities.serviciosPub.Incidente;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "comunidad")
public class Comunidad {
    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Column(name = "nombre")
    @Setter @Getter private String nombre;

    @OneToMany(mappedBy = "comunidad")
    @Getter private List<Miembro> miembros;

    @OneToMany(mappedBy = "comunidad")
    @Getter private List<Incidente> incidentes;

    public Comunidad() {
        this.miembros = new ArrayList<>();
        this.incidentes = new ArrayList<>();
    }

    public void agregarMiembro(Miembro... miembrosNuevos) {
        Collections.addAll(this.miembros, miembrosNuevos);
    }

    public void agregarMiembros(List<Miembro> miembrosNuevos){
        this.miembros.addAll(miembrosNuevos);
    }

    public List<Miembro> getAfectados(){
        return this.miembros.stream().filter(miembro -> miembro.getRol().equals("afectado")).toList();
    }

    public List<Miembro> getObservadores(){
        return this.miembros.stream().filter(miembro -> miembro.getRol().equals("observador")).toList();
    }

    public void agregarIncidente(Incidente... incidentes) {
        Collections.addAll(this.incidentes, incidentes);
    }

    public void agregarIncidentes(List<Incidente> incidentes) {
        this.incidentes.addAll(incidentes);
    }

    public Boolean incidenteYaReportado(Incidente incidenteResuelto){
        return this.incidentes.stream().anyMatch(
                incidente -> incidente.getServicio() == incidenteResuelto.getServicio()
                && incidente.getEntidad() == incidenteResuelto.getEntidad()
                && incidente.getEstadoIncidente() == EstadoIncidente.ABIERTO);
    }

    public EstadoIncidente estadoIncidente(Incidente incidente){
        return incidente.getEstadoIncidente();
    }

    public void crearIncidente(Incidente incidente){
        if(incidenteYaReportado(incidente)){
            incidente.getServicio().cambiarEstado(EstadoServicio.FUERA_DE_SERVICIO, incidente.getUsuario());
        }
        else{
            this.agregarIncidente(incidente);
            incidente.getServicio().cambiarEstado(EstadoServicio.FUERA_DE_SERVICIO, incidente.getUsuario());
            Notificador.getInstancia().notificar(incidente, this.miembros);
        }

    }

    public void resolverIncidente(Incidente incidente, Usuario usuario) {
        incidente.getServicio().cambiarEstado(EstadoServicio.EN_FUNCIONAMIENTO, usuario);
        incidente.marcarComoResuelto(usuario);
        Notificador.getInstancia().notificar(incidente, this.miembros);
    }

    public int getConfianza() {
        return 0;
    }

  /* public void agregarPropuesta(Propuesta propuesta) {
        //TODO
    }*/
}








