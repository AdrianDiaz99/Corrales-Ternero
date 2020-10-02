/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InsertarCria;

import corralesternero.CorralesTerneroBD;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;

/**
 *
 * @author adria
 */
public class DatosCriaModelo {

    public void imprime(JTable t) {
        try {
            t.print();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int grabarDatosCria(String peso, String cantGrasa, int colorMusculo,
            int ciudad, int corral, int grasa) {
        int criID = -1;
        ResultSet rs = null;
        try {
            CallableStatement cs = CorralesTerneroBD.conn.prepareCall("{call dbo.pa_InsertaCrias(?,?,?,?,?,?,?)}");
            cs.setDouble(1, Double.parseDouble(peso));
            cs.setDouble(2, Double.parseDouble(cantGrasa));
            cs.setInt(3, colorMusculo);
            cs.setInt(4, ciudad);
            cs.setInt(5, corral);
            cs.setInt(6, grasa);
            cs.registerOutParameter("CriID", java.sql.Types.INTEGER);
            cs.execute();
            criID = Integer.parseInt(cs.getInt("CriID") + "");
            //Insercion no entro al catch, se inserto correctamente
            return criID;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Si llega aqui regresara -1 ( No se pudo hacer la insercion )
        return criID;
    }

    public int calculaGrasa(double peso, double cantGrasa, int colorMusculo) {

        try {
            CallableStatement cs = CorralesTerneroBD.conn.prepareCall("{call dbo.pa_CalculaTipoGrasa (?,?,?,?)}");
            cs.setDouble(1, peso);
            cs.setDouble(2, cantGrasa);
            cs.setInt(3, colorMusculo);
            cs.registerOutParameter("GraID", java.sql.Types.INTEGER);
            cs.execute();
            int graID = cs.getInt("GraID");
            return graID;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

}
