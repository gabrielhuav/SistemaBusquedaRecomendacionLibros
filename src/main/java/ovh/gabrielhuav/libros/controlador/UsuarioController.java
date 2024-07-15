package ovh.gabrielhuav.libros.controlador;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import paq.Usuario;
import paq.UtilDB;

@RestController
public class UsuarioController {

    private static final Map<String, Usuario> listaUsuarios = new ConcurrentHashMap<>();
    private UtilDB util = new UtilDB();

    @GetMapping("/usuario")
    public String getHtml() throws ClassNotFoundException {
        List<Usuario> listaRegistrada = util.cargaListaUsuarios();
        for (Usuario cadaUsuario : listaRegistrada) {
            String idUsa = String.valueOf(cadaUsuario.getId());
            listaUsuarios.put(idUsa, cadaUsuario);
        }
        return listaUsuarios.toString();
    }

    @GetMapping("/usuario/{id}")
    public Usuario getHtml(@PathVariable String id) {
        final Usuario elUsuario = listaUsuarios.get(id);
        if (elUsuario == null) {
            throw new RuntimeException("Usuario not found");
        }
        return elUsuario;
    }

    @PostMapping("/usuario")
    public RedirectView postNuevoCliente(@RequestParam("nombre") String nombre,
                                         @RequestParam("apellidoP") String apellidoP,
                                         @RequestParam("apellidoM") String apellidoM,
                                         @RequestParam("edad") String edadStr,
                                         @RequestParam("genero") String genero,
                                         @RequestParam("correo") String correo,
                                         @RequestParam("userU") String userU,
                                         @RequestParam("passwordU") String passwordU)
            throws ClassNotFoundException, URISyntaxException, NoSuchAlgorithmException {

        int edad = 0;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            // Handle the case where edadStr is not a valid integer
            System.out.println("Invalid edad value: " + edadStr);
            return new RedirectView("../register.html", true); // Redirect back to register page
        }

        Usuario usuarioNuevo = new Usuario(nombre, apellidoP, apellidoM, edad, genero, correo, userU, passwordU);
        util.registraUsuario(usuarioNuevo);

        System.out.println("Cliente nuevo creado " + usuarioNuevo.toString());

        // Redirect to the login page after creating a new user
        RedirectView redirectView = new RedirectView("/Nombre_Personalizado_De_Mi_Proyecto/login.html", true);
        return redirectView;
    }

    @PostMapping("/login")
    public void postLogin(@RequestParam("txtPass") String password,
                          @RequestParam("txtUsu") String usuario,
                          HttpServletResponse response) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, SQLException {
        // ...
        String resultado = util.validarLoginUsuario(usuario, password);
        Usuario miUsuario = util.encontrarDatosUsuario(usuario);

        if (resultado.equalsIgnoreCase("Encontrado") && miUsuario != null) {
            String nombre = miUsuario.getNombre().replaceAll("\\s+", "");
            String id = String.valueOf(miUsuario.getId());

            response.sendRedirect("/Nombre_Personalizado_De_Mi_Proyecto/menu.html?q=" + id + "&n=" + nombre);
        } else {
            response.sendRedirect("/Nombre_Personalizado_De_Mi_Proyecto/login.html");
        }
    }

    @PutMapping("/usuario/{id}")
    public void putHtml(@PathVariable String id, @RequestParam String content) {
        // You can implement PUT request handling here if needed
    }
}