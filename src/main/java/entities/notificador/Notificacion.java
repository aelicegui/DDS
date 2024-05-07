package entities.notificador;

import entities.Converters.MedioNotificacionAttributeConverter;
import entities.comunidad.Usuario;
import entities.serviciosPub.Incidente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "notificacion")
public class Notificacion {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "incidente_id", referencedColumnName = "id")
    @Setter @Getter private Incidente incidente;

    @Column(name="medioNotificacion")
    @Convert(converter = MedioNotificacionAttributeConverter.class)
    @Setter @Getter private MedioNotificacion medioNotificacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @Setter @Getter private Usuario usuario;

    public Notificacion(Incidente incidente, MedioNotificacion medioNotificacion,Usuario usuario){
        this.incidente = incidente;
        this.medioNotificacion = medioNotificacion;
        this.usuario = usuario;
    }

    public Notificacion() {

    }
}
