package ovh.gabrielhuav.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ovh.gabrielhuav.modelo.SerieFizzBuzz;

@RestController
@RequestMapping("/fizzbuzz")
public class FizzBuzzController {
    private final SerieFizzBuzz serieFizzBuzz;

    @Autowired
    public FizzBuzzController(SerieFizzBuzz serieFizzBuzz) {
        this.serieFizzBuzz = serieFizzBuzz;
    }
    
    @GetMapping("/{n}") //Se puede probar con http://localhost:8080/fizzbuzz/15
    public String getFizzBuzz(@PathVariable("n") int n) {
        return serieFizzBuzz.fizzbuzz(n);
    }
    
    @GetMapping //Se puede probar con http://localhost:8080/fizzbuzz?n=15
    public String fizzbuzz(@RequestParam(value = "n", defaultValue = "15") int n) {
        return serieFizzBuzz.fizzbuzz(n);
    }
}