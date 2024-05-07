package entities.calculadoraConfianza.entidades;

import lombok.Getter;

import java.util.List;

public class ComunidadOutput {
    @Getter public List<UsuarioOutput> usuarios_output;
    @Getter public float nivel_de_confianza;
}

/*  CONTIENE ESTO:
    class ComunidadOutput(BaseModel):
    usuarios_output: list[UsuarioOutput]
    nivel_de_confianza: float
 */