package entities.comunidad;
import com.twilio.type.PhoneNumber;
import entities.entidades.Entidad;
import entities.geoRef.entidades.Localizacion;
import entities.serviciosPub.Incidente;
import lombok.Getter;
import lombok.Setter;
import entities.notificador.EstrategiaNotificacion;
import entities.serviciosPub.Servicio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Column(name="nombre")
    @Setter @Getter private String nombre;

    @Column(name = "apellido")
    @Setter @Getter private String apellido;

    @Column(name = "nombreUsuario")
    @Setter @Getter private String username;

    @Column(name = "mail")
    @Setter @Getter private String mail;

    @Column(name = "telefono")
    @Setter @Getter private String telefono;

    @Column(name = "contrasenia")
    @Setter @Getter private String contrasenia;

    @Column(name = "ubicacion")
    @Embedded
    @Setter @Getter private Localizacion ubicacion;

    @OneToMany(mappedBy = "usuario")
    @Setter @Getter private List<Miembro> perfiles;

    @Column(name = "PermisoDeCargaDeDatos")
    @Setter @Getter private boolean permisoCargaMasivaDeDatos;

    @ManyToMany
    @JoinTable( name = "servicios_de_interes",
            joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "servicio_id"))
    @Setter @Getter private List <Servicio> serviciosDeInteres;

    @ManyToMany
    @JoinTable(name ="entidades_de_interes",
            joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "entidad_id"))
    @Setter @Getter private List<Entidad> entidadesDeInteres;

    @ManyToOne
    @JoinColumn(name = "estrategia_id", referencedColumnName = "id")
    @Setter @Getter private EstrategiaNotificacion estrategiaNotificacion;

    public Usuario(){
        this.entidadesDeInteres = new ArrayList<>();
        this.serviciosDeInteres = new ArrayList<>();
        this.perfiles = new ArrayList<>();
    }

    public void agregarServiciosInteres(Servicio... serviciosNuevos){
        Collections.addAll(this.serviciosDeInteres, serviciosNuevos);
    }

    public void agregarEntidadesInteres(Entidad... entidadesNuevas){
        Collections.addAll(this.entidadesDeInteres, entidadesNuevas);
    }


    public boolean estaInteresadoEn(Incidente incidente) {
        return serviciosDeInteres.contains(incidente.getServicio()) ||
                entidadesDeInteres.contains(incidente.getEntidad());
    }
}
