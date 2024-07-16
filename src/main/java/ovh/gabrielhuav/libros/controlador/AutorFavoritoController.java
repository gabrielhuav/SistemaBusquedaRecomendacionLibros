package ovh.gabrielhuav.libros.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import paq.UtilDB;
import paq.GustoAutor;

@Controller
@RequestMapping("/AutorFavorito")
public class AutorFavoritoController {

    @Autowired
    private UtilDB util;

    @PostMapping
    public ResponseEntity<String> registarAutorFavorito(GustoAutor autorFavorito) {
        try {
            util.registarGustoAutor(autorFavorito);
            return ResponseEntity.ok("Autor favorito registrado con éxito");
        } catch (ClassNotFoundException e) {
            return ResponseEntity.badRequest().body("Error registering autor favorito: " + e.getMessage());
        }
    }
}