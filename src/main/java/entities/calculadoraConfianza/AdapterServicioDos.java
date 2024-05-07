package entities.calculadoraConfianza;

import entities.calculadoraConfianza.entidades.*;
import entities.comunidad.Comunidad;
import entities.comunidad.Usuario;
import entities.serviciosPub.Incidente;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AdapterServicioDos implements InterfazAdapter {
    private ServicioCalculadoraConfianza servicioCalculadora = ServicioCalculadoraConfianza.instancia();
    private static final float puntajeInicial = 5;

    public String calcularConfianzaIndividual(Usuario usuario, Comunidad comunidad) {
        int idUser = usuario.getId().intValue();

        ComunidadOutput comunidadOutput = this.calcularConfianzaDe(comunidad);
        Optional<UsuarioOutput> usuarioBuscado = comunidadOutput.getUsuarios_output().stream().filter(u -> u.getId() == idUser).findFirst();
        if(usuarioBuscado.isPresent()) {
            return usuarioBuscado.get().getNivel_de_confianza();
        }
        else {
            System.out.println("No existe el usuario buscado.");
            return null;
        }
    }

    public float calcularConfianzaComunitaria(Comunidad comunidad) {
        return this.calcularConfianzaDe(comunidad).getNivel_de_confianza();
    }

    @SneakyThrows
    private ComunidadOutput calcularConfianzaDe(Comunidad comunidad) {
        ComunidadInput comunidadInput = this.convertirEnInput(comunidad);
        ComunidadOutput comunidadOutput = servicioCalculadora.calcularConfianza(comunidadInput);
        return comunidadOutput;
    }

    private ComunidadInput convertirEnInput(Comunidad comunidad) {
        List<UsuarioInput> usuarios = comunidad.getMiembros().stream().map(m -> this.convertirUsuario(m.getUsuario())).toList();
        List<IncidenteInput> incidentes = comunidad.getIncidentes().stream().map(i -> this.convertirIncidente(i)).toList();

        ComunidadInput comunidadInput = new ComunidadInput();
        comunidadInput.setUsuarios(usuarios);
        comunidadInput.setIncidentes(incidentes);
        return comunidadInput;
    }

    private UsuarioInput convertirUsuario(Usuario usuario) {
        UsuarioInput user = new UsuarioInput();
        user.setId(usuario.getId().intValue());
        user.setPuntaje_inicial(puntajeInicial);
        return user;
    }

    private IncidenteInput convertirIncidente(Incidente incidente) {
        IncidenteInput incidenteInput = new IncidenteInput();
        incidenteInput.setId(incidente.getId().intValue());
        incidenteInput.setId_establecimiento(incidente.getEstablecimiento().getId().toString());
        incidenteInput.setId_servicio_afectado(incidente.getServicio().getId().toString());
        LocalDateTime fechaHoraApertura = incidente.getFechaHoraApertura().withNano(0).withSecond(0);
        incidenteInput.setFecha_de_apertura(fechaHoraApertura.toString());
        LocalDateTime fechaHoraClausura = incidente.getFechaHoraApertura().withNano(0).withSecond(0);
        incidenteInput.setFecha_de_cierre(fechaHoraClausura.toString());
        incidenteInput.setId_usuario_de_apertura(incidente.getUsuario().getId().intValue());
        //incidenteInput.setId_usuario_de_cierre(); // Nuestros Incidentes no guardan al usuario que lo cierra. Hay que cambiar eso, así podemos obtenerlo para usarlo acá.
        return incidenteInput;
    }
}
