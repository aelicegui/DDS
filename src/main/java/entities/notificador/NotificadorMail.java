package entities.notificador;

import entities.comunidad.Usuario;
import entities.serviciosPub.EstadoIncidente;
import entities.serviciosPub.Incidente;
import lombok.Getter;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class NotificadorMail implements MedioNotificacion{
    //Desacoplar
    @Getter static final private String remitenteEmail = "grupo1@gmail.com";
    @Getter static final private String remitenteClave = "ytidvtvcuokudpmp";



    public String toString(){
        return "Mail";
    }

    @Override
    public void notificar(Incidente incidente, Usuario usuario) {
        String mailDestinatario = usuario.getMail();
        enviarConGMail(mailDestinatario,
                this.asuntoMail(incidente),
                this.cuerpoMail(incidente));

    }

    private String cuerpoMailRevision(Incidente incidente) {
        return "El servicio "+incidente.getServicio().getNombre()+" de "+ incidente.getEntidad().getNombre()
                + " ubicado en "+ incidente.getEntidad().getUbicacion().getNombre() +
                ", se encuentra marcado como fuera de servicio. Se sugiere su revisión, en caso de que se encuentre" +
                "en funcionamiento marque como resuelto el incidente.";
    }

    private String cuerpoMail(Incidente incidente) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String strFecha;
        String cuerpo;
        if (incidente.getEstadoIncidente() == EstadoIncidente.ABIERTO){
            strFecha = dateFormat.format(incidente.getFechaHoraApertura());
           cuerpo = "Se ha reportado el" + strFecha + " un incidente sobre el servicio " + incidente.getServicio().getNombre() +
           " en el establecimiento " + incidente.getEstablecimiento().getNombre() + " ubicado en " +
                   incidente.getEstablecimiento().getUbicacion().getNombre() + ", el usuario " +
                   incidente.getUsuario().getUsername() + " ha reportado el incidente con la siguiente descripción: " +
                   incidente.getEstadoIncidente()+".";

        }
        else{
            strFecha = dateFormat.format(incidente.getFechaHoraCierre());
            cuerpo = "El " + strFecha + " se ha marcado como resuelto el incidente sobre el servicio " + incidente.getServicio().getNombre() +
                    " en el establecimiento " + incidente.getEstablecimiento().getNombre() + " ubicado en " +
                    incidente.getEstablecimiento().getUbicacion().getNombre() + ".";
        }
        return cuerpo;
    }

    private String asuntoMail(Incidente incidente) {
        String asunto;
        if (incidente.getEstadoIncidente() == EstadoIncidente.ABIERTO){
            asunto = "Nuevo incidente abierto en el servicio " + incidente.getServicio().getNombre();
        }
        else{
            asunto = "Se ha marcado como resuelto el incidente en el servicio " + incidente.getServicio().getNombre();
        }
        return asunto;
    }

    private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitenteEmail);
        props.put("mail.smtp.clave", remitenteClave);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitenteEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitenteEmail, remitenteClave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
    }
}
