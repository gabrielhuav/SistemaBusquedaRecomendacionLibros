package ovh.gabrielhuav.libros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import paq.UtilDB;

@Configuration
public class AppConfig {

    @Bean
    public UtilDB utilDB() {
        return new UtilDB();
    }
    
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}