package entities.calculadoraConfianza;

import entities.calculadoraConfianza.entidades.ComunidadOutput;
import entities.calculadoraConfianza.entidades.ComunidadInput;
import entities.config.Config;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioCalculadoraConfianza {
    private static ServicioCalculadoraConfianza instancia = null;
    private static final String urlApi = Config.URL_API_SERVICIO_DOS;
    private final Retrofit retrofit;

    private ServicioCalculadoraConfianza() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioCalculadoraConfianza instancia(){
        if(instancia== null){
            instancia = new ServicioCalculadoraConfianza();
        }
        return instancia;
    }

    public ComunidadOutput calcularConfianza(ComunidadInput comunidad) throws IOException {
        CalculadoraConfianzaService calculadoraConfianzaService = this.retrofit.create(CalculadoraConfianzaService.class);
        Call<ComunidadOutput> requestComunidad = calculadoraConfianzaService.calcular(comunidad);
        Response<ComunidadOutput> responseComunidad = requestComunidad.execute();
        return responseComunidad.body();
    }
}