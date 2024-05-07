package server.init;

import entities.repositorio.ConexionBDD;

public class Initializer{

    public static void init() {
        new Initializer();
                //.iniciarTransaccion()
                //.permisos()
                //.roles()
                //.commitearTransaccion();
    }

    private Initializer iniciarTransaccion() {
        ConexionBDD.crearEntityManager().getTransaction().begin();
        return this;
    }

    private Initializer commitearTransaccion() {
        ConexionBDD.crearEntityManager().getTransaction().commit();
        return this;
    }


    /*
    private Initializer permisos() {
        String[][] permisos = {
                { "Ver servicios", "ver_servicios" },
                { "Crear servicios", "crear_servicios" },
                { "Editar servicios", "editar_servicios" },
                { "Eliminar servicios", "eliminar_servicios" },
        };

        for(String[] unPermiso: permisos) {
            Permiso permiso = new Permiso();
            permiso.setNombre(unPermiso[0]);
            permiso.setNombreInterno(unPermiso[1]);
            ConexionBDD.crearEntityManager().persist(permiso);
        }

        return this;
    }

    private interface BuscadorDePermisos extends WithSimplePersistenceUnit{
        default Permiso buscarPermisoPorNombre(String nombre) {
            return (Permiso) ConexionBDD.crearEntityManager()
                    .createQuery("from " + Permiso.class.getName() + " where nombreInterno = :nombre")
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        }
    }

    private Initializer roles() {
        BuscadorDePermisos buscadorDePermisos = new BuscadorDePermisos() {};

        Rol administrador = new Rol();
        administrador.setNombre("Administrador");
        administrador.setTipo(TipoRol.ADMINISTRADOR);
        administrador.agregarPermisos(
            buscadorDePermisos.buscarPermisoPorNombre("crear_servicios")
        );
        ConexionBDD.crearEntityManager().persist(administrador);

        Rol consumidor = new Rol();
        consumidor.setNombre("Consumidor");
        consumidor.setTipo(TipoRol.NORMAL);
        consumidor.agregarPermisos(
                buscadorDePermisos.buscarPermisoPorNombre("ver_servicios")
        );
        ConexionBDD.crearEntityManager().persist(consumidor);

        Rol prestador = new Rol();
        prestador.setNombre("Prestador");
        prestador.setTipo(TipoRol.NORMAL);
        prestador.agregarPermisos(
                buscadorDePermisos.buscarPermisoPorNombre("ver_servicios")
        );
        ConexionBDD.crearEntityManager().persist(prestador);

        return this;
    }*/
}
