
package Reportes;

import corralesternero.CorralesTerneroModelo;
import corralesternero.Rutinas;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import javax.swing.table.*;
public class Reporte extends JDialog{

    private JComboBox corral,estado,ciudad,grasa;
    private JTable crias;
    private JScrollPane pan;
    private JButton imprimir;
    private DefaultTableModel m;
    JPanel pNorte;
    
    public Reporte(){
        hazInterfaz();
    }
    
    public void hazInterfaz(){
        setTitle("Reportes");
        setSize(1000,400);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setModal(true);
        
        corral=new JComboBox();
        estado=new JComboBox();
        ciudad=new JComboBox();
        grasa=new JComboBox();
        crias=new JTable();
        
        pan=new JScrollPane(crias);
        imprimir=new JButton("Imprimir");
        imprimir.setPreferredSize(new Dimension(150, 35));
        JPanel panSur=new JPanel();
        
        
        pNorte=new JPanel();
        pNorte.add(new JLabel("Estado",SwingConstants.RIGHT));
        pNorte.add(estado);
        pNorte.add(new JLabel("Ciudades",SwingConstants.RIGHT));
        pNorte.add(ciudad);
        pNorte.add(new JLabel("Corral",SwingConstants.RIGHT));
        pNorte.add(corral);
        pNorte.add(new JLabel("Grasa",SwingConstants.RIGHT));
        pNorte.add(grasa);
        
        
        
        m=new DefaultTableModel();
        m.addColumn("ID Cria");
        m.addColumn("Corral");
        m.addColumn("Peso");
        m.addColumn("Cant. Grasa");
        m.addColumn("Col. Musculo");
        m.addColumn("Tipo grasa");
        m.addColumn("Ciudad");
        m.addColumn("Ingreso");
        m.addColumn("Egreso");
        m.addColumn("Motivo");
        m.addColumn("Meses");
        
        crias.setModel(m);
        
        panSur.add(imprimir);
        add(new JPanel(),BorderLayout.EAST);
        add(new JPanel(),BorderLayout.WEST);
        add(panSur,BorderLayout.SOUTH);
        add(pNorte,BorderLayout.NORTH);
        add(pan,BorderLayout.CENTER);
        
    }
    
    public void setCiudades(int indice) {
        ciudad.removeAllItems();
        ResultSet res=CorralesTerneroModelo.getColumnas("ciunombre", "ciudades", "edoid="+indice);
        if(res!=null)
            Rutinas.insertaCombo(res,"ciunombre", ciudad);
    }
    
    public void llenaTabla(){
        String where;
        if(corral.getSelectedItem()=="Todos" || corral.getSelectedIndex()==0 ){
            where="1=1";
        } else {
            where="corral='"+corral.getSelectedItem()+"'";
        }
        if(estado.getSelectedItem()=="Todos" || estado.getSelectedIndex()==0){
            where+=" AND 1=1";
        } else {
            where+=" AND Ciudad in (SELECT CiuNombre From Ciudades WHERE EdoID="+estado.getSelectedIndex()+")";
        }
        if(ciudad.getSelectedIndex()==0 || ciudad.getSelectedItem()=="Todos" || ciudad.getSelectedItem()==null){
            where+=" AND 1=1";
        } else {
            where+=" AND Ciudad='"+ciudad.getSelectedItem()+"'";
        }
        if(grasa.getSelectedItem()=="Todos" || grasa.getSelectedIndex() == 0){
            where+=" AND 1=1";
        } else {
            where+=" AND [Tipo de grasa]='"+grasa.getSelectedItem()+"'";
        }
        ResultSet rsCrias=CorralesTerneroModelo.getColumnas("*",
                "ViewCriasQueDuraronMasDe5Meses", where);

        Vector<String>filas;
        m=new DefaultTableModel();
        
        m.addColumn("ID Cria");
        m.addColumn("Corral");
        m.addColumn("Peso");
        m.addColumn("Cant. Grasa");
        m.addColumn("Col. Musculo");
        m.addColumn("Tipo grasa");
        m.addColumn("Ciudad");
        m.addColumn("Ingreso");
        m.addColumn("Egreso");
        m.addColumn("Motivo");
        m.addColumn("Meses");
        
        
        try {
            while (rsCrias.next()) {
                filas = new Vector();
                for (int i = 0; i < 11; i++) {
                    filas.add(rsCrias.getString(i + 1));
                }

                m.addRow(filas);
            }

        } catch (SQLException e) {

        }
        crias.setModel(m);
        
        crias.getColumnModel().getColumn(5).setPreferredWidth(120);
        validate();
    }
    public void llenaCombo(){
        if(!Rutinas.insertaCombo("EdoNombre", "Estados", estado))
            return;
        
        estado.addItem("Todos");
        ciudad.addItem("Seleccione");
        
        
        if(!Rutinas.insertaCombo("cornombre", "corrales", corral))
            return;
        corral.addItem("Todos");
        
        if(!Rutinas.insertaCombo("GraNombre", "Grasas", grasa))
            return;
        grasa.addItem("Todos");
    }
    public JComboBox getEstado(){
        return estado;
    }
    
    public JComboBox getCiudad(){
        return ciudad;
    }
    
    public JComboBox getGrasa(){
        return grasa;
    }
    
    public JTable getCrias(){
        return crias;
    }
    
    public JButton getImprimir(){
        return imprimir;
    }
    
    public JComboBox getCorral(){
        return corral;
    }
    
    public void setControlador(ReporteControlador c){
        
        imprimir.addActionListener(c);
        
        corral.addItemListener(c);
        estado.addItemListener(c);
        ciudad.addItemListener(c);
        grasa.addItemListener(c);
        
        
        corral.addActionListener(c);
        ciudad.addActionListener(c);
        grasa.addActionListener(c);
        
    }
    
    public void muestra(){
        
        llenaCombo();
        setVisible(true);
    }
}
