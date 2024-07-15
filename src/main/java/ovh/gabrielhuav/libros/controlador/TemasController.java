package ovh.gabrielhuav.libros.controlador;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import paq.UtilDB;
import paq.GustoTemas;
import paq.RecomiendaTema;
import paq.Temas;

@RestController
@RequestMapping("/Temas")
public class TemasController {

    @Autowired
    private UtilDB util;

    //Recuperamos lista en JSON de Temas.
    @GetMapping
    public ResponseEntity<List<Temas>> getListaTemas() {
        try {
            List<Temas> listatemas = util.cargaListaTemas();
            return ResponseEntity.ok(listatemas);
        } catch (ClassNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Regres la listas de los temas preferidos de un usuario
    @GetMapping("/{id}")
    public ResponseEntity<List<GustoTemas>> getHtml(@PathVariable("id") int id) {
        try {
            List<GustoTemas> lista = util.cargaListaGustoTemas(id);
            return ResponseEntity.ok(lista);
        } catch (ClassNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<List<RecomiendaTema>> getlistaRecomendacion(@PathVariable("id") int id) {
        try {
            List<RecomiendaTema> lista = util.cargaListaRecomendacionTema(id);
            return ResponseEntity.ok(lista);
        } catch (ClassNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Metodo para registarar preferencia de tema
    @PostMapping
    public ResponseEntity<Void> postNuevoCliente(@RequestParam("tema") String tema, @RequestParam("idUsuario") String idUsuario, @RequestParam("idTema") String clave) {
        try {
            util.temasFavoritos(idUsuario, clave, tema);
            String uri = "/Nombre_Personalizado_De_Mi_Proyecto/PreferenciaTema.html?q=" + idUsuario;
            URI location = new URI(uri);
            return ResponseEntity.status(HttpStatus.FOUND).location(location).build();
        } catch (ClassNotFoundException | URISyntaxException  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/registraGusto")
    public ResponseEntity<Void> postLogin(@RequestParam("claveTema") String clave, @RequestParam("idUsuario1") String idUsuario, @RequestParam("temaUsuario") String tema) {
        try {
            RecomiendaTema recomienda = util.RecomiendaPorTema(clave, tema, idUsuario);
            util.registarRecomendacionTema(recomienda);
            String a = recomienda.getTitulo().replaceAll(" ", "+");
            String b = recomienda.getNombreAutor().replaceAll(" ", "+");
            String c = recomienda.getIdLibro().replaceAll(" ", "+");
            String d = recomienda.getIdAutor().replaceAll(" ", "+");
            String e = tema.replaceAll(" ", "+");
            String uri = "/Nombre_Personalizado_De_Mi_Proyecto/PreferenciaTema.html?q=" + idUsuario + "&a=" + a + "&b=" + b + "&c=" + c + "&d=" + d + "&e=" + e;
            URI location = new URI(uri);
            return ResponseEntity.status(HttpStatus.FOUND).location(location).build();
        } catch (ClassNotFoundException | URISyntaxException | ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> putHtml(@RequestBody String content) {
        return ResponseEntity.ok().build();
    }
}