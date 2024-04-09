package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Esta clase se encargara de establecer y cerrar la conexion con la base de datos
public class ConfigDB {

    // ESte atributo tendra el estado de la conexion
    static Connection objConnection = null;

    // Metodo para conectar la BD
    public static Connection openConnection() {

        try {

            // Llamamos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Creamos las variables de conexion
            String url = "jdbc:mysql://b7myktzllsfzpoxl0vp8-mysql.services.clever-cloud.com:3306/b7myktzllsfzpoxl0vp8";

            String user = "u9oyaua2x5ekdjhb";
            String password = "WvFiApWsSlRHSQGOyRqa";

            // Establecer la conexion
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Me conecte perfectamente!!!!");

        } catch (ClassNotFoundException error) {
            System.out.println("Error >> Driver no Instalado " + error.getMessage());

        } catch (SQLException error) {
            System.out.println("Error >> error al conectar con la base de datos " + error.getMessage());

        }

        return objConnection;

    }


    // Metodo para finalizar una conexion
    public static void closeConnection() {

        try {
            // Si hay una conexion activa entonces la cierra
            if (objConnection != null)  {
                objConnection.close();
                System.out.println("Se finalizo la conexion con exito");
            };

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}