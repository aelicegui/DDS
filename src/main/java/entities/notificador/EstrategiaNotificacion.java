package entities.notificador;

import entities.Converters.MedioNotificacionAttributeConverter;
import entities.comunidad.Usuario;
import entities.serviciosPub.Incidente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="estrategiaNotificacion")
public class EstrategiaNotificacion{
    @Id
    @GeneratedValue
    private Long id;

    @Convert(converter = MedioNotificacionAttributeConverter.class)
    @Setter @Getter private MedioNotificacion medioNotificacion;

    @ManyToOne
    @JoinColumn(name="tipoNotificacion_id", referencedColumnName = "id")
    @Setter @Getter private TipoNotificacion tipoNotificacion;

    public void notificar(Incidente incidente, Usuario usuario){
        if(usuario != incidente.getUsuario()){
            tipoNotificacion.notificar(new Notificacion(incidente, this.medioNotificacion, usuario));
        }

    }
}
