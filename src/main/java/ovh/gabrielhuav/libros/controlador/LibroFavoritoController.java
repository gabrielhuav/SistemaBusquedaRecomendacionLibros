package ovh.gabrielhuav.libros.controlador;

import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import paq.UtilDB;
import paq.Libro;

@Controller
public class LibroFavoritoController {

    @Autowired
    private UtilDB util;

    @GetMapping
    @ResponseBody
    public String getHtml() {
        return "hola";
    }

    @GetMapping("/lista/{id}")
    @ResponseBody
    public List<Libro> getlistaRecomendacion(@PathVariable("id") int id) throws ClassNotFoundException {
        List<Libro> lista = util.cargaListaLibrosFavoritos(id);
        return lista;
    }

    @PostMapping
    public RedirectView postNuevoCliente(
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

    @PutMapping
    public void putHtml(@RequestBody String content) {
    }
}
