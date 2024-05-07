package entities.notificador;

import entities.comunidad.Usuario;
import entities.serviciosPub.Incidente;


public interface MedioNotificacion{

    public void notificar(Incidente incidente, Usuario usuario);

    public String toString();
}
