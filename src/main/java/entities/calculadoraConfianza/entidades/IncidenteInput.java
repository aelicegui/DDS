package entities.calculadoraConfianza.entidades;

import lombok.Setter;

public class IncidenteInput {
   @Setter public int id ;
   @Setter public String id_establecimiento;
   @Setter public String id_servicio_afectado;
   @Setter public String fecha_de_apertura;
   @Setter public String fecha_de_cierre;
   @Setter public int id_usuario_de_apertura;
   @Setter public int id_usuario_de_cierre;
}

/* CONTIENE:
    class Incidente(BaseModel):
    id: int
    id_establecimiento: str
    id_servicio_afectado : str
    fecha_de_apertura: str
    fecha_de_cierre: str
    id_usuario_de_apertura: int
    id_usuario_de_cierre: int
 */