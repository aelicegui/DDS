package entities.calculadoraConfianza.entidades;

import lombok.Getter;

public class UsuarioOutput {
    @Getter public int id;
    public float puntaje_inicial;
    public float puntuaje_final;
    @Getter public String nivel_de_confianza;
    public String recomendacion;
}

/* CONTIENE ESTO:
    class UsuarioOutput(BaseModel):
    id: int
    puntaje_inicial: float
    puntaje_final: float
    nivel_de_confianza: str
    recomendacion: str
 */