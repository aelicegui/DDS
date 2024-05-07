package entities.calculadoraConfianza.entidades;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ComunidadInput {
    @Getter @Setter public List<UsuarioInput> usuarios;
    @Getter @Setter public List<IncidenteInput> incidentes;
}

/*  CONTIENE ESTO:
    class Comunidad(BaseModel):
    usuarios: list[Usuario]
    incidentes: list[Incidente]
 */
