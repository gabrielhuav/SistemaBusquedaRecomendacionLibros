package ovh.gabrielhuav.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ovh.gabrielhuav.modelo.SerieCollatz;

@RestController
@RequestMapping("/collatz")
public class CollatzController {
    private final SerieCollatz serieCollatz;

    @Autowired
    public CollatzController(SerieCollatz serieCollatz) {
        this.serieCollatz = serieCollatz;
    }

    @GetMapping("/{n}") //Se puede probar con http://localhost:8080/collatz/19
    public String getCollatz(@PathVariable("n") int n) {
        return serieCollatz.collatz(n);
    }

    @GetMapping //Se puede probar con http://localhost:8080/collatz?n=19
    public String collatz(@RequestParam(value = "n", defaultValue = "19") int n) {
        return serieCollatz.collatz(n);
    }
    
    
}