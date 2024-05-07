package entities.entidades;

import lombok.Getter;
import lombok.Setter;

public class LineaDeServicioDeTransporte implements TipoEntidad{
    @Setter @Getter private String tipoDeTransporte;
    @Setter @Getter private Establecimiento estacionOrigen;
    @Setter @Getter private Establecimiento estacionDestino;
}
