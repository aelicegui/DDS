package entities.serviciosPub;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Medio_de_elevacion")
public class MedioDeElevacion extends TipoServicio{
    @Column(name="tramo_hasta_acceso")
    @Setter @Getter private Integer tramoHastaAcceso;
    @Column(name="tramo_hasta_anden")
    @Setter @Getter private Integer tramoHastaAnden;
}
