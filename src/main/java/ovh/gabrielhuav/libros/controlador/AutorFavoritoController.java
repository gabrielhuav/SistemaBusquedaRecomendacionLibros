package ovh.gabrielhuav.libros.controlador;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import paq.GustoAutor;
import paq.RecomiendaAutor;
import paq.UtilDB;

@Controller
@RequestMapping("/AutorFavorito")
public class AutorFavoritoController {

    @Autowired
    private UtilDB util;
    
    @Autowired
    private AutorFavoritoService autorFavoritoService;

    @GetMapping("/{id}")
    public ResponseEntity<List<GustoAutor>> getHtml(@PathVariable int id) throws ClassNotFoundException {
        List<GustoAutor> lista = util.cargaListaGustoAutor(id);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<List<RecomiendaAutor>> getlistaRecomendacion(@PathVariable int id) throws ClassNotFoundException {
        List<RecomiendaAutor> lista = util.cargaListaRecomendacionAutor(id);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> registarAutorFavorito(@RequestParam("idUsuario") String idUsuario, 
                                                       @RequestParam("idAutor") String idAutor, 
                                                       @RequestParam("nombre") String nombre, 
                                                       @RequestParam("nombreAutor") String nombreAutor) throws ClassNotFoundException {
        GustoAutor gusto = new GustoAutor(idUsuario, idAutor, nombreAutor);
        util.registarGustoAutor(gusto);
        return ResponseEntity.ok("Autor favorito registrado con éxito mediante el AutorFavoritoController.java");
    }
    
    @GetMapping("/historial/{idUsuario}")
    @ResponseBody
    public List<RecomiendaAutor> getHistorial(@PathVariable int idUsuario) {
        return autorFavoritoService.getHistorial(idUsuario);
    }

    @PutMapping
    public ResponseEntity<String> putHtml(@RequestBody String content) {
        // Implementar lógica para actualizar el contenido
        return ResponseEntity.ok("Contenido actualizado con éxito");
    }
}