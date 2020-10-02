/*En esta clase se llevara a cabo la conexion a la base de datos
Alumno: Diaz Orozco Jesus Adrian
Maestro: Clemente Garcia Gerardo
Materia: Taller de base de datos*/
package corralesternero;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class CorralesTerneroBD {

    static private String url = null;
    static private Statement statement = null;
    static public Connection conn;
    

    private CorralesTerneroBD() {
    }
    
    static synchronized public Statement getConexion(){
        return getConexion("sa","sa");
    }

    static synchronized public Statement getConexion(String usuario, String password) {
        if (statement == null) {
            url = "jdbc:sqlserver://localhost\\DESKTOP-OFG30J3:1433;databaseName=corralesternero;user="+usuario+";password="+password+";";
            try {
                conn = DriverManager.getConnection(url);
                statement = conn.createStatement();
            } catch (SQLException e) {
                return null;
            }
        }
        return statement;

    }

    static synchronized public void cierraConexion() {
        try {
            conn.close();
            statement.close();
        } catch (SQLException e) {
        }
        conn=null;
        statement=null;
    }
    
    public CorralesTerneroBD clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }
    
}
