package entities.entidades;

import entities.geoRef.entidades.Localizacion;
import entities.repositorio.RepoEntidades;
import entities.repositorio.RepoEstablecimientos;
import lombok.Setter;
import lombok.Getter;
import entities.serviciosPub.Incidente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

@Entity
@Table(name="entidad")
public class Entidad {
     @Id
     @GeneratedValue
     @Getter private Long id;

     @Column(name = "nombre")
     @Setter @Getter private String nombre;

     @OneToMany
     @JoinColumn(name= "entidad_id", referencedColumnName = "id")//el establecimiento no conoce a la entidad
     @Setter @Getter private List<Establecimiento> establecimientos;


     @Embedded
     @Getter @Setter private Localizacion ubicacion;

     @OneToMany(mappedBy = "entidad") //el incidente tmb conoce a la entidad -> bidireccional -> mappedBy
     @Getter private List<Incidente> incidentes;

     /*
     public List<Localizacion> localizaciones(){
          return establecimientos.stream().map(establecimiento -> establecimiento.getLocalizacion()).toList();
     }*/

     public void agregarEstablecimiento(Establecimiento establecimientoNuevo){
          Collections.addAll(this.establecimientos, establecimientoNuevo);
          new RepoEstablecimientos().guardar(establecimientoNuevo);
          new RepoEntidades().actualizar(this);
     }

     public boolean contieneEstablecimiento(String nombreEstablecimiento){
          return establecimientos.stream().map(
                  establecimiento -> establecimiento.getNombre()).toList().contains(nombreEstablecimiento);
     }


     public Establecimiento buscarEstablecimiento(String nombreEstablecimiento) {
          return establecimientos.stream().filter(
                  establecimiento -> nombreEstablecimiento.equals(establecimiento.getNombre())).findFirst().get();
     }

     public Entidad(){
          this.establecimientos = new ArrayList<>();
          this.incidentes = new ArrayList<>();
     }


}

