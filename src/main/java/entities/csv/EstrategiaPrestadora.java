package entities.csv;

import entities.entidades.PrestadoraServicio;
import entities.repositorio.RepoPrestadora;
import lombok.Getter;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstrategiaPrestadora implements EstrategiaImportacion{
    private RepoPrestadora repositorio;

    public EstrategiaPrestadora(){
        this.repositorio = new RepoPrestadora();
    }

    public void importar(String[] campos){

        PrestadoraServicio prestadora;
        try{
            prestadora = this.buscarPrestadoraServicio(campos[1]);
            new ImportadorEntidad().importarEntidad(prestadora, campos);
        }catch(NoResultException exp){
            prestadora = new PrestadoraServicio();
            prestadora.setNombre(campos[1]);
            this.guardarPrestadora(prestadora);
            new ImportadorEntidad().importarEntidad(prestadora, campos);
        }
    }

    public void guardarPrestadora(PrestadoraServicio prestadoraNuevas){
        repositorio.guardar(prestadoraNuevas);
    }

    public PrestadoraServicio buscarPrestadoraServicio(String nombrePrestadora) {
        return repositorio.getPrestadoraXNombre(nombrePrestadora);
    }

}