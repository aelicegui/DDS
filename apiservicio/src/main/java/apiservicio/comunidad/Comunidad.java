package apiservicio.comunidad;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Comunidad {
    @Getter @Setter private long id;
    @Getter @Setter private List<Miembro> miembros;
    @Getter @Setter private List<Incidente> incidentes;
    @Getter @Setter private int confianza;//Parte del servicio 2

    public Comunidad(){
        this.miembros = new ArrayList<>();
        this.incidentes = new ArrayList<>();
    }

}