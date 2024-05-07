package entities.fusiones;

import lombok.Getter;
import lombok.Setter;

public class MiembroApi {
    @Getter @Setter long id;
    @Getter @Setter long usuario;

    public MiembroApi(Long id, Long usuarioID) {
        this.id = id;
        this.usuario = usuarioID;
    }
}
