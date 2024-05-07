package entities.serviciosPub;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Banios")
public class Banios extends TipoServicio{
    @Column(name="genero")
    @Setter @Getter private String genero;
}
