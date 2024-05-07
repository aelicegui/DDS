package controllers;

import entities.repositorio.*;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Usuario": controller = new UsuarioController(new RepoUsuarios()); break;
            case "Comunidad": controller = new ComunidadController(new RepoComunidades()); break;
            case "Ranking": controller = new RankingController(new RepoRankingSemanal()); break;
            case "Incidente": controller = new IncidenteController(new RepoIncidentes()); break;
            case "Miembro": controller = new MiembroController(new RepoMiembros()); break;
            case "CSV": controller = new CSVController(new RepoEntidades(), new RepoEstablecimientos(), new RepoServicios()); break;
          //  case "Servicios": controller = new ServiciosController(new RepositorioDeServicios()); break;
          //  case "Tareas": controller = new TareasController(new RepositorioDeServicios()); break;
        }
        return controller;
    }
}
