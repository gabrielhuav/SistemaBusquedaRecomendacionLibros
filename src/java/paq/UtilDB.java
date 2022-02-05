 package paq;

import static java.lang.System.console; 
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UtilDB{
    
    public List<Usuario> cargaListaUsuarios() throws ClassNotFoundException{
        List<Usuario> lista = new ArrayList<Usuario>(); 
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM Usuario;";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    int id = rs.getInt("id");
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
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
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
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM gustoautor WHERE idUsuario = '" + idBuscar +"';";
                //String query = "SELECT * FROM gustoautor WHERE idUsuario = '2';";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("id");  
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
    
    public List<Gustotemas> cargaListaGustoTemas(int idBuscar) throws ClassNotFoundException{
        List<Gustotemas> lista = new ArrayList<Gustotemas>(); 
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM gustotemas WHERE idUsuario = '" + idBuscar +"';";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("id");  
                    String idUsuario = rs.getString("idUsuario");  
                    String idtema = rs.getString("idtema"); 
                    String tema = rs.getString("tema");  

                    Gustotemas Gustotema = new Gustotemas (id ,idUsuario,idtema,tema);
                    
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
    
    public List<temas> cargaListaTemas() throws ClassNotFoundException{
        List<temas> lista = new ArrayList<temas>(); 
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM temas ;";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("id");  
                    String clave = rs.getString("clave");  
                    String traduccion = rs.getString("traduccion");  

                    temas tema = new temas (id , clave, traduccion );
                    
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
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM recomendacionautor WHERE idUsuario = '" + idBuscar +"';";
                //String query = "SELECT * FROM gustoautor WHERE idUsuario = '2';";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("id");  
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
    
    public List<Libro> cargaListaLibrosFavoritos(int idBuscar) throws ClassNotFoundException{
        List<Libro> lista = new ArrayList<Libro>(); 
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM librosfavoritos WHERE idUsuario = '" + idBuscar +"';";
                //String query = "SELECT * FROM gustoautor WHERE idUsuario = '2';";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("id");  
                    String idUsuario = rs.getString("idUsuario");
                    String idLibro = rs.getString("idLibro");
                    String titulo = rs.getString("titulo");               
                    String idAutor = rs.getString("idAutor"); 
                    String nombreAutor = rs.getString("nombreAutor"); 
                    String FechaRecomendacion = rs.getString("yearP");
                    String imagen = rs.getString("imagen");
                             
                    
                    Libro recomendacion = new Libro (id, idUsuario, idLibro, titulo, idAutor, nombreAutor, FechaRecomendacion,imagen);
                    
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
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM recomendaciontema WHERE idUsuario = '" + idBuscar +"';";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("id");  
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
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
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
    
    public void registarAutor(Autor autor) throws ClassNotFoundException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                
                Statement st = conn.createStatement();

                String query =  "insert into autor (id, nombre, mejorlibro, fechaNacimiento, fechaFallecimiento, biografia) values('" + autor.getId() + "', '" + autor.getNombre() + "', '" + autor.getMejorlibro() +  "', '" + autor.getFechaNacimiento() + "', '" + autor.getFechaFallecimiento()+ "', '" + autor.getBiografia() +"')";
                System.out.println(query);
                st.executeUpdate(query);
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registarRecomendacionAutor(RecomiendaAutor recomendacion) throws ClassNotFoundException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                
                Statement st = conn.createStatement();

                String query =  "insert into recomendacionAutor ( idUsuario, idLibro, titulo, idAutor, nombreAutor, imagen) values('" + recomendacion.getIdUsuario() + "', '" + recomendacion.getIdLibro() + "', '" + recomendacion.getTitulo() +  "', '" + recomendacion.getIdAutor() + "', '" + recomendacion.getNombreAutor() +  "', '" + recomendacion.getImagen() + "')";
                System.out.println(query);
                st.executeUpdate(query);
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registarLibroFavorito(Libro libro) throws ClassNotFoundException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                
                Statement st = conn.createStatement();

                String query =  "insert into librosfavoritos ( idUsuario, idLibro, titulo, idAutor, nombreAutor, yearP, imagen) values('" + libro.getIdUsuario() + "', '" + libro.getIdLibro() + "', '" + libro.getTitulo() +  "', '" + libro.getIdAutor() + "', '" + libro.getNombreAutor() + "', '" + libro.getYearP() +  "', '" + libro.getImagen() + "')";
                System.out.println(query);
                System.out.println(query);
                st.executeUpdate(query);
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registarRecomendacionTema(RecomiendaTema recomendacion) throws ClassNotFoundException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
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
    
    public void temasFavoritos(String idUsuario,String idTema, String tema) throws ClassNotFoundException {
    
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
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
        
    public Autor buscarAutor(String autor) throws ClassNotFoundException, ParseException {
   
        Client client = ClientBuilder.newClient();
        String autor1 = autor.replaceAll(" ", "+");
        String bautor = "https://openlibrary.org/search/authors.json?q=" + autor1 ;   
        WebTarget resourceWebTarget = client.target(bautor);
        
        
        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        System.out.println(response.getStatus());
        
        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(response.readEntity(String.class));
        
        System.out.println("Resultado " + responseJSON.toJSONString());
        
        Object encontrados = (Object) responseJSON.get("numFound");
        System.out.println("numFound " + encontrados.toString());
        
        JSONArray busqueda = (JSONArray) responseJSON.get("docs");
        System.out.println("Autor " + busqueda.toJSONString());
        
        JSONObject Autor = (JSONObject) busqueda.get(0);
        
        Object key = (Object) Autor.get("key");
        Object nombre = (Object) Autor.get("name");
        Object topWork = (Object) Autor.get("top_work");
        Object fechaNac = (Object) Autor.get("birth_date");
        Object fechaFin = (Object) Autor.get("death_date");
        Object bio = (Object) Autor.get("bio");
        
        String keyS = "" ;
        String nombreS = "" ;
        String topWorkS = "" ;
        String fechaNacS = "" ;
        String fechaFinS = "" ;
        String bioS = "" ;
       
        if (Autor.containsKey("key")){            
            System.out.println("key " + key.toString());    
            keyS = key.toString();
        }else {
            keyS = "null";
        }
        
        if (Autor.containsKey("name")){
            System.out.println("Nombre: " + nombre.toString());
            nombreS = nombre.toString();
        }else {
            nombreS = "null";
        }
        
        
        if (Autor.containsKey("top_work")){          
            System.out.println("Su mejor trabajo: " + topWork.toString());
            topWorkS = topWork.toString();
        }else {
            topWorkS = "null";
        }
        
        if (Autor.containsKey("birth_date")){            
            System.out.println("Año nacimiento: " + fechaNac.toString());
             fechaNacS = fechaNac.toString();
        }else {
            fechaNacS = "null";
        }
        
        if (Autor.containsKey("death_date")){
            System.out.println("Año muerte: " + fechaFin.toString());
            fechaFinS = fechaFin.toString();
        }else {
            fechaFinS = "null";
        }
        
        if (Autor.containsKey("bio")){
            System.out.println("Biografia: " + bio.toString());
            bioS = bio.toString();
        }else{
            bioS = "null";
        }
        
        Autor nuevoAutor = new Autor(keyS,nombreS,topWorkS,fechaNacS,fechaFinS,bioS);
        return nuevoAutor;
    }
        
    public RecomiendaAutor RecomiendaPorAutor(String idAutor,String idUsuario,String  nombreAutor) throws ParseException  {
   
        Client client = ClientBuilder.newClient();
        String bautor = "https://openlibrary.org/authors/" + idAutor + "/works.json?limit=100";   
        WebTarget resourceWebTarget = client.target(bautor);
        
        
        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        System.out.println(response.getStatus());
        
        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(response.readEntity(String.class));
        
        System.out.println("Resultado " + responseJSON.toJSONString());
        
        Object encontrados = (Object) responseJSON.get("size");
        System.out.println("numFound " + encontrados.toString());
        
        int jsonInt = Integer.valueOf((String) encontrados.toString());    //Total de trabajos encontrados
        if(jsonInt > 100){
            jsonInt = 100;
        }
        int Random = (int)(Math.random()*jsonInt);
        
        JSONArray busqueda = (JSONArray) responseJSON.get("entries");
        System.out.println("Autor " + busqueda.toJSONString());
        JSONObject libro = (JSONObject) busqueda.get(Random);
        
        Object key = (Object) libro.get("key");
        Object titulo = (Object) libro.get("title");
        List<Integer> imagen = (List<Integer>) libro.get("covers");
      
         
        String keyS = "" ;
        String tituloS = "" ;
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
        
         if (libro.containsKey("covers")){
            imagenS = String.valueOf(imagen.get(0));
        }else {
            imagenS = "null";
        }
        
        RecomiendaAutor recomendacion = new RecomiendaAutor(idUsuario,keyS,tituloS,idAutor,nombreAutor,imagenS);
        
        System.out.println("Id usuario: " + idUsuario);
        System.out.println("Id Libro: " + keyS);
        System.out.println("Titulo del libro: " + tituloS);
        System.out.println("Numero random: " + jsonInt);
        System.out.println("Idusuario: " + idAutor);
        
        return recomendacion;
    }
    
    public RecomiendaTema RecomiendaPorTema(String clave,String tema,String idUsuario) throws ParseException  {
   
        Client client = ClientBuilder.newClient();
        String bautor = "https://openlibrary.org/subjects/" + clave + ".json?limit=100";   
        WebTarget resourceWebTarget = client.target(bautor);
        
        
        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        System.out.println(response.getStatus());
        
        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(response.readEntity(String.class));
        
        System.out.println("Resultado " + responseJSON.toJSONString());
        
        Object encontrados = (Object) responseJSON.get("work_count");
        System.out.println("work_count " + encontrados.toString());
        //////////////////////////////////////////////////////////
        
        int Random = (int)(Math.random()*100);  //Numero Aleatorio
        
        ///////////////////////////////////////////////////////////
        //Arreglo de libros
        JSONArray busqueda = (JSONArray) responseJSON.get("works");
        //Selecciono un arreglo de libros
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
            //imagenS = String.valueOf(imagen.toString());
            imagenS = imagen.toString();
        }else {
            imagenS = "null";
        }
        
        
        RecomiendaTema recomendacion = new  RecomiendaTema(idUsuario,keyS,tituloS,keyAutorS,nameAutorS,tema,imagenS);
        
        //System.out.println("Id usuario: " + idUsuario);
        System.out.println("Id Libro: " + keyS);
        System.out.println("Titulo del libro: " + tituloS);
        System.out.println("Id autor: " + keyAutorS);
        System.out.println("Nombre Autor: " + nameAutorS);
        
       return recomendacion;
    
    }
    
    public void registarGustoAutor(GustoAutor gusto) throws ClassNotFoundException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                
                Statement st = conn.createStatement();

                String query =  "insert into gustoautor (idUsuario, idAutor , nombreAutor ) values('" + gusto.getIdUsuario() + "', '" + gusto.getIdAutor() + "', '"+ gusto.getNombreAutor() +"')";
                System.out.println(query);
                st.executeUpdate(query);
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public String autoresFavoritos() throws ClassNotFoundException{
        Autor autor = new Autor();
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String clave = "OL9358783A";
                String query = "SELECT * FROM autor WHERE id = 'OL1A'";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    String id = rs.getString("id");
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
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM gustoautor WHERE idUsuario = '" + idBuscar +"';";
                //String query = "SELECT * FROM gustoautor WHERE idUsuario = '2';";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    
                    String id = rs.getString("id");  
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
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros  ");
                Statement st = conn.createStatement();

                String query = "SELECT * FROM usuario WHERE userU = '" + usuario +"';";
                ResultSet rs = st.executeQuery(query);          
                while (rs.next()){
                    int id = rs.getInt("id");
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
            
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
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