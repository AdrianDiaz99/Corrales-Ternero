package InsertarCria;

import componentes.*;
import corralesternero.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

public class DatosCria extends JDialog {

    private JLeeReal peso, cantGrasa;
    private JComboBox colorMusculo, ciudad, corral, estado;
    private JButton grabar,limpiar, btnGeneraGrasa, imprimir;
    private JTextField grasa;
    private Font font;
    
    private JLabel etiPeso, etiCantGrasa, etiColorM, etiCiudad, etiEstado,
            etiGrasa,etiCorral;
    
    private JTable criasInsertadas;
    private JScrollPane panInsertadas;
    private DefaultTableModel modelo;
    private int graId;
    
    public DatosCria() {
        setTitle("Agregar Nueva Cria");
        setLayout(null);
        setSize(740, 590);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
        

        peso = new JLeeReal(7);
        etiCantGrasa = new JLabel("Cantidad de grasa:",SwingConstants.RIGHT);
        etiCiudad = new JLabel("Ciudad:",SwingConstants.RIGHT);
        etiColorM = new JLabel("Color del Musculo:",SwingConstants.RIGHT);
        etiCorral = new JLabel("Corral",SwingConstants.RIGHT);
        etiEstado = new JLabel("Estado:",SwingConstants.RIGHT);
        etiGrasa = new JLabel("Grasa:",SwingConstants.RIGHT);
        etiPeso = new JLabel("Peso:",SwingConstants.RIGHT);
        cantGrasa = new JLeeReal(7);
        colorMusculo = new JComboBox();
        estado = new JComboBox();
        ciudad = new JComboBox();
        grasa = new JTextField();
        corral = new JComboBox();
        grabar = new JButton("Grabar");
        limpiar = new JButton("Limpiar");
        imprimir = new JButton("Imprimir");
        btnGeneraGrasa = new JButton("Calcular Tipo de grasa");
        criasInsertadas=new JTable();
        panInsertadas=new JScrollPane(criasInsertadas);
        font=new Font("Arial",Font.BOLD,14);
        
        grasa.setEditable(false);
        grasa.setFont(font);
        
        etiPeso.setBounds(80,10,32,20);
        peso.setBounds(133,7,50,28);
        etiCantGrasa.setBounds(205, 10, 107, 20);
        cantGrasa.setBounds(317, 7, 50, 28);
        etiColorM.setBounds(392, 10, 107, 20);
        colorMusculo.setBounds(504,7,100,28);
        etiEstado.setBounds(80, 54, 50, 20);
        estado.setBounds(133, 50, 100, 28);
        etiCiudad.setBounds(80, 98, 50, 20);
        ciudad.setBounds(133, 93, 100, 28);
        etiCorral.setBounds(280, 58, 50, 20);
        corral.setBounds(265,77,100,28);
        etiGrasa.setBounds(440, 82, 50, 20);
        grasa.setBounds(382, 101, 180, 28);
        btnGeneraGrasa.setBounds(390, 50, 165, 30);
        panInsertadas.setBounds(20, 160, 698, 340);
        imprimir.setBounds(20,513,100,30);
        grabar.setBounds(600, 513, 100, 30);
        limpiar.setBounds(480, 513, 100, 30);
        
        add(grabar);
        add(limpiar);
        add(imprimir);
        add(panInsertadas);
        add(etiPeso);
        add(peso);
        add(etiCantGrasa);
        add(cantGrasa);
        add(etiColorM);
        add(colorMusculo);
        add(etiEstado);
        add(estado);
        add(etiCiudad);
        add(ciudad);
        add(etiCorral);
        add(corral);
        add(etiGrasa);
        add(grasa);
        add(btnGeneraGrasa);
                  
    }
    
