package entities.serviciosPub;

import entities.comunidad.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="cambiodeestado")
public class CambioDeEstado{
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Setter @Getter private EstadoServicio estado;

    @ManyToOne
    @JoinColumn(name="usuario_id", referencedColumnName = "id")
    @Setter @Getter private Usuario usuario;

    @Column(name="fecha", columnDefinition = "Timestamp")
    @Setter @Getter private LocalDateTime fechaHora;

    public CambioDeEstado(EstadoServicio nuevoEstado, Usuario usuario){
        this.estado = nuevoEstado;
        this.usuario = usuario;
        this.fechaHora = LocalDateTime.now();
    }

    public CambioDeEstado() {

    }
}


