package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    
    
    public void registraUsuario(Usuario usuario) throws ClassNotFoundException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
            if (conn != null) {
                
                Statement st = conn.createStatement();

                String query =  "insert into usuario (nombre, apellidoP, apellidoM, edad, genero, correo, userU, passwordU ) values('" + usuario.getNombre() + "', '" + usuario.getApellidoP() +  "', '" + usuario.getApellidoM() + "', '" + usuario.getEdad()+ "', '" + usuario.getGenero() + "', '" + usuario.getCorreo() + "', '" + usuario.getUserU() + "', '" + usuario.getPaswordU() +"')";
                System.out.println(query);
                st.executeUpdate(query);
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}