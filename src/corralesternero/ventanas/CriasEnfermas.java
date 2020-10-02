package corralesternero.ventanas;

/**
 * Esta clase se utilizara como ventana para mostrar las crias enfermas y el
 * reporte de sus sensores en los pasados 3 dias, para que se pueda determinar
 * si sera regresada a algun corral de crias sanas o sera sacrificada.
 * @Autor: Jesus Adrian Diaz Orozco
 * @Maestro: Clemente Garcia Gerardo
 * @Materia: Taller de base de datos
 * @Escuela: Instituto Tecnologico de culiacan
 */

import corralesternero.CorralesTerneroModelo;
import corralesternero.Rutinas;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

    

public class CriasEnfermas extends JDialog{
    
    private JLabel etiEstados, etiCiudades, etiTablaCrias,etiTablaSensores;
    private JComboBox estados;
    private JComboBox ciudades;
    private JScrollPane scrollEstados, scrollCiudades,scrollTablaCrias, scrollTablaSensores;
    private JButton limpiar,sacrificar,liberar, consultar;
    private JTable tablaCrias, tablaSensores;
    private DefaultTableModel mTablaCrias, mTablaSensores;
    private IngresaCorral ingresaCorral;

    
    private class IngresaCorral extends JDialog{
        
        public JComboBox comboCorral;
        public JButton guardar;
        
        public IngresaCorral(){
            
            setTitle("Ingresar corral");
            setSize(200,200);
            setResizable(false);
            setLayout(null);
            setModal(true);
            
            
            comboCorral=new JComboBox();
            guardar=new JButton("Guardar");
            
            comboCorral.setBounds(50, 40, 100, 30);
            guardar.setBounds(50, 90, 100, 30);
            
            add(comboCorral);
            add(guardar);
            
        }
           
    }
    
    public CriasEnfermas(){
        
        hazInterfaz();
        
    }
    
    private void hazInterfaz(){
        setTitle("Crias Enfermas");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(800,700);
        setModal(true);
        
        estados=new JComboBox();
        ciudades=new JComboBox();
        scrollCiudades=new JScrollPane(ciudades);
        scrollEstados=new JScrollPane(estados);
        sacrificar=new JButton("Sacrificar");
        liberar=new JButton("Liberar");
        limpiar=new JButton("Limpiar");
        consultar=new JButton("Consultar");
        tablaCrias=new JTable();
        tablaSensores=new JTable();
        mTablaCrias=new DefaultTableModel();
        mTablaSensores=new DefaultTableModel();
        scrollTablaCrias=new JScrollPane(tablaCrias);
        scrollTablaSensores=new JScrollPane(tablaSensores);
        etiEstados=new JLabel("Estado",SwingConstants.RIGHT);
        etiCiudades=new JLabel("Ciudad",SwingConstants.RIGHT);
        etiTablaCrias=new JLabel("Crias",SwingConstants.RIGHT);
        etiTablaSensores=new JLabel("Sensores",SwingConstants.RIGHT);
        ingresaCorral=new IngresaCorral();
        
        
        mTablaCrias.addColumn("ID Cria");
        mTablaCrias.addColumn("Peso");
        mTablaCrias.addColumn("Cant. Grasa");
        mTablaCrias.addColumn("Col. Musc.");
        mTablaCrias.addColumn("Estado");
        mTablaCrias.addColumn("Ciudad");
        mTablaCrias.addColumn("Fec. Ingreso");
        mTablaCrias.addColumn("Dias");
        
        mTablaSensores.addColumn("ID Cria");
        mTablaSensores.addColumn("Fecha");
        mTablaSensores.addColumn("F. Cardiaca");
        mTablaSensores.addColumn("F. Respiratoria");
        
        tablaCrias.setModel(mTablaCrias);
        tablaSensores.setModel(mTablaSensores);
        
        etiEstados.setBounds(20, 20, 80, 30);
        scrollEstados.setBounds(125, 20, 120, 30);
        etiCiudades.setBounds(20, 60, 80, 30);
        scrollCiudades.setBounds(125, 60, 120, 30);
        consultar.setBounds(400, 40, 120, 30);
        limpiar.setBounds(530, 40, 120, 30);
        etiTablaCrias.setBounds(720, 100, 50, 30);
        scrollTablaCrias.setBounds(20, 125, 755, 350);
        etiTablaSensores.setBounds(345, 475, 70, 30);
        scrollTablaSensores.setBounds(20, 500, 400 , 150);
        sacrificar.setBounds(510, 530, 120, 30);
        liberar.setBounds(510, 575, 120, 30);
        
        
        add(etiEstados);
        add(scrollEstados);
        add(etiCiudades);
        add(scrollCiudades);
        add(limpiar);
        add(consultar);
        add(etiTablaCrias);
        add(scrollTablaCrias);
        add(etiTablaSensores);
        add(scrollTablaSensores);
        add(sacrificar);
        add(liberar);
        
        //setVisible(true);
        //ingresaCorral.setVisible(true);
    }
    /*
    public static void main(String[]a){
        new CriasEnfermas();
    }
    */
    
