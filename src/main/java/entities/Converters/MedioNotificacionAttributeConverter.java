package entities.Converters;

import entities.notificador.MedioNotificacion;
import entities.notificador.NotificadorWhatsApp;
import entities.notificador.NotificadorMail;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MedioNotificacionAttributeConverter implements AttributeConverter <MedioNotificacion, String>{
    @Override
    public String convertToDatabaseColumn(MedioNotificacion medioNotificacion){
        return medioNotificacion == null ? null : medioNotificacion.toString();
    }
    @Override
    public MedioNotificacion convertToEntityAttribute (String medioNotificacion){
        return medioNotificacion == null ? null : this.toMedioNotificacion(medioNotificacion);
    }

    private MedioNotificacion toMedioNotificacion(String medioNotificacion){
        if(medioNotificacion.equals("WhatsApp")){
            return new NotificadorWhatsApp();

        }
        else{

            return new NotificadorMail();
        }
    }
}
