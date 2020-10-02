/*En esta clase se llevara a cabo la capa de la logica del negocio
Alumno: Diaz Orozco Jesus Adrian
Maestro: Clemente Garcia Gerardo
Materia: Taller de base de datos*/
package corralesternero;

import java.awt.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JOptionPane;

public class CorralesTerneroModelo {

    public void imprime(JTable t) {
        try {
            t.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Vector< Vector<String> > consultaCriasHoy(){
        
        Vector< Vector<String> > crias = new Vector();
        Statement s = CorralesTerneroBD.getConexion();
        try{
            
            ResultSet rs = s.executeQuery("SELECT * FROM CriasInsertadasHoyView");
            while(rs.next()){
                
                Vector<String> cria = new Vector();
                cria.add( rs.getInt(1) + "" );
                cria.add( rs.getDouble(2) + "" );
                cria.add( rs.getDouble(3) + "" );
                cria.add( rs.getString(4) + "" );
                cria.add( rs.getString(5) + "" );
                cria.add( rs.getString(6) + "" );
                cria.add( rs.getString(7) + "" );
                cria.add( rs.getString(8) + "" );
                cria.add( rs.getString(9) + "" );
                crias.add(cria);
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return crias;
    }

    public boolean iniciarSesion(String user, char[] pass) {
        String password = "";
        for (int i = 0; i < pass.length; i++) {
            password += pass[i];
        }
        if (CorralesTerneroBD.getConexion(user, password) == null) {
            return false;
        }
        return true;
    }

    static public ResultSet getColumnas(String columna, String tabla) {
        return getColumnas(columna, tabla, " 1=1");
    }

    static public ResultSet getColumnas(String columna, String tabla, String condicion) {
        System.out.println("Entro a getColumnas(columna,tabla,condicion) en clase CorralesTerneroModelo");
        System.out.println("columna: "+columna);
        System.out.println("tabla: "+tabla);
        System.out.println("condicion: "+condicion);
        String sentencia = "select " + columna + " from " + tabla + " where " + condicion;
        ResultSet rs = null;
        try {
            System.out.println("va a intentar ejecutar ("+sentencia+")");
            rs = CorralesTerneroBD.getConexion().executeQuery(sentencia);
           
        } catch (SQLException e) {
            System.out.println("fallo ejecutando ("+sentencia+")");
            e.printStackTrace();
            rs = null;
        }
        return rs;
    }
    
    

    public static void liberarCria(String criId, String corId) {
        String sql = "pa_liberarCria " + criId + "," + corId;
        try {
            CorralesTerneroBD.getConexion().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void sacrificarCria(String criId) {
        String sql = "pa_sacrificarCria " + criId;
        try {
            CorralesTerneroBD.getConexion().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mandarCuarentena(Object criId, String comentario, String medId) {
        String id = (String) criId;
        String sentencia = "pa_mandarCuarentena " + id + "," + comentario + "," + medId;
        System.out.println(sentencia);
        try {
            CorralesTerneroBD.getConexion().execute(sentencia);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void simularSensores() {

        String sql = "EXEC sp_generaSensores";
        try {
            CorralesTerneroBD.getConexion().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteSensor(Object SenID) {
        String sql = "DELETE FROM InventarioSensores WHERE SenID=" + SenID.toString();
        try {
            CorralesTerneroBD.getConexion().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int grabaSensor(int ciuID) {
        int res = -1;
        String sql1 = "BEGIN TRAN "
                + "INSERT INTO InventarioSensores ( CiuID ) VALUES (" + ciuID + ")";
        String sql2 = "SELECT MAX(SenID) FROM InventarioSensores "
                + "COMMIT TRAN";
        ResultSet rs = null;
        Statement statement = CorralesTerneroBD.getConexion();
        try {
            statement.execute(sql1);
            rs = statement.executeQuery(sql2);
            rs.next();
            res = rs.getInt(1);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Vector<Vector> traeVector(String tabla, String condicion) {
        Vector<Vector> v = new Vector();
        try {
            ResultSet rs = CorralesTerneroBD.getConexion().executeQuery("SELECT * FROM "+tabla+" WHERE "+condicion);
            ResultSetMetaData md = rs.getMetaData();
            int i = 0;
            while (rs.next()) {
                v.add(new Vector());
                for (int j = 0; j < md.getColumnCount(); j++) {
                    v.get(i).add(rs.getString(j + 1));
                }
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    public static boolean darSalida() {
        String sentencia = "pa_darSalida";
        try {
            CorralesTerneroBD.getConexion().execute(sentencia);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    //https://docs.microsoft.com/en-us/sql/connect/jdbc/using-a-stored-procedure-with-output-parameters?view=sql-server-ver15
}
