package entities.geoRef.entidades;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@AttributeOverrides({
        @AttributeOverride( name = "id", column = @Column(name = "municipio_id")),
        @AttributeOverride( name = "nombre", column = @Column(name = "nombreMunicipio"))
})
public class Municipio implements Localizacion{
    public int id;
    @Getter @Setter public String nombre;
    @Transient
    @Getter @Setter public Provincia provincia;

}
