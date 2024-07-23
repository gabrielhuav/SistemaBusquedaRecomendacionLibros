package ovh.gabrielhuav.libros.controlador;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import paq.Libro;

import paq.UtilDB;

@Controller
@RequestMapping("/LibroFavorito")
public class LibroFavoritoController {

    @Autowired
    private UtilDB util;

    @PostMapping
    public ResponseEntity<String> registarLibroFavorito(@RequestParam("idUsuario") String idUsuario,
                                                         @RequestParam("idLibro") String idLibro,
                                                         @RequestParam("titulo") String titulo,
                                                         @RequestParam("idAutor") String idAutor,
                                                         @RequestParam("nombreAutor") String nombreAutor,
                                                         @RequestParam("yearP") String yearP,
                                                         @RequestParam("imagen") String imagen) throws ClassNotFoundException {
        Libro libroFavorito = new Libro(idUsuario, idLibro, titulo, idAutor, nombreAutor, yearP, imagen);
        util.registarLibroFavorito(libroFavorito);
        return ResponseEntity.ok("Libro favorito registrado con éxito mediante el LibroFavoritoController.java");
    }

    @GetMapping("/favoritos")
    public ResponseEntity<List<Libro>> obtenerLibrosFavoritos(@RequestParam("idUsuario") int idUsuario) throws ClassNotFoundException {
        List<Libro> librosFavoritos = util.cargaListaLibrosFavoritos(idUsuario);
        return ResponseEntity.ok(librosFavoritos);
    }
}
