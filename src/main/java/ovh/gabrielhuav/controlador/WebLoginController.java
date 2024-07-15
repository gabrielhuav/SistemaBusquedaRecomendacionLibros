package ovh.gabrielhuav.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author gabrielhuav
 */
@Controller
@RequestMapping("/Nombre_Personalizado_De_Mi_Proyecto")
public class WebLoginController {
    
    @GetMapping("/login")
    public String login() {
        return "login"; // nombre de la plantilla HTML
    }
}