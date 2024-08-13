package ovh.gabrielhuav.libros.controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import paq.Libro;
import paq.UtilDB;

/**
 *
 * @author gabri
 */
@RestController
@RequestMapping("/api/libros")
public class LibroController {
    
    @Autowired
    private LibroService libroService;
    
    @Autowired
    private UtilDB util;
    
    @GetMapping("/favoritos/{idUsuario}")
    public List<Libro> getLibrosFavoritos(@PathVariable("idUsuario") int idUsuario) {
        return libroService.getLibrosFavoritos(idUsuario);
    }
    
    @PostMapping("/buscarPorTitulo")
    public List<Libro> buscarPorTitulo(@RequestParam("titulo") String titulo) {
        try {
            return libroService.buscarPorTitulo(titulo);
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON: " + e);
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e);
            return new ArrayList<>();
        }
    }
    
    @PostMapping("/buscarPorAutor")
    public List<Libro> buscarPorAutor(@RequestParam("autor") String autor) throws JsonProcessingException {
        return libroService.buscarPorAutor(autor);
    }
    
    @PostMapping("/buscarPorPalabra")
    public List<Libro> buscarPorPalabra(@RequestParam("palabra") String palabra) throws JsonProcessingException {
        return libroService.buscarPorPalabra(palabra);
    }
    
    @GetMapping("/lista/{id}")
    @ResponseBody
    public List<Libro> getlistaRecomendacion(@PathVariable("id") int id) throws ClassNotFoundException {
        List<Libro> lista = util.cargaListaLibrosFavoritos(id);
        return lista;
    }
    
    @PostMapping("/nuevoFavorito")
    public RedirectView postNuevoFavorito(
            @RequestParam("idUsuario") String idUsuario,
            @RequestParam("Titulo") String titulo,
            @RequestParam("Autor") String nombreAutor,
            @RequestParam("ClaveLibro") String idLibro,
            @RequestParam("ClaveAutor") String idAutor,
            @RequestParam("imagen") String imagen,
            @RequestParam("nombre") String nombre,
            @RequestParam("Primerapublicacion") String year) throws ClassNotFoundException, URISyntaxException {

        Libro libro = new Libro(idUsuario, idLibro, titulo, idAutor, nombreAutor, year, imagen);
        util.registarLibroFavorito(libro);

        String uri = "/Nombre_Personalizado_De_Mi_Proyecto/busquedaLibros.html?q=" + idUsuario + "&n=" + nombre;
        return new RedirectView(uri, true);
    }
}