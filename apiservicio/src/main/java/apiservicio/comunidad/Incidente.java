package apiservicio.comunidad;

import lombok.Getter;
import lombok.Setter;

public class Incidente {
    @Getter @Setter private long id;
    @Getter @Setter private long servicio;
    @Getter @Setter private long establecimiento;
}
