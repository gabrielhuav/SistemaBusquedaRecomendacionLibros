package ovh.gabrielhuav.libros.controlador;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.simple.parser.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import paq.Autor;

import paq.UtilDB;
import paq.RecomiendaAutor;

@Controller
@RequestMapping("/busquedaAutor")
public class BusquedaAutorController {

    @Autowired
    private UtilDB util;

    private static String idUsa = "";
    private static Map<String, Autor> listaUsuarios = new ConcurrentHashMap<String, Autor>();

    @GetMapping
    @ResponseBody
    public String getHtml() throws ClassNotFoundException {
        List<Autor> listaRegistrada = util.cargaListaAutor();
        for (Autor cadaUsuario : listaRegistrada) {
            idUsa = String.valueOf(cadaUsuario.getId());
            listaUsuarios.put(idUsa, cadaUsuario);
        }
        return listaUsuarios.toString();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Autor getAutor(@PathVariable("id") String id) {
        final Autor elUsuario = listaUsuarios.get(id);
        if (elUsuario == null) {
            throw new RuntimeException("Autor no encontrado");
        }
        return elUsuario;
    }

    @PostMapping("/buscar")
    public RedirectView postNuevoAutor(@RequestParam("autorabuscar") String autorabuscar, @RequestParam("idUsuarioBuscar") String idUsuario) throws ClassNotFoundException, URISyntaxException, ParseException, UnsupportedEncodingException {
        Object resultado = util.buscarAutor(autorabuscar);
        if (resultado instanceof List<?>) {
            return handleArrayList((List<Autor>) resultado, idUsuario, autorabuscar);
        } else if (resultado instanceof Autor) {
            return handleAutor((Autor) resultado, idUsuario, autorabuscar);
        } else {
            return new RedirectView("/Nombre_Personalizado_De_Mi_Proyecto/error.html", true);
        }
    }

    private RedirectView handleArrayList(List<Autor> listaAutor, String idUsuario, String nombre) throws UnsupportedEncodingException, ClassNotFoundException {
        if (listaAutor.isEmpty()) {
            return new RedirectView("/Nombre_Personalizado_De_Mi_Proyecto/no-encontrado.html", true);
        } else {
            Autor autorNuevo = listaAutor.get(0);
            return processAutor(autorNuevo, idUsuario, nombre);
        }
    }

    private RedirectView handleAutor(Autor autorNuevo, String idUsuario, String nombre) throws UnsupportedEncodingException, ClassNotFoundException {
        return processAutor(autorNuevo, idUsuario, nombre);
    }

    private RedirectView processAutor(Autor autorNuevo, String idUsuario, String nombre) throws UnsupportedEncodingException, ClassNotFoundException {
        util.registrarAutor(autorNuevo);
        List<Autor> listaRegistrada = util.cargaListaAutor();
        for (Autor cadaUsuario : listaRegistrada) {
            String idUsa = String.valueOf(cadaUsuario.getId());
            listaUsuarios.put(idUsa, cadaUsuario);
        }

        String a = URLEncoder.encode(autorNuevo.getId(), StandardCharsets.UTF_8.toString());
        String b = URLEncoder.encode(autorNuevo.getNombre(), StandardCharsets.UTF_8.toString());
        String c = URLEncoder.encode(autorNuevo.getMejorlibro(), StandardCharsets.UTF_8.toString());
        String d = URLEncoder.encode(autorNuevo.getFechaNacimiento(), StandardCharsets.UTF_8.toString());
        String e = URLEncoder.encode(autorNuevo.getFechaFallecimiento(), StandardCharsets.UTF_8.toString());
        String f = URLEncoder.encode(autorNuevo.getBiografia(), StandardCharsets.UTF_8.toString());

        String uri = "/Nombre_Personalizado_De_Mi_Proyecto/autor.html?q=" + URLEncoder.encode(idUsuario, StandardCharsets.UTF_8.toString()) + "&n=" + URLEncoder.encode(nombre, StandardCharsets.UTF_8.toString()) + "&a=" + a + "&b=" + b + "&c=" + c + "&d=" + d + "&e=" + e + "&f=" + f;
        return new RedirectView(uri, true);
    }

    @PostMapping("/Recomienda")
    public ResponseEntity<String> postRecomienda(@RequestParam("idAutor") String idAutor,
                                                @RequestParam("nombreAutor") String nombreAutor,
                                                @RequestParam("nombre") String nombre,
                                                @RequestParam("idUsuario") String idUsuario)
            throws ClassNotFoundException, URISyntaxException, ParseException {
        RecomiendaAutor recomienda = util.RecomiendaPorAutor(idAutor, idUsuario, nombreAutor);
        util.registarRecomendacionAutor(recomienda);

        String a = recomienda.getIdLibro().replaceAll(" ", "+");
        String b = recomienda.getTitulo().replaceAll(" ", "+");
        String c = recomienda.getIdAutor().replaceAll(" ", "+");
        String d = recomienda.getNombreAutor().replaceAll(" ", "+");

        String uri = "/Nombre_Personalizado_De_Mi_Proyecto/RecomendacionAutor.html?q=" + idUsuario + "&n=" + nombre + "&a=" + a + "&b=" + b + "&c=" + c + "&d=" + d;

        // Return a custom HTTP response with a status code and a response body
        return ResponseEntity.ok().body(uri);
    }
}