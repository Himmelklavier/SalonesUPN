package salonesupn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    public static Connection conectar(){
     //Poner en clase aparte y método aparte llamados conexión
        String dbURL = "jdbc:mysql://localhost:3306/lem_upn_2022v1";
        String username = "root";
        String password = "";
        Connection conn;
        //String password = Credentials.PASSWORD; //genérico

    // conectar
        try {
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println(" Conectado ");
            }
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
        
    }
    
}
