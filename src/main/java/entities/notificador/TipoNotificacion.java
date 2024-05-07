package entities.notificador;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_notificacion")
public abstract class TipoNotificacion {
    @Id
    @GeneratedValue
    @Setter @Getter private Long id;

    public void notificar(Notificacion notificacion){
    }

}
