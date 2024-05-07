package entities.fusiones;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ComunidadApi {

    @Getter @Setter private long id;
    @Getter @Setter private List<MiembroApi> miembros;
    @Getter @Setter private List<IncidenteApi> incidentes;
    @Getter @Setter private int confianza;  //Parte del servicio 2

    public ComunidadApi(Long id, List<MiembroApi> listaMiembros, List<IncidenteApi> listaIncidentes, int confianza) {
        this.id = id;
        this.miembros = listaMiembros;
        this.incidentes = listaIncidentes;
    }
}
