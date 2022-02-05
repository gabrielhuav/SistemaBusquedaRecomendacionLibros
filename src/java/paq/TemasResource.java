
package paq;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.parser.ParseException;


@Path("Temas")
public class TemasResource {

    @Context
    private UriInfo context;
    
    // Manajo de identificadores de los usuarios.
    UtilDB util = new UtilDB();

    public TemasResource() {
    }

    //Recuperamos lista en JSON de Temas.
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<temas> getListaTemas() throws ClassNotFoundException { 
        List<temas> listatemas = util.cargaListaTemas();
        return listatemas  ; 
    }
    
    //Regres la listas de los temas preferidos de un usuario
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Gustotemas> getHtml(@PathParam("id") int id) throws ClassNotFoundException { 
        List<Gustotemas> lista = util.cargaListaGustoTemas(id);
        return lista ; 
    }
    
    @GET
    @Path("lista/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecomiendaTema> getlistaRecomendacion(@PathParam("id") int id) throws ClassNotFoundException { 
        List<RecomiendaTema> lista = util.cargaListaRecomendacionTema(id);
        return lista ; 
    }
    
    //Metodo para registarar preferencia de tema
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postNuevoCliente(
            @FormParam("tema") String tema, 
            @FormParam("idUsuario") String idUsuario, 
            @FormParam("idTema") String clave ) throws ClassNotFoundException, URISyntaxException, NoSuchAlgorithmException {
        //Usuario usuarioNuevo = new Usuario(nombre, apellidoP, apellidoM, edad, genero, correo, userU, passwordU);
        //util.registraUsuario(usuarioNuevo);
        util.temasFavoritos(idUsuario, clave, tema);
        
        
        String uri = "../PreferenciaTema.html?q="  + idUsuario ;
        java.net.URI location = new java.net.URI(uri);
        return Response.temporaryRedirect(location).build();

    }
    
    @POST
    @Path("/registraGusto")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postLogin(
            @FormParam("claveTema") String clave,
            @FormParam("idUsuario1") String idUsuario,
            @FormParam("temaUsuario") String tema ) throws ClassNotFoundException, URISyntaxException, NoSuchAlgorithmException, SQLException, ParseException {
            RecomiendaTema recomienda = util.RecomiendaPorTema(clave, tema,idUsuario);
            util.registarRecomendacionTema(recomienda);
            
            String a = recomienda.getTitulo().replaceAll(" ", "+");
            String b = recomienda.getNombreAutor().replaceAll(" ", "+");   
            String c = recomienda.getIdLibro().replaceAll(" ", "+");
            String d = recomienda.getIdAutor().replaceAll(" ", "+");
            String e = tema.replaceAll(" ", "+");;  
        
        String uri = "../PreferenciaTema.html?q="+ idUsuario + "&a=" + a + "&b=" + b + "&c=" + c + "&d=" + d + "&e=" + e ;
        java.net.URI location = new java.net.URI(uri);
        return Response.temporaryRedirect(location).build();

        
    }
    
    
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
}
