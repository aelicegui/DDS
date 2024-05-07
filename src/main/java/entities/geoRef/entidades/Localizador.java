package entities.geoRef.entidades;
import entities.comunidad.Usuario;
import entities.entidades.Entidad;
import entities.entidades.Establecimiento;

public class Localizador {
    private static Localizador instancia = null;
    private AdapterGeolocalizacion adapter;
    private double distanciaMaximaCercania;

    public static Localizador getInstancia(){
        if(instancia == null){
            instancia = new Localizador();
        }
        return instancia;
    }
    private Localizador(){
        adapter = new AdapterGeoref();
        this.distanciaMaximaCercania = 20000;
    }

    public void asignarMunicipioAUsuario(Usuario usuario, String nombreMunicipio, String nombreProvincia) {
        Municipio municipio = adapter.obtenerMunicipio(nombreMunicipio, nombreProvincia);
        usuario.setUbicacion(municipio);
    }

    public void asignarProvinciaAEntidad(Entidad entidad, String nombreProvincia) {
        Provincia provincia = adapter.obtenerProvincia(nombreProvincia);
        entidad.setUbicacion(provincia);
    }

    public void asignarMunicipioAEstablecimiento(Establecimiento establecimiento, String nombreMunicipio, String nombreProvincia) {
        Municipio municipio = adapter.obtenerMunicipio(nombreMunicipio, nombreProvincia);
        establecimiento.setUbicacion(municipio);
    }

    public Provincia obtenerLaProvinciaDe(Municipio municipio) {
        return adapter.obtenerProvinciaDelMunicipio(municipio);
    }

    public boolean municipioSeEncuentraEnLaProvincia(Municipio municipio, Provincia provincia) {
        return this.obtenerLaProvinciaDe(municipio) == provincia;
    }

    public boolean seEncuentranEnLaMismaProvincia (Municipio municipio1, Municipio municipio2) {
        Provincia p1 = adapter.obtenerProvinciaDelMunicipio(municipio1);
        Provincia p2 = adapter.obtenerProvinciaDelMunicipio(municipio2);
        return p1.equals(p2);
    }

    public boolean estanCerca(Coordenadas usuario, Coordenadas establecimiento) {

        return this.estanCercaCoordenadas(usuario.getLatitud(), usuario.getLongitud(), establecimiento.getLatitud(), establecimiento.getLongitud());
    }

    private boolean estanCercaCoordenadas(double lat1, double lon1, double lat2, double lon2) {
        int radioTierra = 6371; // Radio de la Tierra en kilómetros

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distancia = radioTierra * c;
        double distanciaEnMetros = distancia * 1000; // Convertir a metros

        return distanciaEnMetros <= distanciaMaximaCercania;
    }
}
