package entities.entidades;

import entities.geoRef.entidades.Coordenadas;
import entities.geoRef.entidades.Localizacion;
import entities.geoRef.entidades.Municipio;
import entities.repositorio.RepoEntidades;
import entities.repositorio.RepoEstablecimientos;
import entities.repositorio.RepoServicios;
import lombok.*;
import entities.serviciosPub.Servicio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Entity
@Table(name="establecimiento")
public class Establecimiento {
    @Id
    @GeneratedValue
    @Getter private Long id;

    @Column(name="nombre")
    @Setter @Getter private String nombre;

    @Embedded
    @Getter @Setter private Municipio ubicacion;

    @Embedded
    @Getter @Setter private Coordenadas coordenadas;

    @OneToMany
    @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
    @Setter @Getter private List<Servicio> serviciosDisponibles;

    public void agregarServicio(Servicio servicioNuevo){
        Collections.addAll(this.serviciosDisponibles, servicioNuevo);
        new RepoServicios().guardar(servicioNuevo);
        new RepoEstablecimientos().actualizar(this);
    }
    public boolean contieneServicio(String nombreServicio){
        return serviciosDisponibles.stream().map(
                servicio -> servicio.getNombre()).toList().contains(nombreServicio);
    }

    public  Establecimiento(){
        this.serviciosDisponibles = new ArrayList<>();
    }
}
