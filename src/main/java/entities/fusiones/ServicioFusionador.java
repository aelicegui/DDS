package entities.fusiones;

import org.springframework.web.bind.annotation.RequestBody;
import retrofit2.Call;
import retrofit2.http.POST;

import java.util.List;

public interface ServicioFusionador {
    @POST
    Call<List<Propuesta>> generarPropuestas(@RequestBody List<ComunidadApi> comunidades);

    @POST("aceptar")
    Call<Propuesta> aceptar(@RequestBody Propuesta propuesta);

    @POST("rechazar")
    Call<Propuesta> rechazar(@RequestBody Propuesta propuesta);

}

