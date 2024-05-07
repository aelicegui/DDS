package entities.geoRef.entidades;
import lombok.SneakyThrows;
import entities.geoRef.ServicioGeoref;

import java.util.Optional;

public class AdapterGeoref implements AdapterGeolocalizacion {
    private ServicioGeoref servicioGeoref = ServicioGeoref.instancia();

    @SneakyThrows
    public Provincia obtenerProvincia(String nombreProvincia) {
        ListadoProvincias listadoDeProvinciasArgentinas = servicioGeoref.listadoProvincias();
        Optional<Provincia> posibleProvincia = listadoDeProvinciasArgentinas.provincias.stream().filter(p -> p.nombre.equals(nombreProvincia)).findAny();
        if(posibleProvincia.isPresent()){
            return posibleProvincia.get();
        } else {
            System.out.println("No existe la provincia buscada.");
            return null;
        }
    }

    @SneakyThrows
    public Municipio obtenerMunicipio(String nombreMunicipio, String nombreProvincia) {
        Provincia provinciaSeleccionada = this.obtenerProvincia(nombreProvincia);
        ListadoMunicipios municipiosDeLaProvincia = servicioGeoref.listadoMunicipiosProvincia(provinciaSeleccionada);
        Optional<Municipio> posibleMunicipio = municipiosDeLaProvincia.municipios.stream().filter(m -> m.nombre.equals(nombreMunicipio)).findAny();
        if(posibleMunicipio.isPresent()){
            return posibleMunicipio.get();
        } else {
            System.out.println("No existe el municipio buscado.");
            return null;
        }
    }

    public Provincia obtenerProvinciaDelMunicipio(Municipio municipio) {
        return municipio.getProvincia();
    }
}
