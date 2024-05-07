package entities.calculadoraConfianza;

import entities.calculadoraConfianza.entidades.ComunidadOutput;
import entities.calculadoraConfianza.entidades.ComunidadInput;
import org.springframework.web.bind.annotation.RequestBody;
import retrofit2.Call;
import retrofit2.http.POST;


public interface CalculadoraConfianzaService {
    @POST("calculador/comunidad/usuarios")  // Chequear si este parámetro está bien con Postman.
    Call<ComunidadOutput> calcular(@RequestBody ComunidadInput comunidad);
}
