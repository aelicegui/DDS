package entities.geoRef.entidades;
import lombok.Getter;
import lombok.Setter;

public class Provincia implements Localizacion{
    public int id;
    @Getter @Setter public String nombre;
}