    public void limpiaComponentes(){
        peso.setText("");
        cantGrasa.setText("");
        colorMusculo.setSelectedIndex(0);
        corral.setSelectedIndex(0);
        estado.setSelectedIndex(0);
        grasa.setText("");

    }
    public void llenaCombos(){
        vaciaCombos();
        /*if(!*/Rutinas.insertaCombo("colnombre","coloresmusculo",colorMusculo);/*)
            return;*/
  
        /*if(!*/Rutinas.insertaCombo("cornombre", "corrales",corral);/*)
            return;*/
        
        Rutinas.insertaCombo("edonombre", "estados",estado);
    }
    public void vaciaCombos(){
        colorMusculo.removeAllItems();
        corral.removeAllItems();
        estado.removeAllItems();
    }
   
    public void recibeInsercionCria(int criID) {
        String mensaje;
        int tipo;
        String titulo;
        //CriID sera -1 cuando no se pueda hacer la insercion
        if (criID == -1) {
            mensaje = "No se pudieron grabar los datos";
            tipo = JOptionPane.ERROR_MESSAGE;
            titulo = "Error";
        } else {
            mensaje = "Se grabo correctamente la informacion";
            tipo = JOptionPane.INFORMATION_MESSAGE;
            titulo = "Aviso";
            insertaRow(criID);
            limpiaComponentes();
        }
        JOptionPane.showMessageDialog(null, mensaje, titulo, tipo);
    }
    
    private void insertaRow(int criID){
        String senId;
        String vPeso = peso.getText(), vCantGrasa = cantGrasa.getText(),
                vColMusc = colorMusculo.getSelectedItem().toString(),
                vCorral = corral.getSelectedItem().toString(),
                vEst = estado.getSelectedItem().toString(),
                vCiu = ciudad.getSelectedItem().toString(),
                vGra = grasa.getText();
        //Obtenemos valor numerico de la grasa de cobertura
        int vNumGrasaC = Integer.parseInt(grasa.getText().substring(20,21));
        
        //Grasa de cobertura 2 requiere sensor
        if( vNumGrasaC == 2){
            senId="Pendiente";
        } else {
            senId="N/A";
        }
        String [] tupla = { criID+"", vPeso, vCantGrasa, vColMusc, vCorral, vEst,
            vCiu, vGra, senId };
        modelo.addRow(tupla);
    }
    
    public void ocultar() {
        setVisible(false);
        peso.setText("");
        cantGrasa.setText("");
        grasa.setText("");
        colorMusculo.setSelectedIndex(0);
        corral.setSelectedIndex(0);
        estado.setSelectedIndex(0);
    }
    
    public void inicializarModelo(){
        
        modelo = new DefaultTableModel();
        modelo.addColumn("ID Cria");
        modelo.addColumn("Peso");
        modelo.addColumn("Cant. Grasa");
        modelo.addColumn("Col. Musculo");
        modelo.addColumn("Corral");
        modelo.addColumn("Estado");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Tipo Grasa");
        modelo.addColumn("Sensor");
        
    }
    
    public void darFormatoColumnas(){
        
        criasInsertadas.getColumnModel().getColumn(0).setPreferredWidth(25);
        criasInsertadas.getColumnModel().getColumn(1).setPreferredWidth(40);
        criasInsertadas.getColumnModel().getColumn(2).setPreferredWidth(60);
        criasInsertadas.getColumnModel().getColumn(4).setPreferredWidth(30);
        criasInsertadas.getColumnModel().getColumn(7).setPreferredWidth(100);
        criasInsertadas.getColumnModel().getColumn(8).setPreferredWidth(40);
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        criasInsertadas.getColumnModel().getColumn(0).setCellRenderer(tcr);
        criasInsertadas.getColumnModel().getColumn(1).setCellRenderer(tcr);
        criasInsertadas.getColumnModel().getColumn(2).setCellRenderer(tcr);
        criasInsertadas.getColumnModel().getColumn(3).setCellRenderer(tcr);
        criasInsertadas.getColumnModel().getColumn(4).setCellRenderer(tcr);
        criasInsertadas.getColumnModel().getColumn(5).setCellRenderer(tcr);
        criasInsertadas.getColumnModel().getColumn(6).setCellRenderer(tcr);
        criasInsertadas.getColumnModel().getColumn(7).setCellRenderer(tcr);
        criasInsertadas.getColumnModel().getColumn(8).setCellRenderer(tcr);
        
    }

