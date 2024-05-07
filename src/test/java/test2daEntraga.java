import entities.comunidad.Usuario;
import entities.config.Config;
import entities.csv.EstrategiaODC;
import entities.csv.EstrategiaPrestadora;
import entities.csv.Importador;
import entities.entidades.Entidad;
import entities.entidades.Establecimiento;
import entities.repositorio.RepoODC;
import entities.repositorio.RepoPrestadora;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.io.File;

import java.util.List;


import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import entities.geoRef.entidades.Localizador;
import entities.geoRef.entidades.Municipio;
import entities.geoRef.entidades.Provincia;


public class test2daEntraga {
    private Importador importador;

    @BeforeEach
    public void init() {
        this.importador = new Importador(new EstrategiaPrestadora());
    }

    @Test
    @DisplayName("Prueba del Importador de ODC´s.")
    public void odcCreado() {
        importador.setEstrategia(new EstrategiaODC());
        importador.importar(Config.RUTA_ARCHIVOCSV);

        Assertions.assertEquals(
                2, new RepoODC().getAll().size());
    }

    @Test
    @DisplayName("Prueba: Importacion de las Prestadoras.")
    public void prestadoraCreada(){
        importador.importar(Config.RUTA_ARCHIVOCSV);

        Assertions.assertEquals(
                3, new RepoPrestadora().getAll().size());
    }
    /*
        @Test
        @DisplayName("Prueba Entidad al Importar Prestadoras.")
        public void prestadoraCreadaEntidad(){
            importador.importar(new File(Config.RUTA_ARCHIVOCSV));

            Assertions.assertEquals(
                    3,
                    EstrategiaPrestadora.getPrestadoras().stream().map(
                            prestadora -> prestadora.getEntidades().size()).toList().stream()
                            .reduce(0, Integer::sum));
        }

        @Test
        @DisplayName("Prueba Establecimientos al Importar Prestadoras.")
        public void prestadoraCreadaEstablecimiento(){
            importador.importar(new File(Config.RUTA_ARCHIVOCSV));
            List<Entidad> entidades = EstrategiaPrestadora.getPrestadoras().stream().map(
                    prestadora -> prestadora.getEntidades()).flatMap(
                            entidad -> entidad.stream()).toList();

            Assertions.assertEquals(
                    4,entidades.stream().map(
                                            entidad -> entidad.getEstablecimientos().size()
                            ).toList().stream()
                            .reduce(0, Integer::sum));
        }

        @Test
        @DisplayName("Prueba Servicios al Importar Prestadoras.")
        public void prestadoraCreadaServicios(){
            importador.importar(new File(Config.RUTA_ARCHIVOCSV));
            List<Establecimiento> establecimientos = EstrategiaPrestadora.getPrestadoras().stream().map(
                    prestadora -> prestadora.getEntidades()).flatMap(
                    entidad -> entidad.stream()).map(
                    entidad -> entidad.getEstablecimientos()
                    ).flatMap(establecimiento -> establecimiento.stream()).toList();

            Assertions.assertEquals(
                    4,establecimientos.stream().map(
                                    establecimiento -> establecimiento.getServiciosDisponibles().size()
                            ).toList().stream()
                            .reduce(0, Integer::sum));
        }
    */
    @Test
    @DisplayName("Prueba del Localizador: Asignaciones.")
    public void testeoLocalizador1(){
        Usuario usuario = new Usuario();
        Entidad entidad = new Entidad();
        Establecimiento establecimiento = new Establecimiento();
        Localizador localizador = Localizador.getInstancia();

        Provincia provincia2 = new Provincia();
        provincia2.setNombre("Buenos Aires");

        // TESTEAMOS LA ASIGNACION DE MUNICIPIO A USUARIO
        localizador.asignarMunicipioAUsuario(usuario, "Pinamar", "Buenos Aires");
        Assertions.assertEquals("Pinamar", usuario.getUbicacion().getNombre());

        // TESTEAMOS LA ASIGNACION DE PROVINCIA A ENTIDAD
        localizador.asignarProvinciaAEntidad(entidad,"Buenos Aires");
        Assertions.assertEquals("Buenos Aires", entidad.getUbicacion().getNombre());

        // TESTEAMOS LA ASIGNACION DE MUNICIPIO A ESTABLECIMIENTO
        localizador.asignarMunicipioAEstablecimiento(establecimiento, "Pinamar", "Buenos Aires");
        Assertions.assertEquals("Pinamar", establecimiento.getUbicacion().getNombre());

    }
    @Test
    @DisplayName("Prueba del Localizador: Otras funciones.")
    public void testeoLocalizador2() {
        Usuario usuario = new Usuario();
        Entidad entidad = new Entidad();
        Establecimiento establecimiento = new Establecimiento();
        Localizador localizador = Localizador.getInstancia();

        // VARIABLES DE EJEMPLO
        Provincia provincia = new Provincia();
        provincia.setNombre("Buenos Aires");
        Municipio municipio = new Municipio();
        municipio.setNombre("Pinamar");
        municipio.setProvincia(provincia);

        // TESTEAMOS LA FUNCION QUE BUSCA LA PROVINCIA A LA QUE PERTENECE EL MUNICIPIO
        Assertions.assertEquals("Buenos Aires", localizador.obtenerLaProvinciaDe(municipio).getNombre());

        // TESTEAMOS LA FUNCION QUE VERIFICA QUE UN MUNICIPIO PERTENECE A UNA PROVINCIA ESPECIFICA
        Assertions.assertEquals(true, localizador.municipioSeEncuentraEnLaProvincia(municipio,provincia));

        // TESTEAMOS LA FUNCION QUE VERIFICA QUE 2 MUNICIPIOS DISTINTOS PERTENECEN A LA MISMA PROVINCIA
        Municipio municipio2 = new Municipio();
        municipio2.setNombre("Villa Gesel");
        municipio2.setProvincia(provincia);
        Assertions.assertEquals(true, localizador.seEncuentranEnLaMismaProvincia(municipio,municipio2));
    }
}

