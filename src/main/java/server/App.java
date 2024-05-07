package server;

import entities.comunidad.Comunidad;
import entities.comunidad.Miembro;
import entities.comunidad.Usuario;
import entities.entidades.Entidad;
import entities.entidades.Establecimiento;
import entities.geoRef.entidades.Localizador;
import entities.notificador.EstrategiaNotificacion;
import entities.notificador.NotificadorMail;
import entities.notificador.Sincronico;
import entities.notificador.TipoNotificacion;
import entities.ranking.Ranker;
import entities.ranking.Ranking;
import entities.ranking.RankingSegunCantidad;
import entities.ranking.RankingSemanal;
import entities.repositorio.*;
import entities.serviciosPub.Incidente;
import entities.serviciosPub.ServicioSimple;

import java.util.List;

public class App {

    public static void main(String[] args) {
        Server.init();
        /*
        RepoEntidades repoEntidades = new RepoEntidades();

        Entidad entidad2 = new Entidad();
        entidad2.setNombre("entidad2");
        repoEntidades.guardar(entidad2);
        Entidad entidad3 = new Entidad();
        entidad3.setNombre("entidad3");
        repoEntidades.guardar(entidad3);
        Entidad entidad4 = new Entidad();
        entidad4.setNombre("entidad4");
        repoEntidades.guardar(entidad4);
        Entidad entidad5 = new Entidad();
        entidad5.setNombre("entidad5");
        repoEntidades.guardar(entidad5);
        Entidad entidad6 = new Entidad();
        entidad6.setNombre("entidad6");
        repoEntidades.guardar(entidad6);
        Entidad entidad7 = new Entidad();
        entidad7.setNombre("entidad7");
        repoEntidades.guardar(entidad7);
        Entidad entidad8 = new Entidad();
        entidad8.setNombre("entidad8");
        repoEntidades.guardar(entidad8);
        new Ranker().generateWeeklyRanking();*/


    /*    Usuario usuario = new Usuario();
        usuario.setUsername("aelicegui");
        usuario.setContrasenia("123");
        usuario.setMail("fghbng@yopmail.com");
        EstrategiaNotificacion estrategiaNotificacion = new EstrategiaNotificacion();
        estrategiaNotificacion.setMedioNotificacion(new NotificadorMail());
        TipoNotificacion tipoNotificacion = new Sincronico();
        new RepoTipoNotificacion().guardar(tipoNotificacion);
        estrategiaNotificacion.setTipoNotificacion(tipoNotificacion);
        new RepoEstrategiaNotificacion().guardar(estrategiaNotificacion);
        usuario.setEstrategiaNotificacion(estrategiaNotificacion);
        new RepoUsuarios().guardar(usuario);
        Comunidad comunidad1 = new Comunidad();
        comunidad1.setNombre("Alta Comunidad");
        Comunidad comunidad2 = new Comunidad();
        comunidad2.setNombre("La Re Comunidad");
        Comunidad comunidad3 = new Comunidad();
        comunidad3.setNombre("Piola Comunidad");
        Miembro miembro1 = new Miembro(usuario, comunidad1);
        Miembro miembro2 = new Miembro(usuario, comunidad2);
        Miembro miembro3 = new Miembro(usuario, comunidad3);
        RepoComunidades repoComunidades =  new RepoComunidades();
        repoComunidades.guardar(comunidad1);
        repoComunidades.guardar(comunidad2);
        repoComunidades.guardar(comunidad3);
        miembro1.setAdmin(true);
        RepoMiembros repoMiembros = new RepoMiembros();
        repoMiembros.guardar(miembro1);
        repoMiembros.guardar(miembro2);
        repoMiembros.guardar(miembro3);
        RepoEntidades repoEntidades = new RepoEntidades();
        RepoEstablecimientos repoEstablecimientos = new RepoEstablecimientos();
        RepoServicios repoServicios = new RepoServicios();
        RepoIncidentes repoIncidentes = new RepoIncidentes();
        ServicioSimple servicio1 = new ServicioSimple();
        servicio1.setNombre("servicio1");
        ServicioSimple servicio2 = new ServicioSimple();
        servicio2.setNombre("servicio2");
        repoServicios.guardar(servicio1);
        repoServicios.guardar(servicio2);
        Entidad entidad1 = new Entidad();
        entidad1.setNombre("entidad1");
        repoEntidades.guardar(entidad1);
        Establecimiento establecimiento1 = new Establecimiento();
        establecimiento1.setNombre("establecimiento1");
        Localizador.getInstancia().asignarMunicipioAEstablecimiento(establecimiento1, "Almafuerte","Córdoba");
        repoEstablecimientos.guardar(establecimiento1);
        Incidente incidente1 = new Incidente(servicio1, entidad1, establecimiento1, usuario, "incidente1");
        Incidente incidente2 = new Incidente(servicio2, entidad1, establecimiento1, usuario, "incidente");
        repoIncidentes.guardar(incidente1);
        repoIncidentes.guardar(incidente2);

        comunidad1.crearIncidente(incidente1);
        comunidad1.crearIncidente(incidente2);

        repoServicios.actualizar(servicio1);
        repoServicios.actualizar(servicio2);
        repoComunidades.actualizar(comunidad1);
*/

    }
}
