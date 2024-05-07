package apiservicio.controller;

import apiservicio.comunidad.Comunidad;
import apiservicio.fusion.EstadoPropuesta;
import apiservicio.fusion.Propuesta;
import apiservicio.fusion.Receptor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/fusion")
public class FusionController {

/*
    @GetMapping
    public String verApi(){
        return "La api funciona";
    }
*/
    @PostMapping
    public List<Propuesta> generarPropuestas(@RequestBody List<Comunidad> comunidades){
        return new Receptor().generarPropuestas(comunidades);
    }

    @PostMapping("/aceptar")
    public Propuesta aceptarPropuesta(@RequestBody Propuesta propuesta){
        return propuesta.aceptar();
    }

    @PostMapping("/rechazar")
    public Propuesta rechazarPropuesta(@RequestBody Propuesta propuesta){
        return propuesta.rechazar();
    }
}
