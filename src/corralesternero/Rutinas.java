package corralesternero;

import java.awt.Image;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Rutinas {

	public static ImageIcon AjustarImagen(String ico,int Ancho,int Alto)
    {
        ImageIcon tmpIconAux = new ImageIcon(ico);
        //Escalar Imagen
        ImageIcon tmpIcon = new ImageIcon(tmpIconAux.getImage().getScaledInstance(Ancho,
        		Alto, Image.SCALE_SMOOTH));//SCALE_DEFAULT
        return tmpIcon;
    } 
    public static void limpiaCombo(JComboBox combo) {
        combo.removeAllItems();
        combo.addItem("Seleccione");
        combo.setSelectedIndex(0);
    }
    
    public static boolean insertaCombo(String columna, String tabla, JComboBox combo){
        return insertaCombo(columna,tabla," 1=1",combo);
    }
    
    public static boolean insertaCombo(String columna, String tabla,String condicion, JComboBox combo) {
        ResultSet res = CorralesTerneroModelo.getColumnas(columna, tabla,condicion);
        if(res==null){
            return false;
        }
        return insertaCombo(res,columna,combo);
    }
    
    public static boolean insertaCombo(ResultSet res,String columna, JComboBox combo) {
        combo.addItem("Seleccione");
        combo.setSelectedIndex(0);
        try {
            while (res.next()) {
                combo.addItem(res.getString(columna));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    e.toString(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }




}
