package entities.geoRef;

import entities.config.Config;
import entities.geoRef.entidades.ListadoMunicipios;
import entities.geoRef.entidades.ListadoProvincias;
import entities.geoRef.entidades.Provincia;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioGeoref {
    private static ServicioGeoref instancia = null;
    private static final int maximaCantidadRegistrosDefault = 200;
    private static final String urlApi = Config.URL_API;
    private final Retrofit retrofit;

    private ServicioGeoref() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioGeoref instancia(){
        if(instancia== null){
            instancia = new ServicioGeoref();
        }
        return instancia;
    }

    public ListadoProvincias listadoProvincias() throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoProvincias> requestProvinciasArgentinas = georefService.provincias();
        Response<ListadoProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
        return responseProvinciasArgentinas.body();
    }

    public ListadoMunicipios listadoMunicipiosProvincia(Provincia provincia) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
        Response<ListadoMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
        return responseListadoDeMunicipios.body();
    }
}
