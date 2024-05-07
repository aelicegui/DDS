import entities.comunidad.Comunidad;
import entities.comunidad.Miembro;
import entities.comunidad.Usuario;
import entities.entidades.Entidad;
import entities.entidades.Establecimiento;
import entities.notificador.*;
import entities.serviciosPub.Incidente;
import entities.notificador.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import entities.ranking.RankingSegunCantidad;
import entities.ranking.RankingSegunTiempo;
import entities.serviciosPub.Servicio;
import entities.serviciosPub.ServicioSimple;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;


public class test3erEntrega {

    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;
    private Miembro miembro1;
    private Miembro miembro2;
    private Miembro miembro3;

    private Establecimiento establecimiento;
    private Entidad entidad;
    private Servicio servicio;
    private Comunidad comunidad;
    private NotificadorWhatsApp notificadorWPP;
    private NotificadorMail notificadorMail;
    private Sincronico asincronico;
    private Calendarizada calendarizada;
    private EstrategiaNotificacion estrategiaNotificacion1;
    private EstrategiaNotificacion estrategiaNotificacion2;


    @BeforeEach
    public void init() {
        this.usuario1 = new Usuario();
        this.usuario2 = new Usuario();
        this.usuario3 = new Usuario();
        this.miembro1 = new Miembro(usuario1, comunidad);
        this.miembro2 = new Miembro(usuario2, comunidad);
        this.miembro3 = new Miembro(usuario3, comunidad);
        this.usuario1.getPerfiles().add(miembro1);
        this.usuario2.getPerfiles().add(miembro2);
        this.usuario2.getPerfiles().add(miembro2);

        this.comunidad = new Comunidad();
        comunidad.agregarMiembro(miembro3);

        this.establecimiento = new Establecimiento();
        this.entidad = new Entidad();
        this.servicio = new ServicioSimple();
        entidad.agregarEstablecimiento(establecimiento);
        establecimiento.agregarServicio(servicio);
        this.notificadorWPP = mock(NotificadorWhatsApp.class);
        this.notificadorMail = mock(NotificadorMail.class);
        this.calendarizada = new Calendarizada(LocalTime.now().plusSeconds(2));
        this.asincronico = new Sincronico();
        this.estrategiaNotificacion1 = new EstrategiaNotificacion();
        estrategiaNotificacion1.setMedioNotificacion(notificadorWPP);
        estrategiaNotificacion1.setTipoNotificacion(asincronico);
        this.estrategiaNotificacion2 = new EstrategiaNotificacion();
        estrategiaNotificacion2.setMedioNotificacion(notificadorMail);
        estrategiaNotificacion2.setTipoNotificacion(asincronico);
        usuario1.setEstrategiaNotificacion(estrategiaNotificacion1);
        usuario2.setEstrategiaNotificacion(estrategiaNotificacion2);
        usuario3.setEstrategiaNotificacion(estrategiaNotificacion1);
        Mockito.doNothing().when(notificadorWPP).notificar(any(), any());
        Mockito.doNothing().when(notificadorMail).notificar(any(), any());
        //RepoUsuarios.getInstancia().agregarUsuario(usuario1, usuario2, usuario3);
        usuario1.agregarServiciosInteres(servicio);
        usuario2.agregarEntidadesInteres(entidad);
    }

    @Test
    @DisplayName("Prueba apertura de incidente")
    public void aperturaIncidente(){
        Incidente incidente = new Incidente(servicio, entidad, establecimiento, usuario1, "a");
        comunidad.crearIncidente(incidente);
        Assertions.assertEquals(1, comunidad.getIncidentes().size());




    }

    @Test
    @DisplayName("Prueba notificacion por WPP por Comunidad")
    public void pruebaNotificadorWPPPorComunidad(){
        comunidad.agregarMiembro(miembro1,miembro2);
        Incidente incidente = new Incidente(servicio, entidad, establecimiento, usuario1, "a");
        comunidad.crearIncidente(incidente);
        verify(notificadorWPP, times(1)).notificar(any(), any());


    }

    @Test
    @DisplayName("Prueba notificacion por WPP por Interes")
    public void pruebaNotificadorWPPPorInteres(){
        Incidente incidente = new Incidente(servicio, entidad, establecimiento, usuario1, "a");
        comunidad.crearIncidente(incidente);
        verify(notificadorWPP, times(0)).notificar(any(), any());


    }

