package entities.fusiones;

import entities.comunidad.Comunidad;

import java.util.List;

public class ClienteServicioUno{
    private static ClienteServicioUno instancia = null;
    private InterfazAdapter adapter;

    public static ClienteServicioUno getInstancia(){
        if(instancia == null){
            instancia = new ClienteServicioUno();
        }
        return instancia;
    }
    private ClienteServicioUno(){
        adapter = new AdapterServicioUno();
    }

    // Métodos que se comunican con el Adapter
    public List<Propuesta> generarPropuestasFusion(List<Comunidad> comunidades) {
        return adapter.generarPropuestasFusion(comunidades);
    }

    public Propuesta aceptar(Propuesta propuesta) {
        return adapter.aceptar(propuesta);
    }

    public Propuesta rechazar(Propuesta propuesta) {
        return adapter.rechazar(propuesta);
    }

}

/* Métodos de InterfazAdapter que entiende ClienteServicioUno
    public List<Propuesta> generarPropuestasFusion(List<Comunidad> comunidades);

    public Propuesta aceptar(Propuesta propuesta);

    public Propuesta rechazar(Propuesta propuesta);
*/
