package apiservicio.fusion;

import apiservicio.comunidad.Comunidad;
import apiservicio.criterios.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Receptor {
    private List<Criterio> criterios;
    private List<Propuesta> propuestasCreadas;   // Acá almacenamos las propuestasActivas para filtrar aquellas Comunidades que YA tienen una propuesta hecha, ya que el TP dice que una Comunidad no puede tener 2 propuestas al mismo tiempo.

    public Receptor() {
        this.propuestasCreadas = new ArrayList<>();
        this.criterios = new ArrayList<>();
        this.criterios.add(new CriterioConfianza());
        this.criterios.add(new CriterioEstablecimiento());
        this.criterios.add(new CriterioUsuario());
        this.criterios.add(new CriterioServicio());
    }

    //TODO: Este es el método principal. Nuestra API recibe un listado de comunidades (a traves de la Query) y nosotros generamos las propuestas.
    public List<Propuesta> generarPropuestas(List<Comunidad> comunidades){
        return comunidades.stream().flatMap(comunidad1 ->
                this.esFusionable(comunidad1, comunidades)).toList();
    }

    public Stream<Propuesta> esFusionable(Comunidad comunidad, List<Comunidad> comunidades){
        return comunidades.stream().map(comunidad1 ->
                intentarFusion(comunidad1, comunidad)).filter(Objects::nonNull);
    }

    public Propuesta intentarFusion(Comunidad comunidad1, Comunidad comunidad2){
        Propuesta propuestaNueva = null;
        if(this.sonFusionables(comunidad1, comunidad2)){
            propuestaNueva = new Propuesta(comunidad1, comunidad2);
            this.propuestasCreadas.add(propuestaNueva);
        }
        return propuestaNueva;
    }

    public boolean sonFusionables(Comunidad comunidad1, Comunidad comunidad2) {
        boolean compatibles = false;
        if(comunidad1 != comunidad2){
            if(this.criterios.stream().allMatch(criterio -> criterio.cumpleCriterio(comunidad1, comunidad2))){
                if(this.propuestasCreadas.stream().noneMatch(propuesta -> propuesta.participa(comunidad1)) ||
                        this.propuestasCreadas.stream().noneMatch(propuesta -> propuesta.participa(comunidad2))){
                    compatibles = true;
                }
            }
        }
        return compatibles;
    }

    /*public List<Propuesta> propuestasUltimos6Meses(){
        return propuestas.stream().filter(propuesta -> propuesta.getEstado() != EstadoPropuesta.EN_ESPERA &&
                propuesta.getFechaCreacion().isAfter(LocalDate.now().minusMonths(6))).toList();
    }*/
}

