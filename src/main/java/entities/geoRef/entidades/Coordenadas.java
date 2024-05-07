package entities.geoRef.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Coordenadas {
    @Column(name="latitud")
    @Getter @Setter double latitud;
    @Column(name="longitud")
    @Getter @Setter double longitud;

    public Coordenadas(double lat, double lon) {
        this.latitud = lat;
        this.longitud = lon;
    }
    public Coordenadas() {
    }
}
