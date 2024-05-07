package apiservicio.fusion;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import apiservicio.comunidad.Comunidad;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// Esta clase contiene 2 candidatos a fusionarse. Ambos candidatos van a ser notificados con esta propuesta para ver si les interesa realizar la fusión o no.
public class Propuesta {
    @Getter private Comunidad comunidadUno;
    @Getter private Comunidad comunidadDos;
    //@JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Getter private LocalDate fechaCreacion;
    @Setter @Getter private EstadoPropuesta estado;

    public Propuesta(Comunidad comunidad1, Comunidad comunidad2){
        this.comunidadUno = comunidad1;
        this.comunidadDos = comunidad2;
        this.fechaCreacion = LocalDate.now();
        this.estado = EstadoPropuesta.EN_ESPERA;
    }

    public boolean participa(Comunidad comunidad){
        return (comunidadUno == comunidad || comunidadDos == comunidad);
    }

  /*  public boolean proponeMismaFusion(Propuesta propuesta){
        return ((comunidadUno == propuesta.getComunidadUno() && comunidadDos == propuesta.getComunidadDos()) ||
                (comunidadUno == propuesta.getComunidadDos() && comunidadDos == propuesta.getComunidadUno()));
    }*/

    public void cambiarEstado(EstadoPropuesta estado){
        this.estado = estado;
    }
    
    public Propuesta aceptar() {
        this.estado = EstadoPropuesta.APROBADA;
        return this;
    }

    public Propuesta rechazar() {
        this.estado = EstadoPropuesta.RECHAZADA;
        return this;
    }
}

