 package paq;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class UtilDB{
    
    public UtilDB() {
        
    }
    
    public List<Usuario> cargaListaUsuarios() throws ClassNotFoundException{
        List<Usuario> lista = new ArrayList<Usuario>(); 
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM Usuario;";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    int id = rs.getInt("idUsuario");
                    String nombre = rs.getString("nombre");  
                    String apellidoP = rs.getString("apellidoP"); 
                    String apellidoM = rs.getString("apellidoM"); 
                    int edad = rs.getInt("edad");
                    String genero = rs.getString("genero");
                    String correo = rs.getString("correo");
                    String userU = rs.getString("userU");
                    String passwordU = rs.getString("passwordU");       
                    
                    Usuario usuario = new Usuario(id , nombre, apellidoP, apellidoM, edad, genero, correo, userU, passwordU);
                    lista.add(usuario);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List <Autor> cargaListaAutor() throws ClassNotFoundException{
        List<Autor> lista = new ArrayList<Autor>(); 
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM autor;";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("id");  
                    String nombre = rs.getString("nombre");  
                    String mejorlibro = rs.getString("mejorlibro");  
                    String fechaNacimiento = rs.getString("fechaNacimiento");  
                    String fechaFallecimiento = rs.getString("fechaFallecimiento");  
                    String biografia = rs.getString("biografia");                     
                    
                    Autor autornew = new Autor (id , nombre, mejorlibro, fechaNacimiento, fechaFallecimiento, biografia);
                    
                    lista.add(autornew);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List<GustoAutor> cargaListaGustoAutor(int idBuscar) throws ClassNotFoundException{
        List<GustoAutor> lista = new ArrayList<GustoAutor>(); 
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM gustoautor WHERE idUsuario = '" + idBuscar +"';";
                //String query = "SELECT * FROM gustoautor WHERE idUsuario = '2';";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("idUsuario");  
                    String idUsuario = rs.getString("idUsuario");  
                    String idAutor = rs.getString("idAutor");  
                    String nombreAutor = rs.getString("nombreAutor");
                             
                    
                    GustoAutor gustoAutor = new GustoAutor (id , idUsuario, idAutor, nombreAutor);
                    
                    lista.add(gustoAutor);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List<GustoTemas> cargaListaGustoTemas(String idBuscar) throws ClassNotFoundException{
        List<GustoTemas> lista = new ArrayList<GustoTemas>(); 
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM gustotemas WHERE idUsuario = '" + idBuscar +"';";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("idUsuario");  
                    String idUsuario = rs.getString("idUsuario");  
                    String idtema = rs.getString("idtema"); 
                    String tema = rs.getString("tema");  

                    GustoTemas Gustotema = new GustoTemas (id ,idUsuario,idtema,tema);
                    
                    lista.add(Gustotema);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List<Temas> cargaListaTemas() throws ClassNotFoundException{
        List<Temas> lista = new ArrayList<Temas>(); 
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM temas ;";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("id");  
                    String clave = rs.getString("clave");  
                    String traduccion = rs.getString("traduccion");  

                    Temas tema = new Temas (id , clave, traduccion );
                    
                    lista.add(tema);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List<RecomiendaAutor> cargaListaRecomendacionAutor(int idBuscar) throws ClassNotFoundException{
        List<RecomiendaAutor> lista = new ArrayList<RecomiendaAutor>(); 
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM recomendacionautor WHERE idUsuario = '" + idBuscar +"';";
                //String query = "SELECT * FROM gustoautor WHERE idUsuario = '2';";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("idUsuario");  
                    String idUsuario = rs.getString("idUsuario");
                    String idLibro = rs.getString("idLibro");
                    String titulo = rs.getString("titulo");               
                    String idAutor = rs.getString("idAutor"); 
                    String nombreAutor = rs.getString("nombreAutor"); 
                    String imagen = rs.getString("imagen");
                    String FechaRecomendacion = rs.getString("FechaRecomendacion");
                             
                    
                    RecomiendaAutor recomendacion = new RecomiendaAutor (id, idUsuario, idLibro, titulo, idAutor, nombreAutor, imagen,FechaRecomendacion);
                    
                    lista.add(recomendacion);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List<Libro> cargaListaLibrosFavoritos(int idBuscar) throws ClassNotFoundException {
        List<Libro> lista = new ArrayList<>(); 
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM librosfavoritos WHERE idUsuario = '" + idBuscar + "';";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    String id = rs.getString("id");  
                    String idUsuario = rs.getString("idUsuario");
                    String idLibro = rs.getString("idLibro");
                    String titulo = rs.getString("titulo");               
                    String idAutor = rs.getString("idAutor"); 
                    String nombreAutor = rs.getString("nombreAutor"); 
                    String yearP = rs.getString("yearP");
                    String imagen = rs.getString("imagen");
                    String fechaRecomendacion = rs.getString("FechaRecomendacion");

                    Libro recomendacion = new Libro(idUsuario, idLibro, titulo, idAutor, nombreAutor, yearP, imagen);
                    lista.add(recomendacion);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<RecomiendaTema> cargaListaRecomendacionTema(int idBuscar) throws ClassNotFoundException{
        List<RecomiendaTema> lista = new ArrayList<RecomiendaTema>(); 
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM recomendaciontema WHERE idUsuario = '" + idBuscar +"';";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("idUsuario");  
                    String idUsuario = rs.getString("idUsuario");
                    String idLibro = rs.getString("idLibro");
                    String titulo = rs.getString("titulo");               
                    String idAutor = rs.getString("idAutor");  
                    String nombreAutor = rs.getString("nombreAutor");
                    String tema = rs.getString("tema");
                    String imagen = rs.getString("imagen");
                    String FechaRecomendacion = rs.getString("FechaRecomendacion");
                             
                    
                    RecomiendaTema recomendacion = new RecomiendaTema (id, idUsuario, idLibro, titulo, idAutor, nombreAutor,tema, imagen,FechaRecomendacion);
                    
                    lista.add(recomendacion);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public void registraUsuario(Usuario usuario) throws ClassNotFoundException, NoSuchAlgorithmException {
        String password = getMD5Hash(usuario.getPaswordU());
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                
                Statement st = conn.createStatement();
                String query =  "insert into usuario (nombre, apellidoP, apellidoM, edad, genero, correo, userU, passwordU ) values('" + usuario.getNombre() + "', '" + usuario.getApellidoP() +  "', '" + usuario.getApellidoM() + "', '" + usuario.getEdad()+ "', '" + usuario.getGenero() + "', '" + usuario.getCorreo() + "', '" + usuario.getUserU() + "', '" + password +"')";
                System.out.println(query);
                st.executeUpdate(query);
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrarAutor(Autor autor) throws ClassNotFoundException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                String query = "SELECT * FROM autor WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, autor.getId());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    System.out.println("El autor ya habia sido buscado anteriormente...");
                    // El registro ya existe, actualizarlo
                    query = "UPDATE autor SET nombre = ?, mejorlibro = ?, fechaNacimiento = ?, fechaFallecimiento = ?, biografia = ? WHERE id = ?";
                    pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, autor.getNombre());
                    pstmt.setString(2, autor.getMejorlibro());
                    pstmt.setString(3, autor.getFechaNacimiento());
                    pstmt.setString(4, autor.getFechaFallecimiento());
                    pstmt.setString(5, autor.getBiografia());
                    pstmt.setString(6, autor.getId());
                    pstmt.executeUpdate();
                } else {
                    // El registro no existe, insertarlo
                    System.out.println("El autor es ...");
                    query = "INSERT INTO autor (id, nombre, mejorlibro, fechaNacimiento, fechaFallecimiento, biografia) VALUES (?, ?, ?, ?, ?, ?)";
                    pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, autor.getId());
                    pstmt.setString(2, autor.getNombre());
                    pstmt.setString(3, autor.getMejorlibro());
                    pstmt.setString(4, autor.getFechaNacimiento());
                    pstmt.setString(5, autor.getFechaFallecimiento());
                    pstmt.setString(6, autor.getBiografia());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrarRecomendacionAutor(RecomiendaAutor recomendacion) throws ClassNotFoundException {
        if (recomendacion == null) {
            System.err.println("Error: La recomendación es null");
            return;
        }

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                String query = "INSERT INTO recomendacionautor (idUsuario, idLibro, titulo, idAutor, nombreAutor, imagen) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, recomendacion.getIdUsuario());
                pstmt.setString(2, recomendacion.getIdLibro());
                pstmt.setString(3, recomendacion.getTitulo());
                pstmt.setString(4, recomendacion.getIdAutor());
                pstmt.setString(5, recomendacion.getNombreAutor());
                pstmt.setString(6, recomendacion.getImagen());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void registarLibroFavorito(Libro libro) throws ClassNotFoundException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                String query = "insert into librosfavoritos (idUsuario, idLibro, titulo, idAutor, nombreAutor, yearP, imagen) values (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, libro.getIdUsuario());
                st.setString(2, libro.getIdLibro());
                st.setString(3, libro.getTitulo());
                st.setString(4, libro.getIdAutor());
                st.setString(5, libro.getNombreAutor());
                st.setString(6, libro.getYearP());
                st.setString(7, libro.getImagen());
                st.executeUpdate();
            }
        } catch(SQLException ex){
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrarRecomendacionTema(RecomiendaTema recomendacion) throws ClassNotFoundException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                
                Statement st = conn.createStatement();

                String query =  "insert into recomendaciontema ( idUsuario, idLibro, titulo, idAutor, nombreAutor ,tema, imagen) values('" + recomendacion.getIdUsuario() + "', '" + recomendacion.getIdLibro() + "', '" + recomendacion.getTitulo() +  "', '" + recomendacion.getIdAutor() + "', '" + recomendacion.getNombreAutor() + "', '" + recomendacion.getTema() + "', '" + recomendacion.getImagen() +"')";
                System.out.println(query);
                st.executeUpdate(query);
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrarTemaFavoritoUsuario(String idUsuario,String idTema, String tema) throws ClassNotFoundException {
    
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                
                Statement st = conn.createStatement();
                String query =  "insert into gustotemas (idUsuario, idtema, tema) values('" + idUsuario + "', '" + idTema + "', '" + tema +"')";
                System.out.println(query);
                st.executeUpdate(query);
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Object buscarAutor(String id) throws ClassNotFoundException, ParseException, UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();
        String bautor = "https://openlibrary.org/authors/" + id + ".json";
        System.out.println("Primero trato de buscar por id: " + id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try {
            response = restTemplate.exchange(bautor, HttpMethod.GET, request, String.class);
            System.out.println("Status: " + response.getStatusCode());

            if (response.getStatusCode() == HttpStatus.OK) {
                JSONParser parser = new JSONParser();
                JSONObject responseJSON = (JSONObject) parser.parse(response.getBody());

                System.out.println("Resultado " + responseJSON.toJSONString());

                String keyS = responseJSON.containsKey("key") ? responseJSON.get("key").toString() : "null";
                String nombreS = responseJSON.containsKey("name") ? responseJSON.get("name").toString() : "null";
                String topWorkS = responseJSON.containsKey("top_work") ? responseJSON.get("top_work").toString() : "null";
                String fechaNacS = responseJSON.containsKey("birth_date") ? responseJSON.get("birth_date").toString() : "null";
                String fechaFinS = responseJSON.containsKey("death_date") ? responseJSON.get("death_date").toString() : "null";
                //String bioS = responseJSON.containsKey("bio") ? responseJSON.get("bio").toString() : "null";
                String bioV = "";

                if (responseJSON.containsKey("bio")) {
                    JSONObject bioObject = (JSONObject) responseJSON.get("bio");
                    String bioS = (String) bioObject.get("value");
                    System.out.println("el valor filtrado del json es:" + bioS);
                    bioV = bioS;
                } else {
                    // manejar el caso en que "bio" no existe
                }

                System.out.println("ID: " + keyS);
                System.out.println("Nombre: " + nombreS);
                System.out.println("Su mejor trabajo: " + topWorkS);
                System.out.println("Año nacimiento: " + fechaNacS);
                System.out.println("Año muerte: " + fechaFinS);
                System.out.println("Biografia: " + bioV);

                return new Autor(keyS, nombreS, topWorkS, fechaNacS, fechaFinS, bioV);
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("No se encontró autor con ID " + id + ", buscando por nombre: " + id);

                String nombreEscapado = URLEncoder.encode(id, StandardCharsets.UTF_8.toString());
                bautor = "https://openlibrary.org/search/authors.json?q=" + nombreEscapado;

                response = restTemplate.exchange(bautor, HttpMethod.GET, request, String.class);
                System.out.println("Status: " + response.getStatusCode());

                if (response.getStatusCode() == HttpStatus.OK) {
                    JSONParser parser = new JSONParser();
                    JSONObject responseJSON = (JSONObject) parser.parse(response.getBody());

                    System.out.println("Resultado " + responseJSON.toJSONString());

                    Object encontrados = responseJSON.get("numFound");
                    if (encontrados == null || Integer.parseInt(encontrados.toString()) == 0) {
                        return null; // No se encontraron autores
                    }

                    System.out.println("numFound " + encontrados.toString());

                    JSONArray busqueda = (JSONArray) responseJSON.get("docs");
                    List<Autor> autores = new ArrayList<>();

                    for (Object obj : busqueda) {
                        JSONObject autorJSON = (JSONObject) obj;
                        String keyS = autorJSON.containsKey("key") ? autorJSON.get("key").toString() : "null";
                        String nombreS = autorJSON.containsKey("name") ? autorJSON.get("name").toString() : "null";
                        String topWorkS = autorJSON.containsKey("top_work") ? autorJSON.get("top_work").toString() : "null";
                        String fechaNacS = autorJSON.containsKey("birth_date") ? autorJSON.get("birth_date").toString() : "null";
                        String fechaFinS = autorJSON.containsKey("death_date") ? autorJSON.get("death_date").toString() : "null";
                        String temasAutorS = autorJSON.containsKey("top_subjects") ? autorJSON.get("top_subjects").toString() : "null";

                        System.out.println("ID: " + keyS);
                        System.out.println("Nombre: " + nombreS);
                        System.out.println("Su mejor trabajo: " + topWorkS);
                        System.out.println("Año nacimiento: " + fechaNacS);
                        System.out.println("Año muerte: " + fechaFinS);
                        System.out.println("Biografiaa: " + temasAutorS);

                        Autor autor = new Autor(keyS, nombreS, topWorkS, fechaNacS, fechaFinS, temasAutorS);
                        autores.add(autor);
                    }

                    return autores;
                }
            } else {
                throw e; // Re-throw other exceptions
            }
        }
        return null;
    }
        
    public RecomiendaAutor RecomiendaPorAutor(String idAutor, String idUsuario, String nombreAutor) throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String bautor = "https://openlibrary.org/" + idAutor + "/works.json?limit=100";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(bautor, HttpMethod.GET, request, String.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.err.println("Error: La clave no se encontró en el servicio");
                return null; // O maneja el error de otra manera adecuada
            } else {
                throw e; // Re-lanzar la excepción si no es un error 404
            }
        }

        System.out.println("Status: " + response.getStatusCode());

        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(response.getBody());

        System.out.println("Resultado " + responseJSON.toJSONString());

        Object encontrados = responseJSON.get("size");
        if (encontrados == null) {
            throw new NullPointerException("El campo 'size' no se encontró en la respuesta JSON");
        }
        System.out.println("numFound " + encontrados.toString());

        int jsonInt = Integer.parseInt(encontrados.toString());
        if (jsonInt > 100) {
            jsonInt = 100;
        }
        int random = (int) (Math.random() * jsonInt);

        JSONArray busqueda = (JSONArray) responseJSON.get("entries");
        System.out.println("Autor " + busqueda.toJSONString());
        JSONObject libro = (JSONObject) busqueda.get(random);

        String keyS = libro.containsKey("key") ? libro.get("key").toString() : "null";
        String tituloS = libro.containsKey("title") ? libro.get("title").toString() : "null";
        String imagenS = libro.containsKey("covers") ? String.valueOf(((List<Integer>) libro.get("covers")).get(0)) : "null";

        RecomiendaAutor recomendacion = new RecomiendaAutor(idUsuario, keyS, tituloS, idAutor, nombreAutor, imagenS);

        System.out.println("Id usuario: " + idUsuario);
        System.out.println("Id Libro: " + keyS);
        System.out.println("Titulo del libro: " + tituloS);
        System.out.println("Numero random: " + jsonInt);
        System.out.println("Idusuario: " + idAutor);

        return recomendacion;
    }
    
    public RecomiendaTema RecomiendaPorTema(String clave, String tema, String idUsuario) throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String bautor = "https://openlibrary.org/subjects/" + clave + ".json?limit=100";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(bautor, HttpMethod.GET, request, String.class);

        System.out.println("Status: " + response.getStatusCode());

        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(response.getBody());

        System.out.println("Resultado " + responseJSON.toJSONString());

        Object encontrados = (Object) responseJSON.get("work_count");
        System.out.println("work_count " + encontrados.toString());

        int Random = (int) (Math.random() * Integer.parseInt(encontrados.toString()));

        JSONArray busqueda = (JSONArray) responseJSON.get("works");
        JSONObject libro = (JSONObject) busqueda.get(Random);

        Object key = (Object) libro.get("key");
        Object titulo = (Object) libro.get("title");

        JSONArray autores = (JSONArray) libro.get("authors");
        JSONObject autor = (JSONObject) autores.get(0);

        Object keyAutor = (Object) autor.get("key");
        Object nameAutor = (Object) autor.get("name");
        Object imagen = (Object) libro.get("cover_id");

        String keyS = "" ;
        String tituloS = "" ;
        String keyAutorS = "" ;
        String nameAutorS = "" ;
        String imagenS = "" ;

        if (libro.containsKey("key")){              
            keyS = key.toString();
        }else {
            keyS = "null";
        }

        if (libro.containsKey("title")){
            tituloS = titulo.toString();
        }else {
            tituloS = "null";
        }

        if (autor.containsKey("key")){              
            keyAutorS = keyAutor.toString();
        }else {
            keyAutorS = "null";
        }

        if (autor.containsKey("name")){
            nameAutorS = nameAutor.toString();
        }else {
            nameAutorS = "null";
        }

        if (libro.containsKey("cover_id")){
            imagenS = imagen.toString();
        }else {
            imagenS = "null";
        }

        RecomiendaTema recomendacion = new RecomiendaTema(idUsuario, keyS, tituloS, keyAutorS, nameAutorS, tema, imagenS);

        System.out.println("Id Libro: " + keyS);
        System.out.println("Titulo del libro: " + tituloS);
        System.out.println("Id autor: " + keyAutorS);
        System.out.println("Nombre Autor: " + nameAutorS);

        return recomendacion;
    }
    
    public void registarGustoAutor(GustoAutor gusto) throws ClassNotFoundException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                String query = "insert into gustoautor (idUsuario, idAutor, nombreAutor) values(?, ?, ?)";
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, gusto.getIdUsuario());
                st.setString(2, gusto.getIdAutor());
                st.setString(3, gusto.getNombreAutor());
                st.executeUpdate();
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     
    public String autoresFavoritos() throws ClassNotFoundException{
        Autor autor = new Autor();
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String clave = "OL9358783A";
                String query = "SELECT * FROM autor WHERE id = 'OL1A'";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    String id = rs.getString("idUsuario");
                    String nombre = rs.getString("nombre");  
                    String mejorlibro = rs.getString("mejorlibro"); 
                    String fechaNacimiento = rs.getString("fechaNacimiento"); 
                    String fechaFallecimiento = rs.getString("fechaFallecimiento");
                    String biografia = rs.getString("biografia");
                    
                    
                    autor = new Autor(id,nombre,mejorlibro,fechaNacimiento,fechaFallecimiento,biografia);
                    System.out.println(autor.toString());      
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return autor.toString();
    }
     
     public List <GustoAutor> cargaListaGustoAutorDAO(int idBuscar) throws ClassNotFoundException{
        List<GustoAutor> lista = new ArrayList<GustoAutor>(); 
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM gustoautor WHERE idUsuario = '" + idBuscar +"';";
                //String query = "SELECT * FROM gustoautor WHERE idUsuario = '2';";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("idUsuario");  
                    String idUsuario = rs.getString("idUsuario");  
                    String idAutor = rs.getString("idAutor");  
                             
                    
                    GustoAutor gustoAutor = new GustoAutor (id , idUsuario, idAutor);
                    
                    lista.add(gustoAutor);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public Usuario encontrarDatosUsuario(String usuario) throws ClassNotFoundException , NoSuchAlgorithmException, SQLException {
        
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros  ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM usuario WHERE userU = '" + usuario +"';";
                ResultSet rs = st.executeQuery(query);          
                while (rs.next()){
                    int id = rs.getInt("idUsuario");
                    String nombre = rs.getString("nombre");  
                    String apellidoP = rs.getString("apellidoP"); 
                    String apellidoM = rs.getString("apellidoM"); 
                    int edad = rs.getInt("edad");
                    String genero = rs.getString("genero");
                    String correo = rs.getString("correo");
                    String userU = rs.getString("userU");
                    String passwordU = rs.getString("passwordU");       
                    
                    Usuario usuarioencontrado = new Usuario(id , nombre, apellidoP, apellidoM, edad, genero, correo, userU, passwordU);
                    return usuarioencontrado;
                }           
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String validarLoginUsuario(String usuario ,String password) throws ClassNotFoundException, NoSuchAlgorithmException {
        boolean encontrado = false;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libros", "admin", "admin");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros para hacer el Login ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM usuario WHERE userU = '" + usuario +"';";
                ResultSet rs = st.executeQuery(query);
                encontrado = rs.next();
                
                if (encontrado==true) {
                    String passwordEncriptadaMysql =  rs.getString("passwordU");
                    String passwordEncriptadaFormulario = "";
                    passwordEncriptadaFormulario = getMD5Hash(password);
                    System.out.println("AAAAAAAAA" + passwordEncriptadaMysql );
                    if((passwordEncriptadaFormulario).equals(passwordEncriptadaMysql)) {
                        System.out.println("login Exitoso");                        
                        return "Encontrado";
                    } else { 
                        return "Usuario No Encontrado";
                    }
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error";
    }
       
    public static String getMD5Hash(String entradaH) throws NoSuchAlgorithmException {
        String resultado = entradaH;
        if (entradaH != null) {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(entradaH.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            resultado = hash.toString(16);
            while (resultado.length() < 40) {
                resultado = "0" + resultado;
            }
        }
        return resultado;
    }

}