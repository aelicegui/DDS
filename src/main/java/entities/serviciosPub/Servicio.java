package entities.serviciosPub;

import entities.comunidad.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="servicio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo")
public abstract class Servicio {
    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Column(name="nombre")
    @Setter @Getter protected String nombre;

    @Enumerated(EnumType.STRING)
    @Setter @Getter protected EstadoServicio estado;

    @OneToMany
    @JoinColumn(name="servicio_id", referencedColumnName = "id")
    @Getter protected List<CambioDeEstado> historialDeEstados;

    //TODO
    @OneToOne
    @JoinColumn(name="tipoServicio_id", referencedColumnName = "id")
    @Getter protected TipoServicio tipoServicio;

    public abstract void cambiarEstado(EstadoServicio nuevoEstado, Usuario usuario);

    public abstract Boolean estaDisponible();

    public abstract void agregarCambioDeEstado(CambioDeEstado... cambioDeEstadosNuevo);



}
