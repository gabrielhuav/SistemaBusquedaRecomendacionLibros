/* Giovanni Olmos Salmones - 2172002785
 * Gabriel Hurtado Aviles - 2172000781
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientes;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ClienteLibros {
    public static void main(String[] args) throws ParseException {
        
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://openlibrary.org/");
        //WebTarget resourceWebTarget = target.path("/books/OL1M.json");
        //WebTarget resourceWebTarget = target.path("/authors/OL23919A.json");
        WebTarget resourceWebTarget = target.path("search/authors.json?q=j k rowling");
        
        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        System.out.println(response.getStatus());
        
        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(response.readEntity(String.class));
        
        System.out.println("Resultado " + responseJSON.toJSONString());
        
        Object numFound = (Object) responseJSON.get("numFound");
        System.out.println("numFound " + numFound.toString());
        
        
        /*Object nombre = (Object) responseJSON.get("name");
        System.out.println("Nombre " + nombre.toString());
        
        /*Object fechaFin = (Object) responseJSON.get("death_date");
        System.out.println("Año muerte " + fechaFin.toString());
        
        JSONArray nombresAlt = (JSONArray) responseJSON.get("alternate_names");
        for (int i = 0; i < nombresAlt.size(); i++) {
            Object otrosNombre = (Object) nombresAlt.get(i);
            System.out.println("Nombre " + otrosNombre.toString());
        }
        
        Object bio = (Object) responseJSON.get("bio");
        System.out.println("Biografia " + bio.toString());
        
        /*JSONObject bio = (JSONObject) responseJSON.get("bio");
        System.out.println("Bio " + bio.toString());
        
        Object value = (Object) bio.get("value");
        System.out.println("Biography " + value.toString());*/
    }
}
