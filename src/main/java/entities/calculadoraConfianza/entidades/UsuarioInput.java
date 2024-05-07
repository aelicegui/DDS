package entities.calculadoraConfianza.entidades;

import lombok.Setter;

public class UsuarioInput {
    @Setter public int id;
    @Setter public float puntaje_inicial;
}

/* CONTIENE ESTO:
    class Usuario(BaseModel):
    id: int
    puntaje_inicial: float
 */