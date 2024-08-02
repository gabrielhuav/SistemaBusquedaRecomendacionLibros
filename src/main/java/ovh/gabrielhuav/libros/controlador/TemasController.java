package ovh.gabrielhuav.libros.controlador;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;

import paq.GustoTemas;
import paq.UtilDB;
import paq.RecomiendaTema;
import paq.Temas;
import paq.Usuario;

@Controller
@RequestMapping("/Temas")
public class TemasController {
    
    @Autowired
    private UtilDB utilDB;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Temas> getListaTemas() throws ClassNotFoundException {
        List<Temas> listatemas = utilDB.cargaListaTemas();
        return listatemas;
    }
    
     @GetMapping("/getClaveFromTraduccion")
    public @ResponseBody String getClaveFromTraduccion(@RequestParam("traduccion") String traduccion) throws ClassNotFoundException {
        List<Temas> temasList = getListaTemas();
        for (Temas tema : temasList) {
            if (tema.getTraduccion().equals(traduccion)) {
                return tema.getClave();
            }
        }
        return null; // or throw an exception if not found
    }
    
    @PostMapping
    public ResponseEntity<String> agregarTema(@RequestParam("idUsuario") String idUsuario, 
                                             @RequestParam("idTema") String idTema, 
                                             @RequestParam("tema") String tema) throws ClassNotFoundException {
        // Agregar tema a la base de datos
        utilDB.registrarTemaFavoritoUsuario(idUsuario, idTema, tema);
        return ResponseEntity.ok("Tema agregado con éxito mediante el TemasController.java");
    }
    
    @GetMapping("/registraGusto")
    public String getRecomendacionHtml() {
        return "recomendacion";
    }
    
    @PostMapping("/registraGusto")
    public ResponseEntity<String> recomendar(@RequestParam("idUsuario") String idUsuario, 
                                             @RequestParam("tema") String tema, 
                                             @RequestParam("claveTema") String claveTema) throws ParseException, ClassNotFoundException {
        // Recomendar libro based on tema and user preferences
        RecomiendaTema recomienda = utilDB.RecomiendaPorTema(claveTema, idUsuario, tema);
        utilDB.registrarRecomendacionTema(recomienda);
        return ResponseEntity.ok("Recomendación realizada con éxito");
    }
    
    @GetMapping("/historial/{idUsuario}")
    public ResponseEntity<String> getHistorial(@PathVariable("idUsuario") String idUsuario) throws ClassNotFoundException {
        List<GustoTemas> historial = utilDB.cargaListaGustoTemas(idUsuario);
        Gson gson = new Gson(); // create an instance of Gson
        return ResponseEntity.ok(gson.toJson(historial));
    }
}