package entities.comunidad;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="miembro")
public class Miembro {
    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Column(name="nickname")
    @Setter @Getter private String nickname;

    @Column(name="Admin")
    @Setter @Getter private boolean admin;

    @Column(name="Rol")
    @Setter @Getter private String rol; //"afectado" "observador"

    @ManyToOne
    @JoinColumn(name ="comunidad_id", referencedColumnName = "id")
    @Setter @Getter private Comunidad comunidad;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @Setter @Getter private Usuario usuario;

    public Miembro(Usuario usuario, Comunidad comunidad){
        this.usuario = usuario;
        this.comunidad = comunidad;
        this.admin = false;
    }


    public Miembro() {

    }
}