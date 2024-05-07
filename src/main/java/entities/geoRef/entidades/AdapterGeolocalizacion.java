package entities.geoRef.entidades;

public interface AdapterGeolocalizacion {
    public Provincia obtenerProvincia(String nombreProvincia);

    public Municipio obtenerMunicipio(String nombreMunicipio, String nombreProvincia);

    public Provincia obtenerProvinciaDelMunicipio(Municipio municipio);
}
