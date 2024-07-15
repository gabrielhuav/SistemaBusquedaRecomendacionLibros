package ovh.gabrielhuav.libros.controlador;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import paq.GustoAutor;
import paq.RecomiendaAutor;

@Controller
public class AutorFavoritoController {

    @Autowired
    private UtilDB util;

    // Manajo de identificadores de los usuarios.
    private static String idUsa = "";
    private static final Map<String, GustoAutor> listaUsuarios = new ConcurrentHashMap<String, GustoAutor>();

    @GetMapping("/AutorFavorito/{id}")
    @ResponseBody
    public List<GustoAutor> getHtml(@PathVariable("id") int id) throws ClassNotFoundException {
        List<GustoAutor> lista = util.cargaListaGustoAutor(id);
        return lista;
    }

    @GetMapping("/AutorFavorito/lista/{id}")
    @ResponseBody
    public List<RecomiendaAutor> getlistaRecomendacion(@PathVariable("id") int id) throws ClassNotFoundException {
        List<RecomiendaAutor> lista = util.cargaListaRecomendacionAutor(id);
        return lista;
    }

    @PostMapping("/AutorFavorito")
    public RedirectView postNuevoCliente(@RequestParam("idUsuario") String idUsuario, @RequestParam("idAutor") String idAutor, @RequestParam("nombre") String nombre, @RequestParam("nombreAutor") String nombreAutor) throws ClassNotFoundException, URISyntaxException {
        GustoAutor gusto = new GustoAutor(idUsuario, idAutor, nombreAutor);
        util.registarGustoAutor(gusto);

        String uri = "/Nombre_Personalizado_De_Mi_Proyecto/autor.html?q=" + idUsuario + "&n=" + nombre;
        return new RedirectView(uri, true);
    }

    @GetMapping("/Nombre_Personalizado_De_Mi_Proyecto/webresources/busquedaAutor/buscar")
    public String buscarAutor(@RequestParam("idAutor") String idAutor) {
        // Lógica para buscar autor
        return "resultado_busqueda"; // Devuelve una vista o un mensaje de resultado
    }

    @PutMapping("/AutorFavorito")
    public void putHtml(@RequestBody String content) {
    }
}