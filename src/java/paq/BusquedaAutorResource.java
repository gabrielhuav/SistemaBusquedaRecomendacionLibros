/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq;

import java.net.URI;
import java.net.URISyntaxException;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author xido_ 
 */
@Path("busquedaAutor")

public class BusquedaAutorResource extends HttpServlet {

@Context
    private UriInfo context;
    
    // Manajo de identificadores de los usuarios.
    private static String idUsa = "";
    private static Map<String, Autor> listaUsuarios = new ConcurrentHashMap <String, Autor>();
    UtilDB util = new UtilDB();

    public BusquedaAutorResource() {
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() throws ClassNotFoundException  { 
        List<Autor> listaRegistrada = util.cargaListaAutor();
        for(Autor cadaUsuario: listaRegistrada){
            idUsa = String.valueOf(cadaUsuario.getId());
            listaUsuarios.put(idUsa, cadaUsuario);
        }
        return listaUsuarios.toString();   
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAutor(@PathParam("id") String id){
        final Autor elUsuario = listaUsuarios.get(id);
        System.out.println(elUsuario.toString());
        if (elUsuario == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(elUsuario).build();
    }
    
    @POST
    @Path("/buscar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postNuevoAutor(
           @FormParam("autorabuscar") String autorabuscar,
           @FormParam("nombre") String nombre,
           @FormParam("idUsuarioBuscar") String idUsuario) throws ClassNotFoundException, ParseException, URISyntaxException {
        Autor autorNuevo = util.buscarAutor(autorabuscar);
        util.registarAutor(autorNuevo);
        List<Autor> listaRegistrada = util.cargaListaAutor();
        for(Autor cadaUsuario: listaRegistrada){
            idUsa = String.valueOf(cadaUsuario.getId());
            listaUsuarios.put(idUsa, cadaUsuario);
        }     

        String a = autorNuevo.getId().replaceAll(" ", "+");
        String b = autorNuevo.getNombre().replaceAll(" ", "+");
        String c = autorNuevo.getMejorlibro().replaceAll(" ", "+");
        String d = autorNuevo.getFechaNacimiento().replaceAll(" ", "+");
        String e = autorNuevo.getFechaFallecimiento().replaceAll(" ", "+");
        String f = autorNuevo.getBiografia().replaceAll(" ", "+");
        
        String uri = "../autor.html?q="+ idUsuario + "&n=" + nombre +"&a=" + a + "&b=" + b + "&c=" + c + "&d=" + d + "&e=" + e + "&f=" + f;
        java.net.URI location = new java.net.URI(uri);
        return Response.temporaryRedirect(location).build();
    }
    
    @POST
    @Path("/Recomienda")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postRecomienda(
           @FormParam("idAutor") String idAutor,
           @FormParam("nombreAutor") String nombreAutor,
           @FormParam("nombre") String nombre,
           @FormParam("idUsuario") String idUsuario ) throws ClassNotFoundException, ParseException, URISyntaxException {
        
           RecomiendaAutor recomienda = util.RecomiendaPorAutor(idAutor, idUsuario, nombreAutor);
           util.registarRecomendacionAutor(recomienda);
           
            String a = recomienda.getIdLibro().replaceAll(" ", "+");
            String b = recomienda.getTitulo().replaceAll(" ", "+");
            String c = recomienda.getIdAutor().replaceAll(" ", "+");
            String d = recomienda.getNombreAutor().replaceAll(" ", "+");   
        
        String uri = "../RecomendacionAutor.html?q="+ idUsuario + "&n=" + nombre + "&a=" + a + "&b=" + b + "&c=" + c + "&d=" + d ;
        java.net.URI location = new java.net.URI(uri);
        return Response.temporaryRedirect(location).build();
    }
    
    
    /*@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response postNuevoCliente(
           @FormParam("autorabuscar") String autorabuscar ) throws ClassNotFoundException, ParseException {
        Autor autorNuevo = util.buscarAutor(autorabuscar);
        util.registarAutor(autorNuevo);
        autorNuevo.getId();
        
        System.out.println("Cliente nuevo creado " + autorNuevo.toString());
        return Response.created(URI.create("/autor/" + autorNuevo.getId())).build();
    }*/
    
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
}
