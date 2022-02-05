/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq;

import java.net.URISyntaxException;
import java.util.List;
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
@Path("LibroFavorito")
public class LibroFavoritoResource {

    @Context
    private UriInfo context;
    
    UtilDB util = new UtilDB();

    
    public LibroFavoritoResource() {
    }

    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        //TODO return proper representation object
        return "hola";
    }
    
    @GET
    @Path("lista/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Libro> getlistaRecomendacion(@PathParam("id") int id) throws ClassNotFoundException { 
        List<Libro> lista = util.cargaListaLibrosFavoritos(id);
        return lista ; 
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postNuevoCliente(
           @FormParam("idUsuario") String idUsuario,
           @FormParam("Titulo") String titulo,
           @FormParam("Autor") String nombreAutor,
           @FormParam("ClaveLibro") String idLibro,
           @FormParam("ClaveAutor") String idAutor,
           @FormParam("imagen") String imagen,
           @FormParam("nombre") String nombre,
           @FormParam("Primerapublicacion") String year ) throws ClassNotFoundException, ParseException, URISyntaxException {
            
            Libro libro = new Libro(idUsuario,idLibro,titulo,idAutor,nombreAutor,year,imagen);
            util.registarLibroFavorito(libro);

        /*return Response.status(200)
			.entity("RECIBI: " + titulo + " " + nombreAutor + " " + idLibro + " " + idAutor + " " + year + " ID: " + idUsuario + "Imagen "+ imagen + " Mi nombre" + nombre)
			.build();*/
        
        String uri = "../busquedaLibros.html?q="+ idUsuario + "&n=" + nombre;
        java.net.URI location = new java.net.URI(uri);
        return Response.temporaryRedirect(location).build();
    }

    
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
}
