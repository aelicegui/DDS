package entities.fusiones;

import lombok.Getter;
import lombok.Setter;

public class IncidenteApi {
    @Getter @Setter private long id;
    @Getter @Setter private long servicio;
    @Getter @Setter private long establecimiento;


    public IncidenteApi(Long id, Long servicioId, Long establecimientoId) {
        this.id = id;
        this.servicio = servicioId;
        this.establecimiento = establecimientoId;
    }
}
