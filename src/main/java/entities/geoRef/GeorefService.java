package entities.geoRef;

import entities.geoRef.entidades.ListadoMunicipios;
import entities.geoRef.entidades.ListadoProvincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefService {
    @GET("provincias")
    Call<ListadoProvincias> provincias();

    @GET("provincias")
   Call<ListadoProvincias> provincias(@Query("campos") String campos);

    @GET("municipios")
    Call<ListadoMunicipios> municipios(@Query("provincia") int idProvincia);

    @GET("municipios")
    Call<ListadoMunicipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos);

    @GET("municipios")
    Call<ListadoMunicipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos, @Query("max") int max);
}
