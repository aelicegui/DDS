package entities.fusiones;

import entities.comunidad.Comunidad;
import entities.comunidad.Miembro;
import entities.config.Config;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import entities.serviciosPub.Incidente;

import java.io.IOException;
import java.util.List;


public class ServicioUno {
    private static ServicioUno instancia = null;
    private static final String urlApi = Config.URL_API_SERVICIO_UNO;
    private final Retrofit retrofit;
    private ServicioUno() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioUno instancia(){
        if(instancia== null){
            instancia = new ServicioUno();
        }
        return instancia;
    }

    private List<ComunidadApi> convertirComunidades(List<Comunidad> comunidades) {
        return comunidades.stream().map(comunidad -> convertirComunidad(comunidad)).toList();
    }

    public List<Propuesta> generarPropuestasFusion(List<Comunidad> comunidades) throws IOException {
        List<ComunidadApi> comunidadesApi =this.convertirComunidades(comunidades);
        ServicioFusionador servicioFusionador = this.retrofit.create(ServicioFusionador.class);
        Call<List<Propuesta>> requestListaPropuestas = servicioFusionador.generarPropuestas(comunidadesApi);
        Response<List<Propuesta>> respuestaListaPropuestas = requestListaPropuestas.execute();
        return respuestaListaPropuestas.body();
    }

    private ComunidadApi convertirComunidad(Comunidad comunidad) {
        return new ComunidadApi(comunidad.getId(),
                comunidad.getMiembros().stream().map(miembro -> this.convertirMiembro(miembro)).toList(),
                comunidad.getIncidentes().stream().map(incidente -> this.convertirIncidente(incidente)).toList(),
                comunidad.getConfianza());
    }

    private MiembroApi convertirMiembro(Miembro miembro){
        return new MiembroApi(miembro.getId(), miembro.getUsuario().getId());
    }

    private IncidenteApi convertirIncidente(Incidente incidente){
        return new IncidenteApi(incidente.getId(), incidente.getServicio().getId(), incidente.getEstablecimiento().getId());
    }

    public Propuesta aceptar(Propuesta propuesta) throws IOException {
        ServicioFusionador servicioFusionador = this.retrofit.create(ServicioFusionador.class);
        Call<Propuesta> requestPropuestaAceptada = servicioFusionador.aceptar(propuesta);
        Response<Propuesta> respuestaPropuestaAceptada = requestPropuestaAceptada.execute();
        return respuestaPropuestaAceptada.body();
    }

    public Propuesta rechazar(Propuesta propuesta) throws IOException {
        ServicioFusionador servicioFusionador = this.retrofit.create(ServicioFusionador.class);
        Call<Propuesta> requestPropuestaRechazada = servicioFusionador.rechazar(propuesta);
        Response<Propuesta> respuestaPropuestaRechazada = requestPropuestaRechazada.execute();
        return respuestaPropuestaRechazada.body();
    }
}
