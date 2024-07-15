/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {

    public static void main(String[] args) {
        String dbUrl = "jdbc:mariadb://localhost:3306/libros";
        String dbUser = "admin";
        String dbPassword = "admin";

        try {
            // Cargar el controlador de la base de datos
            Class.forName("org.mariadb.jdbc.Driver");

            // Establecer la conexión
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos");
                conn.close();
            } else {
                System.out.println("No se pudo establecer la conexión");
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: No se encontró el controlador de la base de datos");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("Error al conectar a la base de datos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
