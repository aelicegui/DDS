package entities.entidades;

import entities.repositorio.RepoEntidades;
import entities.repositorio.RepoPrestadora;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Entity
@Table(name="prestadora")
public class PrestadoraServicio {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="nombre")
    @Getter @Setter private String nombre;

    @OneToMany
    @JoinColumn(name="prestadora_id", referencedColumnName = "id")
    @Getter private List<Entidad> entidades;

    public void agregarEntidad(Entidad entidadNueva){
        Collections.addAll(this.entidades, entidadNueva);
        new RepoEntidades().guardar(entidadNueva);
        new RepoPrestadora().actualizar(this);

    }
    public boolean contieneEntidad(String nombreEntidad){
        return entidades.stream().map(
                entidad -> entidad.getNombre()).toList().contains(nombreEntidad);
    }

    public Entidad buscarEntidad(String nombreEntidad) {
        return entidades.stream().filter(entidad -> nombreEntidad.equals(entidad.getNombre())).findFirst().get();
    }

    public PrestadoraServicio(){
        this.entidades = new ArrayList<>();
    }
}