    public void muestra(Vector< Vector<String> > criasInsertadasHoy) {
        llenaCombos();
        inicializarModelo();
        for( int i = 0; i < criasInsertadasHoy.size() ; i++ ){
            
            //Cuando el sensor viene null lo cambiamos a "Pendiente"
            if(criasInsertadasHoy.get(i).get(8).equals("null")){
                
                criasInsertadasHoy.get(i).remove(8);
                criasInsertadasHoy.get(i).add("Pendiente");
                
            }
            
            modelo.addRow(criasInsertadasHoy.get(i));
            
            
        }
        criasInsertadas.setModel(modelo);
        
        darFormatoColumnas();
        
        setVisible(true);
    }
    
   
    public void setControlador(DatosCriaControlador C){
        
        btnGeneraGrasa.addActionListener(C);
        limpiar.addActionListener(C);
        imprimir.addActionListener(C);
        btnGeneraGrasa.addActionListener(C);
        grabar.addActionListener(C);
        cantGrasa.addFocusListener(C);
        peso.addFocusListener(C);
        colorMusculo.addFocusListener(C);
        peso.addKeyListener(C);
        cantGrasa.addKeyListener(C);
        colorMusculo.addKeyListener(C);
        corral.addKeyListener(C);
        estado.addItemListener(C);
        estado.addKeyListener(C);
        ciudad.addKeyListener(C);
        grabar.addKeyListener(C);
        limpiar.addKeyListener(C);
        addWindowListener(C);
    }

    public void setCiudades(int indice) {
        ciudad.removeAllItems();
        ResultSet res=CorralesTerneroModelo.getColumnas("ciunombre", "ciudades", "edoid="+indice);
        if(res!=null)
            Rutinas.insertaCombo(res,"ciunombre", ciudad);
    }
    
    //Validar que se hayan ingresado datos en peso, grasa y color
    public boolean ready(){
        int tPeso=peso.getText().length();
        int tGrasa=cantGrasa.getText().length();
        int color=colorMusculo.getSelectedIndex();
        if( tPeso == 0 || tGrasa == 0 || color == 0 )
            return false;
        return true;
    }
    
    public void limpiaTabla(){
        for(int i=0;i<modelo.getRowCount();i++){
            modelo.removeRow(i);
        }
    }
    
    public void setGrasa(int graId){
        if(graId == -1){
            JOptionPane.showMessageDialog(null, "Problema al obtener grasa de cobertura",
                    "Error", JOptionPane.ERROR_MESSAGE);
            grasa.setText("");
            return;
        }
        this.graId=graId;
        grasa.setText(" Grasa de cobertura " + ( graId - 1 ) + " " );
    }
        
    public JLeeReal getPeso(){
        return peso;
    }
    public JLeeReal getCantGrasa(){
        return cantGrasa;
    }
    public JComboBox getColorMusculo(){
        return colorMusculo;
    }
    public JComboBox getCiudad(){
        return ciudad;
    }
    public int getGrasa(){
        return graId;
    }
    public JButton getImprimir(){
        return imprimir;
    }
    public JButton getCalGrasa(){
        return btnGeneraGrasa;
    }    
    public JComboBox getCorral(){
        return corral;
    }
    public JComboBox getEstado(){
        return estado;
    }
    public JButton getGrabar(){
        return grabar;
    }
    public JButton getLimpiar(){
        return limpiar;
    }
    public JTable getTablaCrias(){
        return criasInsertadas;
    }
    
}
