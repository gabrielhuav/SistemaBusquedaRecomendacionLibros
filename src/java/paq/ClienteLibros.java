/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Array;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import paq.Autor;
import paq.RecomiendaAutor;
import paq.RecomiendaTema;
import paq.UtilDB;

public class ClienteLibros {
    public static void main(String[] args) throws ParseException, ClassNotFoundException, NoSuchAlgorithmException {
        
        Client client = ClientBuilder.newClient();
        String clave = "fantasy" ;
        String bautor = "https://openlibrary.org/subjects/" + clave + ".json?limit=100";   
        WebTarget resourceWebTarget = client.target(bautor);
        
        
        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        System.out.println(response.getStatus());
        
        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(response.readEntity(String.class));
        
        System.out.println("Resultado " + responseJSON.toJSONString());
        
        Object encontrados = (Object) responseJSON.get("work_count");
        System.out.println("work_count " + encontrados.toString());
        //////////////////////////////////////////////////////////
        
        int Random = (int)(Math.random()*100);  //Numero Aleatorio
        
        ///////////////////////////////////////////////////////////
        //Arreglo de libros
        JSONArray busqueda = (JSONArray) responseJSON.get("works");
        //Selecciono un arreglo de libros
        JSONObject libro = (JSONObject) busqueda.get(Random);
        
        Object key = (Object) libro.get("key");
        Object titulo = (Object) libro.get("title");
        
        JSONArray autores = (JSONArray) libro.get("authors");
        JSONObject autor = (JSONObject) autores.get(0);
        
        Object keyAutor = (Object) autor.get("key");
        Object nameAutor = (Object) autor.get("name");
        Object imagen = (Object) libro.get("cover_id");
         
        String keyS = "" ;
        String tituloS = "" ;
        String keyAutorS = "" ;
        String nameAutorS = "" ;
        String imagenS = "" ;
        
        
        if (libro.containsKey("key")){              
            keyS = key.toString();
        }else {
            keyS = "null";
        }
        
        if (libro.containsKey("title")){
            tituloS = titulo.toString();
        }else {
            tituloS = "null";
        }
        
        if (autor.containsKey("key")){              
            keyAutorS = keyAutor.toString();
        }else {
            keyAutorS = "null";
        }
        
        if (autor.containsKey("name")){
            nameAutorS = nameAutor.toString();
        }else {
            nameAutorS = "null";
        }
        
        if (libro.containsKey("cover_id")){
            //imagenS = String.valueOf(imagen.toString());
            imagenS = imagen.toString();
        }else {
            imagenS = "null";
        }
        
        //RecomiendaTema recomendacion = new  RecomiendaTema(idUsuario,keyS,tituloS,keyAutorS,nameAutorS,tema);
        
        //System.out.println("Id usuario: " + idUsuario);
        System.out.println("Id Libro: " + keyS);
        System.out.println("Titulo del libro: " + tituloS);
        System.out.println("Id autor: " + keyAutorS);
        System.out.println("Nombre Autor: " + nameAutorS);
        System.out.println("cover_id: " + imagenS);
        
       //return recomendacion;

    }  
}

