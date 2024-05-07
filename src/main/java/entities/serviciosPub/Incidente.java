package entities.serviciosPub;

import entities.comunidad.Comunidad;
import entities.comunidad.Usuario;
import entities.entidades.Entidad;
import entities.entidades.Establecimiento;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Entity
@Table(name="incidente")
public class Incidente {
    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name="servicio_id", referencedColumnName = "id")
    @Getter @Setter private Servicio servicio;

    @ManyToOne
    @JoinColumn(name="usuario_id", referencedColumnName = "id")
    @Getter @Setter private Usuario usuario;

    @Column(name="fechaHoraApertura", columnDefinition = "datetime")
    @Getter @Setter private LocalDateTime fechaHoraApertura;

    @ManyToOne
    @JoinColumn(name = "entidad_id", referencedColumnName = "id")
    @Getter @Setter private Entidad entidad;

    @ManyToOne
    @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
    @Getter @Setter private Establecimiento establecimiento;

    @Column(name="descripcion", columnDefinition = "text")
    @Getter @Setter private String descripcion;

    @Column(name="fechaHoraCierre", columnDefinition = "datetime")
    @Getter @Setter private LocalDateTime fechaHoraCierre;

    @Column(name="estado")
    @Enumerated(EnumType.STRING)
    @Getter @Setter private EstadoIncidente estadoIncidente;

    @ManyToOne
    @JoinColumn(name ="comunidad_id", referencedColumnName = "id")
    @Getter @Setter private Comunidad comunidad;

    public Incidente(Comunidad comunidad, Servicio servicio, Entidad entidad, Establecimiento establecimiento, Usuario usuario, String descripcion){
        this.comunidad = comunidad;
        this.servicio = servicio;
        this.entidad = entidad;
        this.establecimiento = establecimiento;
        this.fechaHoraApertura = LocalDateTime.now();
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.estadoIncidente =  EstadoIncidente.ABIERTO;
    }

    public Incidente() {
        this.estadoIncidente =  EstadoIncidente.ABIERTO;
    }

    public void marcarComoResuelto(Usuario usuario){
        this.estadoIncidente = EstadoIncidente.RESUELTO;
        this.fechaHoraCierre = LocalDateTime.now();
        this.servicio.cambiarEstado(EstadoServicio.EN_FUNCIONAMIENTO, usuario);
    }

    public long tiempoDeCierre(){
       Duration duracion = Duration.between(fechaHoraApertura, fechaHoraCierre);
       long duracionSegundos = duracion.toSecondsPart();
       return duracionSegundos;
    }

    public String fechaAperturaFormateada(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return this.getFechaHoraApertura().format(formatter);
    }

    public String fechaCierreFormateada(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return this.getFechaHoraCierre().format(formatter);

    }

}
