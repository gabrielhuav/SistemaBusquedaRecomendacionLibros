package ovh.gabrielhuav.controlador;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ovh.gabrielhuav.modelo.SerieCollatz;

@RestController //Se puede probar con http://localhost:8080/api/secuence/collatz/5
@RequestMapping("/api/secuence")
public class EndPointController {
    private final SerieCollatz serieCollatz;

    @Autowired
    public EndPointController(SerieCollatz serieCollatz) {
        this.serieCollatz = serieCollatz;
    }

    @PutMapping("/collatz/{input}")
    @ResponseBody
    public Map<String, String> putCollatz(@PathVariable("input") Integer input) {
        return serieCollatz.collatzSequence(input);
    }

    @GetMapping("/collatz/{input}")
    @ResponseBody
    public Map<String, String> getCollatz(@PathVariable("input") Integer input) {
        return serieCollatz.collatzSequence(input);
    }

    @GetMapping("/collatz")
    @ResponseBody
    public Map<String, String> getDefaultCollatz() {
        return serieCollatz.collatzSequence(20); //valor por defecto
    }
}