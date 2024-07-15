package ovh.gabrielhuav.controlador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ovh.gabrielhuav.modelo", "ovh.gabrielhuav.controlador", "ovh.gabrielhuav.libros.controlador"})
public class SecuenciasV3Application {

    public static void main(String[] args) {
        SpringApplication.run(SecuenciasV3Application.class, args);
    }
}