package entities.fusiones;

import entities.comunidad.Comunidad;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class Propuesta {
    @Getter private Comunidad comunidadUno;
    @Getter private Comunidad comunidadDos;
    //@JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Getter private LocalDate fechaCreacion;
    @Setter @Getter private EstadoPropuesta estado;

    public Propuesta(){}

}
