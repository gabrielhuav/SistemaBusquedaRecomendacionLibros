/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.Pruebas;

/**
 *
 * @author XStrike
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class probarConexion {
    
    public static void main(String[] args) throws SQLException {
        probarConexion prob = new probarConexion();
        prob.probarConexion();
    }
    
    public void probarConexion() throws SQLException{
        
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/libros", "libro", "secreto");
                    
        if (conn != null) {
                System.out.println("UtilBD ====> Conectado a la base de datos de libros ");
        } else {
            System.out.println("No se puede conectar a la base de datos, F");
        } 
    }
}