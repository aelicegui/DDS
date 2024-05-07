package entities.notificador;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Sincronico")
public class Sincronico extends TipoNotificacion{
    @Override
    public void notificar(Notificacion notificacion) {
        notificacion.getMedioNotificacion().notificar(notificacion.getIncidente(), notificacion.getUsuario());
    }
}
