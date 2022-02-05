/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServlet;

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

/**
 * REST Web Service
 *
 * @author xido_
 */
@Path("AutorFavorito")
public class AutorFavoritoResource extends HttpServlet {

    @Context
    private UriInfo context;
    
    // Manajo de identificadores de los usuarios.
    private static String idUsa = "";
    private static final Map<String, GustoAutor> listaUsuarios = new ConcurrentHashMap <String, GustoAutor>();
    UtilDB util = new UtilDB();
    
    public AutorFavoritoResource() {
    }

 
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GustoAutor> getHtml(@PathParam("id") int id) throws ClassNotFoundException { 
        List<GustoAutor> lista = util.cargaListaGustoAutor(id);
        return lista ; 
    }
    
    
    @GET
    @Path("lista/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecomiendaAutor> getlistaRecomendacion(@PathParam("id") int id) throws ClassNotFoundException { 
        List<RecomiendaAutor> lista = util.cargaListaRecomendacionAutor(id);
        return lista ; 
    }
     
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postNuevoCliente(
           @FormParam("idUsuario") String idUsuario,
           @FormParam("idAutor") String idAutor,
           @FormParam("nombre") String nombre,
           @FormParam("nombreAutor") String nombreAutor) throws ClassNotFoundException, ParseException, URISyntaxException {
            
            GustoAutor gusto = new GustoAutor(idUsuario, idAutor, nombreAutor);
            util.registarGustoAutor(gusto);

            String uri = "../autor.html?q="+ idUsuario + "&n=" + nombre;
            java.net.URI location = new java.net.URI(uri);
            return Response.temporaryRedirect(location).build();

    
    }
    
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
}
