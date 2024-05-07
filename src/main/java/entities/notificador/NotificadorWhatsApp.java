package entities.notificador;

import entities.comunidad.Usuario;
import entities.serviciosPub.EstadoIncidente;
import entities.serviciosPub.Incidente;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class NotificadorWhatsApp implements MedioNotificacion{
    //Desacoplar
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public String toString(){
        return "WhatsApp";
    }

    @Override
    public void notificar(Incidente incidente, Usuario usuario){
        PhoneNumber telefono = new PhoneNumber(usuario.getTelefono());
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        telefono,
                        new com.twilio.type.PhoneNumber("whatsapp:+?????????????"),
                        mensajeIncidente(incidente))
                .create();

    }

    private String mensajeRevision(Incidente incidente) {
        return "El servicio "+incidente.getServicio().getNombre()+" de "+ incidente.getEntidad().getNombre()
                + " ubicado en "+ incidente.getEntidad().getUbicacion().getNombre() +
                ", se encuentra marcado como fuera de servicio. Se sugiere su revisión, en caso de que se encuentre" +
                "en funcionamiento marque como resuelto el incidente.";
    }

    private String mensajeIncidente(Incidente incidente) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String strFecha;
        String mensaje;
        if (incidente.getEstadoIncidente() == EstadoIncidente.ABIERTO){
            strFecha = dateFormat.format(incidente.getFechaHoraApertura());
            mensaje = "Nuevo incidente abierto en el servicio " + incidente.getServicio().getNombre() +
                    " el " + strFecha + " en el establecimiento " + incidente.getEstablecimiento().getNombre() + " ubicado en " +
                    incidente.getEstablecimiento().getUbicacion().getNombre() + ", el usuario " +
                    incidente.getUsuario().getUsername() + " ha reportado el incidente con la siguiente descripción: " +
                    incidente.getEstadoIncidente()+".";
        }
        else{
            strFecha = dateFormat.format(incidente.getFechaHoraCierre());
            mensaje = "Se ha marcado como resuelto el incidente en el servicio " + incidente.getServicio().getNombre()+
                    " el " + strFecha + " en el establecimiento " + incidente.getEstablecimiento().getNombre() + " ubicado en " +
                    incidente.getEstablecimiento().getUbicacion().getNombre() + ".";
        }
        return mensaje;
    }
}
