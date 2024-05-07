package entities.serviciosPub;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_servicio")
public abstract class TipoServicio {
    @Id
    @GeneratedValue
    @Setter @Getter private Long id;
}
