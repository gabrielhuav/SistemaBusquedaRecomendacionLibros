package ovh.gabrielhuav.libros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import paq.UtilDB;

@Configuration
public class AppConfig {

    @Bean
    public UtilDB utilDB() {
        return new UtilDB();
    }
}