    public JButton getGuardar(){
        return ingresaCorral.guardar;
    }
   
    public String getCorral(){
        return ingresaCorral.comboCorral.getSelectedIndex()+"";
    }
    
    public JButton getConsultar(){
        return consultar;
    }
    
    public JButton getLimpiar(){
        return limpiar;
    }
    
    public JButton getSacrificar(){
        return sacrificar;
    }
    
    public JButton getLiberar(){
        return liberar;
    }
    
    public JComboBox getCiudades(){
        return ciudades;
    }
    
    public JComboBox getEstados(){
        return estados;
    }
    
    public JTable getTablaCrias(){
        return tablaCrias;
    }
    
    public String getSelectedCria(){
        return tablaCrias.getValueAt(tablaCrias.getSelectedRow(), 0).toString();
    }
    
    public void mostrarIngresarCorral(){
        ingresaCorral.setVisible(true);
    }
       public void ocultarIngresarDieta(){
        ingresaCorral.setVisible(false);
    }
    
    public void quitaCria(){
        mTablaSensores=new DefaultTableModel();
        
        mTablaSensores.addColumn("ID Cria");
        mTablaSensores.addColumn("Fecha");
        mTablaSensores.addColumn("F. Cardiaca");
        mTablaSensores.addColumn("F. Respiratoria");
        
        tablaSensores.setModel(mTablaSensores);
        
        int row=tablaCrias.getSelectedRow();
        mTablaCrias.removeRow(row);
        ingresaCorral.comboCorral.setSelectedIndex(0);
        ingresaCorral.setVisible(false);
        
    }
    
    public void llenaTablaCrias(){
        mTablaCrias=new DefaultTableModel();
        
        mTablaCrias.addColumn("ID Cria");
        mTablaCrias.addColumn("Peso");
        mTablaCrias.addColumn("Cant. Grasa");
        mTablaCrias.addColumn("Col. Musc.");
        mTablaCrias.addColumn("Estado");
        mTablaCrias.addColumn("Ciudad");
        mTablaCrias.addColumn("Fec. Ingreso");
        mTablaCrias.addColumn("Dias");
        Vector<Vector>v;
        if(ciudades.getSelectedItem()==null || ciudades.getSelectedItem()=="Seleccione"){
            v=CorralesTerneroModelo.traeVector("CriasEnfermasView","1=1");
        } else {        
            String ciuNombre=ciudades.getSelectedItem().toString();
            v=CorralesTerneroModelo.traeVector("CriasEnfermasView", "Ciudad='"+ciuNombre+"'");
        }
        
        
        for(int i=0;i<v.size();i++){
            mTablaCrias.addRow(v.get(i));
        }
        tablaCrias.setModel(mTablaCrias);
    }
    
    public void llenaTablaSensores(int row){
        mTablaSensores=new DefaultTableModel();
        
        mTablaSensores.addColumn("ID Cria");
        mTablaSensores.addColumn("Fecha");
        mTablaSensores.addColumn("F. Cardiaca");
        mTablaSensores.addColumn("F. Respiratoria");
        
        int criId=Integer.parseInt(tablaCrias.getValueAt(row, 0).toString());
        Vector<Vector>v=CorralesTerneroModelo.traeVector("ReportaSensoresView", "CriId="+criId);
        for(int i=0;i<v.size();i++){
            mTablaSensores.addRow(v.get(i));
        }
        tablaSensores.setModel(mTablaSensores);
    }
    
    public void llenaCombo(JComboBox combo){
        
        
        if(combo.getItemCount()>0){
            combo.removeAllItems();
        }
        if(combo==estados){
            Rutinas.insertaCombo("EdoNombre", "Estados", combo);
        } else {
            Rutinas.insertaCombo("CiuNombre", "Ciudades","EdoId="+estados.getSelectedIndex(), combo);
        }
        
        if(ingresaCorral.comboCorral.getItemCount()==0){
            Rutinas.insertaCombo("CorNombre", "Corrales", ingresaCorral.comboCorral);
            ingresaCorral.comboCorral.removeItem("Enfermos");
        }
    }
    
    public void limpiaCombos(){
        ItemListener[]listenersEstados=estados.getItemListeners();
        ItemListener[]listenersCiudades=ciudades.getItemListeners();
        ciudades.removeAllItems();
        estados.setSelectedIndex(0);
    }
    
    public void muestra(){
        llenaCombo(estados);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
