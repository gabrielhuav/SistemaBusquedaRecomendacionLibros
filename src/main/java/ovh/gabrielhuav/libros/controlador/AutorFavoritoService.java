package ovh.gabrielhuav.libros.controlador;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paq.RecomiendaAutor;
import paq.UtilDB;

@Service
public class AutorFavoritoService {
    
    @Autowired
    private UtilDB utilDB;
    
    public List<RecomiendaAutor> getHistorial(int idUsuario) {
        List<RecomiendaAutor> historial = new ArrayList<>();
        try {
            historial = utilDB.cargaListaRecomendacionAutor(idUsuario);
        } catch (ClassNotFoundException e) {
            // Manejar la excepción
        }
        return historial;
    }
}