    @Test
    @DisplayName("Prueba notificacion por mail por comunidad")
    public void pruebaNotificadorMailPorComunidad(){
        comunidad.agregarMiembro(miembro1,miembro2);
        Incidente incidente = new Incidente(servicio, entidad, establecimiento, usuario1, "a");
        comunidad.crearIncidente(incidente);

        verify(notificadorMail, times(1)).notificar(any(), any());


    }

    @Test
    @DisplayName("Prueba notificacion por mail por Interes")
    public void pruebaNotificadorMailPorInteres(){
        Incidente incidente = new Incidente(servicio, entidad, establecimiento, usuario1, "a");
        comunidad.crearIncidente(incidente);

        verify(notificadorMail, times(1)).notificar(any(), any());


    }

    @Test
    @DisplayName("Prueba notificacion calendarizada")
    public void pruebaNotificadorCalendarizada(){
        comunidad.agregarMiembro(miembro1,miembro2);
        Incidente incidente = new Incidente(servicio, entidad, establecimiento, usuario1, "a");
        estrategiaNotificacion2.setTipoNotificacion(calendarizada);
        comunidad.crearIncidente(incidente);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        verify(notificadorMail, times(1)).notificar(any(), any());


    }

    @Test
    @DisplayName("Prueba notificacion calendarizada")
    public void incidenteYaCreado(){
        Incidente incidente = new Incidente(servicio, entidad, establecimiento, usuario1, "a");
        estrategiaNotificacion2.setTipoNotificacion(calendarizada);
        comunidad.crearIncidente(incidente);
        comunidad.crearIncidente(incidente);

        Assertions.assertEquals(1, comunidad.getIncidentes().size());
        Assertions.assertEquals(2, servicio.getHistorialDeEstados().size());


    }

    @Test
    @DisplayName("Prueba ranking cantidad")
    public void rankingCantidad(){
        Incidente incidente = new Incidente(servicio, entidad, establecimiento, usuario1, "a");
        Incidente incidente2 = new Incidente(servicio, entidad, establecimiento, usuario1, "a");
        Entidad entidad2 = new Entidad();
        Entidad entidad3 = new Entidad();
        Incidente incidente3 = new Incidente(servicio, entidad2,new Establecimiento(), usuario1, "a");
        Incidente incidente4 = new Incidente(servicio, entidad3, new Establecimiento(), usuario1, "a");

        estrategiaNotificacion2.setTipoNotificacion(calendarizada);
        comunidad.crearIncidente(incidente);
        comunidad.crearIncidente(incidente2);
        comunidad.crearIncidente(incidente3);
        comunidad.crearIncidente(incidente4);
        comunidad.resolverIncidente(incidente3, usuario2);
        comunidad.resolverIncidente(incidente4, usuario2);
        comunidad.resolverIncidente(incidente, usuario2);
        comunidad.resolverIncidente(incidente2, usuario2);

        List<Entidad> entidades = new ArrayList<>();
        Collections.addAll(entidades, entidad,entidad2,entidad3);
        RankingSegunCantidad rankingSegunCantidad = new RankingSegunCantidad();
        Assertions.assertEquals(entidad, rankingSegunCantidad.calcularRanking(entidades).getEntidades().stream().findFirst().get());


    }

    @Test
    @DisplayName("Prueba ranking tiempo")
    public void rankingTiempo() throws InterruptedException {
        Incidente incidente = new Incidente(servicio, entidad, establecimiento, usuario1, "a");
        Incidente incidente2 = new Incidente(servicio, entidad, establecimiento, usuario1, "a");
        Entidad entidad2 = new Entidad();
        Entidad entidad3 = new Entidad();
        Incidente incidente3 = new Incidente(servicio, entidad2,new Establecimiento(), usuario1, "a");
        Incidente incidente4 = new Incidente(servicio, entidad3, new Establecimiento(), usuario1, "a");

        estrategiaNotificacion2.setTipoNotificacion(calendarizada);
        comunidad.crearIncidente(incidente);
        comunidad.crearIncidente(incidente2);
        comunidad.crearIncidente(incidente3);
        comunidad.crearIncidente(incidente4);
        comunidad.resolverIncidente(incidente3, usuario2);
        comunidad.resolverIncidente(incidente4, usuario2);
        comunidad.resolverIncidente(incidente, usuario2);
        comunidad.resolverIncidente(incidente2, usuario2);
        List<Entidad> entidades = new ArrayList<>();
        Collections.addAll(entidades, entidad,entidad2,entidad3);
        RankingSegunTiempo rankingSegunTiempo = new RankingSegunTiempo();
        Assertions.assertEquals(entidad, rankingSegunTiempo.calcularRanking(entidades).getEntidades().stream().findFirst().get());


    }

}
