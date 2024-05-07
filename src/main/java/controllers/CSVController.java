package controllers;

import entities.comunidad.Comunidad;
import entities.comunidad.Miembro;
import entities.comunidad.Usuario;
import entities.config.Config;
import entities.csv.EstrategiaODC;
import entities.csv.EstrategiaPrestadora;
import entities.csv.Importador;
import entities.repositorio.RepoEntidades;
import entities.repositorio.RepoEstablecimientos;
import entities.repositorio.RepoMiembros;
import entities.repositorio.RepoServicios;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVController extends Controller{

    RepoEntidades repoEntidades;
    RepoEstablecimientos repoEstablecimientos;
    RepoServicios repoServicios;

    public CSVController(RepoEntidades repoEntidades, RepoEstablecimientos repoEstablecimientos, RepoServicios repoServicios) {
        this.repoEntidades = repoEntidades;
        this.repoEstablecimientos = repoEstablecimientos;
        this.repoServicios = repoServicios;
    }

    public void show(Context context) {
        Usuario usuarioLogueado = this.usuarioLogueado(context);
        if(usuarioLogueado == null){
            context.redirect("/login");
        }else {
            Map<String, Object> model = new HashMap<>();
            this.render(context,"cargaDeDatos.hbs", model);
        }

    }

    public void upload(Context context) {
        Usuario usuarioLogueado = this.usuarioLogueado(context);
        if(usuarioLogueado == null){
            context.redirect("/login");
        }else {
            Map<String, Object> model = new HashMap<>();
            UploadedFile uploadedFile = context.uploadedFiles("archivo").get(0);
            try{
                if(uploadedFile != null) {
                    String nombreArchivo = uploadedFile.filename(); // Obtener el nombre del archivo
                    convertUploadedFileToFile(uploadedFile, nombreArchivo);
                    Importador importador = new Importador(new EstrategiaPrestadora());
                    importador.importar(Config.RUTA_ARCHIVOSCSV_CREADOS + nombreArchivo);
                    model.put("cargado", true);
                    this.render(context, "respuestaDatos.hbs", model);
                }
            }catch (Exception exp){
                exp.printStackTrace();
                model.put("cargado", false);
                this.render(context, "respuestaDatos.hbs", model);
            }
        }

    }
    private static void convertUploadedFileToFile(UploadedFile uploadedFile, String nombreArchivo) throws IOException {
        File file = new File(Config.RUTA_ARCHIVOSCSV_CREADOS + nombreArchivo);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            uploadedFile.content().transferTo(fos); // Copia el contenido del UploadedFile al archivo
        }
    }
}
