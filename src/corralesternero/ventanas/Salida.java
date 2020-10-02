/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corralesternero.ventanas;

/**
 *
 * @author adria
 */
import corralesternero.CorralesTerneroModelo;
import corralesternero.Rutinas;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.table.DefaultTableModel;
public class Salida extends JDialog{
    
    JTable tabla;
    JButton salida;
    JLabel titulo;
    JScrollPane pan;
    JComboBox estados,ciudades;
    
    public Salida(){
        setTitle("Gestion de Salidas");
        hazInterfaz();
    }
    
    public void hazInterfaz(){
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500,400);
        setLayout(new BorderLayout());
        setModal(true);
        
        tabla=new JTable();
        estados=new JComboBox();
        ciudades=new JComboBox();
        pan=new JScrollPane(tabla);
        salida=new JButton("Realizar Salida");
        salida.setSize(150,35);
        titulo=new JLabel("Crias vigentes para siguiente proceso",SwingConstants.CENTER);
        titulo.setFont(new Font("AR BLANCA",Font.PLAIN,20));
        JPanel panCentro=new JPanel();
        JPanel panSur=new JPanel();
        JPanel panNorte=new JPanel();
        panNorte.setLayout(new GridLayout(0,1));
        panNorte.add(titulo);
        JPanel panNorteCombos = new JPanel();
        panNorteCombos.setLayout(new GridLayout(0,5));
        panNorteCombos.add(new JLabel("Estados",SwingConstants.RIGHT));
        panNorteCombos.add(estados);
        panNorteCombos.add(new JLabel("Ciudades",SwingConstants.RIGHT));
        panNorteCombos.add(ciudades);
        panNorteCombos.add(new JLabel());
        panNorte.add(panNorteCombos);
        
        panSur.add(salida);
        
        add(panNorte,BorderLayout.NORTH);
        add(pan,BorderLayout.CENTER);
        add(panSur,BorderLayout.SOUTH);
        
    }
    public void llenaCombo(){
    if(estados.getItemCount()!=0)
        return;
    if(!Rutinas.insertaCombo("EdoNombre", "Estados", estados))
        return;
    estados.addItem("Todos");
        
    ciudades.addItem("Seleccione");
    }
    public void llenaCiudades(){
        
        if(estados.getSelectedIndex()>=0){
            ciudades.removeAllItems();
            ResultSet res=CorralesTerneroModelo.getColumnas("ciunombre", "ciudades", "edoid="+estados.getSelectedIndex());
            if(res!=null){
                 Rutinas.insertaCombo(res,"ciunombre", ciudades);
                 ciudades.addItem("Todos");
            }
            
        }
    }
    
    public void hazTabla(){
        String where;
        if(estados.getSelectedItem()=="Todos" || estados.getSelectedIndex()==0){
            where=" 1=1";
        } else {
            where=" Ciudad in (SELECT CiuNombre From Ciudades WHERE EdoID="+estados.getSelectedIndex()+")";
        }
        if(ciudades.getSelectedIndex()==0 || ciudades.getSelectedItem()=="Todos" || ciudades.getSelectedItem()==null){
            where+=" AND 1=1";
        } else {
            where+=" AND Ciudad='"+ciudades.getSelectedItem()+"'";
        }
        ResultSet rsCrias=CorralesTerneroModelo.getColumnas("*" , "criaslistasview",where);      
        
        Vector<String>filas;
        
        DefaultTableModel modelo=new DefaultTableModel();
        modelo.addColumn("ID Cria");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Corral");
        modelo.addColumn("Ingreso");
        modelo.addColumn("Meses");
        
        try {
            while (rsCrias.next()) {
                filas = new Vector();
                for (int i = 0; i < 5; i++) {
                    filas.add(rsCrias.getString(i + 1));
                }

                modelo.addRow(filas);

            }
            System.out.println("termino de llenar");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        tabla.setModel(modelo);
        validate();
    
    }
    
    public JComboBox getEstados(){
        return estados;
    }
    
    public JComboBox getCiudades(){
        return ciudades;
    }
    
    public void muestra(){
        setVisible(true);
    }
    public void ocultar(){
        estados.setSelectedIndex(0);
        setVisible(false);
    }
    
    public JButton getBtnSalida(){
        return salida;
    }
    
    public JTable getTabla(){
        return tabla;
    }
    
}
