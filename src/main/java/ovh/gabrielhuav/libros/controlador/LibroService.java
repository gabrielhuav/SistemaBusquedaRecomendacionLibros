/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ovh.gabrielhuav.libros.controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static java.lang.Math.log;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import paq.Libro;
import paq.UtilDB;

/**
 *
 * @author gabri
 */
@Service
public class LibroService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    public List<Libro> buscarPorTitulo(String titulo) throws JsonProcessingException {
        String url = "http://openlibrary.org/search.json?title=" + titulo;
        return buscarLibros(url);
    }
    
    public List<Libro> buscarPorAutor(String autor) throws JsonProcessingException {
        String url = "http://openlibrary.org/search.json?author=" + autor;
        return buscarLibros(url);
    }
    
    public List<Libro> buscarPorPalabra(String palabra) throws JsonProcessingException {
        String url = "http://openlibrary.org/search.json?q=" + palabra;
        return buscarLibros(url);
    }

    private List<Libro> buscarLibros(String url) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            List<Libro> libros = new ArrayList<>();
            for (JsonNode node : root.get("docs")) {
                Libro libro = new Libro();
                libro.setTitulo(getJsonNodeText(node, "title"));
                libro.setNombreAutor(getJsonNodeText(node, "author_name", 0)); // Ajuste aquí
                libro.setIdLibro(getJsonNodeText(node, "key")); // Ajuste aquí
                libro.setIdAutor(getJsonNodeText(node, "author_key", 0)); // Ajuste aquí
                libro.setYearP(getJsonNodeText(node, "first_publish_year"));
                libro.setImagen(getJsonNodeText(node, "cover_i"));
                libros.add(libro);
            }
            return libros;
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON: " + e);
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e);
            return new ArrayList<>();
        }
    }

    private String getJsonNodeText(JsonNode node, String fieldName) {
        JsonNode fieldNode = node.get(fieldName);
        return fieldNode != null ? fieldNode.asText() : "";
    }

    private String getJsonNodeText(JsonNode node, String fieldName, int index) {
        JsonNode fieldNode = node.get(fieldName);
        if (fieldNode != null && fieldNode.isArray() && fieldNode.size() > index) {
            return fieldNode.get(index).asText();
        }
        return "";
    }

    
    public List<Libro> getLibrosFavoritos(int idUsuario) {
        List<Libro> librosFavoritos = new ArrayList<>();
        try {
            UtilDB utilDB = new UtilDB();
            librosFavoritos = utilDB.cargaListaLibrosFavoritos(idUsuario);
        } catch (ClassNotFoundException e) {
            // Manejar la excepción
        }
        return librosFavoritos;
    }

}
