package apiservicio.criterios;

import apiservicio.comunidad.Comunidad;

// Esta interfaz va a ser implementada por los Criterios que vamos a utilizar a la hora evaluar posibles candidatos para las fusiones.
public interface Criterio {
    // Algunos criterios tienen un atributo donde guardamos valores, para poder cambiarlos a futuro desde acá y no tener que tocar el resto del código.

    public boolean cumpleCriterio(Comunidad comunidad, Comunidad otraComunidad);
    /*
    Lo que tratamos de hacer en estos métodos es:
    1: Obtener una lista con todos los establecimientos (sin repetidos).
    2: Obtener una lista de los establecimientos repetidos.
    3: Calcular que porcentaje representan los repetidos dentro del total.
    4: Finalmente, comprobar la condicion de ese criterio
    */
}
